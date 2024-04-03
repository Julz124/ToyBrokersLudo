
import FileIO.{JsonFileIO, RestPersistenceAPI}
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.*
import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.unmarshalling.Unmarshal
import controller.RestCoreAPI
import controller.impl.Controller
import model.{Cell, GameField, GameState, Move, Player}
import org.scalatest.BeforeAndAfterEach
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import play.api.libs.json.Json
import util.json.JsonReaders.*
import util.json.JsonWriters.*
import util.{handleResponse, sendHttpRequest}

import concurrent.duration.DurationInt
import scala.concurrent.{Await, ExecutionContextExecutor, Future}
import scala.util.{Failure, Success}


class RestCoreAPISpec extends AnyWordSpec with Matchers with BeforeAndAfterEach {

  implicit val system: ActorSystem[Any] = ActorSystem(Behaviors.empty, "RestCoreAPISpec")
  implicit val executionContext: ExecutionContextExecutor = system.executionContext

  val restPersistenceAPI: RestPersistenceAPI = RestPersistenceAPI()
  restPersistenceAPI.start()

  val restCoreAPI: RestCoreAPI = RestCoreAPI()
  restCoreAPI.start()

  override def beforeEach(): Unit = {
    restPersistenceAPI.fileIO = JsonFileIO()
    restCoreAPI.controller = Controller(using restCoreAPI.persistenceController)(using restCoreAPI.uiController)
  }

  "The RestCoreAPI" should {
    "return a welcome message for the root path" in {
      val request = HttpRequest(uri = "http://localhost:8082")
      val response = sendHttpRequest(request).flatMap { response =>
        Future.successful(
          response.entity match {
            case HttpEntity.Strict(_, data) => data.utf8String
            case _ => "Unknown error occurred"
          }
        )
      }

      val expectedResponse =
        """
          |<h1>Welcome to the REST Core API service!</h1>
          |<h2>Available routes:</h2>
          |
          |<p><a href="/core/gameField">GET           ->     core/gameField</a></p>
          |<p><a href="/core/possibleMoves">GET       ->     core/possibleMoves</a></p>
          |<p><a href="/core/getTargets">GET          ->     core/getTargets</a></p>
          |<p><a href="/core/move">POST               ->     core/move</a></p>
          |<p><a href="/core/dice">POST               ->     core/dice</a></p>
          |<p><a href="/core/undo">POST               ->     core/undo</a></p>
          |<p><a href="/core/redo">POST               ->     core/redo</a></p>
          |<p><a href="/core/save">POST               ->     core/save</a></p>
          |<p><a href="/core/load">POST               ->     core/load</a></p>
          |<br>
          |""".stripMargin.trim

      Await.result(response, 10.seconds).replaceAll("\\s+", "") shouldEqual expectedResponse.replaceAll("\\s+", "")
    }
  }

  "GET /core/gameField" should {
    "return the game field for GET /core/gameField" in {
      val request = HttpRequest(uri = "http://localhost:8082/core/gameField")
      val response = sendHttpRequest(request).flatMap { response =>
        handleResponse(response)(jsonStr => Json.parse(jsonStr).as[GameField])
      }
      Await.result(response, 10.seconds) shouldBe GameField.init()
    }
  }

  "POST /core/move" should {
    "make move" in {
      restCoreAPI.controller.gameField = restCoreAPI.controller.gameField.copy(
        gameState = GameState(shouldDice = false, currentPlayer = Player.Green, diceNumber = 6)
      )
      val jsonBody = Json.toJson(Move(20, 0)).toString()
      val request = HttpRequest(
        method = HttpMethods.POST,
        uri = s"http://localhost:8082/core/move",
        entity = HttpEntity(ContentTypes.`application/json`, jsonBody)
      )
      val response = sendHttpRequest(request).map { response =>
        handleResponse(response)(jsonStr => Json.parse(jsonStr))
      }
      Await.result(response, 10.seconds)
      restCoreAPI.controller.gameField.map.get(0, 0).get.token shouldBe None
    }
  }

  "POST /core/move" should {
    "return error for move if player have to dice" in {
      val jsonBody = Json.toJson(Move(20, 0)).toString()
      val request = HttpRequest(
        method = HttpMethods.POST,
        uri = s"http://localhost:8082/core/move",
        entity = HttpEntity(ContentTypes.`application/json`, jsonBody)
      )
      val response = sendHttpRequest(request).map { response =>
        handleResponse(response)(jsonStr => Json.parse(jsonStr))
      }
      response.onComplete {
        case Success(value) => throw IllegalStateException()
        case Failure(exception) =>
          exception shouldBe RuntimeException("409 Conflict: You have to Dice")
      }
    }
  }

  "POST /core/dice" should {
    "make dice" in {
      val request = HttpRequest(
        method = HttpMethods.POST,
        uri = s"http://localhost:8082/core/dice"
      )
      val response = sendHttpRequest(request).map { response =>
        handleResponse(response)(jsonStr => Json.parse(jsonStr))
      }
      Await.result(response, 10.seconds)
      restCoreAPI.controller.gameField.gameState.diceNumber should not be 0
    }
  }

  "POST /core/dice" should {
    "return error for dice if player have to move" in {
      restCoreAPI.controller.gameField = restCoreAPI.controller.gameField.copy(
        gameState = GameState(shouldDice = false, currentPlayer = Player.Green, diceNumber = 6)
      )
      val request = HttpRequest(
        method = HttpMethods.POST,
        uri = s"http://localhost:8082/core/dice"
      )
      val response = sendHttpRequest(request).map { response =>
        handleResponse(response)(jsonStr => Json.parse(jsonStr))
      }
      response.onComplete {
        case Success(value) => throw IllegalStateException()
        case Failure(exception) =>
          exception shouldEqual RuntimeException("409 Conflict: You have to Move")
      }
    }
  }

  "POST /core/redo" should {
    "redo" in {
      val requestDice = HttpRequest(
        method = HttpMethods.POST,
        uri = s"http://localhost:8082/core/dice"
      )
      val responseDice = sendHttpRequest(requestDice).map { response =>
        handleResponse(response)(jsonStr => Json.parse(jsonStr))
      }
      Await.result(responseDice, 10.seconds)

      val requestUndo = HttpRequest(
        method = HttpMethods.POST,
        uri = s"http://localhost:8082/core/undo"
      )
      val responseUndo = sendHttpRequest(requestUndo).map { response =>
        handleResponse(response)(jsonStr => Json.parse(jsonStr))
      }
      Await.result(responseUndo, 10.seconds)

      val requestRedo = HttpRequest(
        method = HttpMethods.POST,
        uri = s"http://localhost:8082/core/undo"
      )
      val responseRedo = sendHttpRequest(requestRedo).map { response =>
        handleResponse(response)(jsonStr => Json.parse(jsonStr))
      }
      Await.result(responseRedo, 10.seconds)

      restCoreAPI.controller.gameField.gameState.diceNumber should be(0)
    }
  }

  "POST /core/possibleMoves" should {
    "return error for possibleMoves if player have to dice" in {
      val request = HttpRequest(uri = "http://localhost:8082/core/possibleMoves")
      val response = sendHttpRequest(request).flatMap { response =>
        handleResponse(response)(jsonStr => Json.parse(jsonStr).as[List[String]])
      }
      response.onComplete {
        case Success(value) => throw IllegalStateException()
        case Failure(exception) =>
          exception shouldBe RuntimeException("409 Conflict: You have to Dice")
      }
    }
  }

  "POST /core/possibleMoves" should {
    "return possibleMoves" in {
      restCoreAPI.controller.gameField = restCoreAPI.controller.gameField.copy(
        gameState = GameState(shouldDice = false, currentPlayer = Player.Green, diceNumber = 6)
      )
      val request = HttpRequest(uri = "http://localhost:8082/core/possibleMoves")
      val response = sendHttpRequest(request).flatMap { response =>
        handleResponse(response)(jsonStr => Json.parse(jsonStr).as[List[Move]])
      }
      Await.result(response, 10.seconds) should contain theSameElementsAs List(
        Move(20, 0), Move(20, 1), Move(20, 2), Move(20, 3)
      )
    }
  }

  "POST /core/undo" should {
    "undo" in {
      val requestDice = HttpRequest(
        method = HttpMethods.POST,
        uri = s"http://localhost:8082/core/dice"
      )
      val responseDice = sendHttpRequest(requestDice).map { response =>
        handleResponse(response)(jsonStr => Json.parse(jsonStr))
      }
      Await.result(responseDice, 10.seconds)

      val requestUndo = HttpRequest(
        method = HttpMethods.POST,
        uri = s"http://localhost:8082/core/undo"
      )
      val responseUndo = sendHttpRequest(requestUndo).map { response =>
        handleResponse(response)(jsonStr => Json.parse(jsonStr))
      }
      Await.result(responseUndo, 10.seconds)

      restCoreAPI.controller.gameField.gameState.diceNumber should be(0)
    }
  }

}
