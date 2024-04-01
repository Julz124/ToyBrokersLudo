package controller

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.javadsl.server.RequestEntityExpectedRejection
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives.{entity, *}
import akka.stream.ActorMaterializer
import model.{GameField, Move}
import play.api.libs.json.Json
import util.json.JsonReaders.*
import util.json.JsonWriters.*

import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, ExecutionContextExecutor}
import scala.util.{Failure, Success}

class RestCoreAPI:
  implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "my-system")
  implicit val executionContext: ExecutionContextExecutor = system.executionContext

  private val controller = new DefaultController
  private val RestUIPort = 8082
  private val routes: String =
    """
      <h1>Welcome to the REST Core API service!</h1>
      <h2>Available routes:</h2>

      <p><a href="/core/gameField">GET           ->     core/gameField</a></p>
      <p><a href="/core/possibleMoves">GET       ->     core/possibleMoves</a></p>
      <p><a href="/core/getTargets">GET          ->     core/getTargets</a></p>
      <p><a href="/core/move">POST               ->     core/move</a></p>
      <p><a href="/core/dice">POST               ->     core/dice</a></p>
      <p><a href="/core/undo">POST               ->     core/undo</a></p>
      <p><a href="/core/redo">POST               ->     core/redo</a></p>
      <p><a href="/core/save">POST               ->     core/save</a></p>
      <p><a href="/core/load">POST               ->     core/load</a></p>
      <br>
    """.stripMargin

  private val route =
    concat(
      pathSingleSlash {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, routes))
      },
      get {
        path("core" / "gameField") {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, Json.toJson(controller.gameField).toString()))
        }
      },
      get {
        path("core" / "possibleMoves") {
          controller.possibleMoves match {
            case Success(result) =>
              complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, Json.toJson(result).toString()))
            case Failure(exception) =>
              complete(HttpResponse(StatusCodes.Conflict, entity = exception.getMessage))
          }
        }
      },
      get {
        path("core" / "getTargets") {
          try {
            val targets = Await.result(controller.getTargets, 10.seconds)
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, Json.toJson(targets).toString()))
          } catch {
            case ex: Exception =>
              complete(HttpResponse(StatusCodes.Conflict, entity = ex.getMessage))
          }
        }
      },
      path("core" / "move") {
        post {
          entity(as[String]) { moveJson =>
            val move: Move = Json.fromJson(Json.parse(moveJson)).get
            controller.makeMove(move) match
              case Success(_) =>
                complete(HttpResponse(StatusCodes.OK, entity = ""))
              case Failure(exception) =>
                complete(HttpResponse(StatusCodes.Conflict, entity = exception.getMessage))
          }
        }
      },
      path("core" / "dice") {
        post {
          controller.dice() match
            case Success(_) =>
              complete(HttpResponse(StatusCodes.OK, entity = ""))
            case Failure(exception) =>
              complete(HttpResponse(StatusCodes.Conflict, entity = exception.getMessage))
        }
      },
      path("core" / "undo") {
        post {
          controller.undo() match
            case Success(_) =>
              complete(HttpResponse(StatusCodes.OK, entity = ""))
            case Failure(exception) =>
              complete(HttpResponse(StatusCodes.Conflict, entity = exception.getMessage))
        }
      },
      path("core" / "redo") {
        post {
          controller.redo() match
            case Success(_) =>
              complete(HttpResponse(StatusCodes.OK, entity = ""))
            case Failure(exception) =>
              complete(HttpResponse(StatusCodes.Conflict, entity = exception.getMessage))
        }
      },
      path("core" / "save") {
        post {
          entity(as[String]) { target =>
            try {
              Await.result(controller.save(target), 10.seconds)
              complete(HttpResponse(StatusCodes.OK, entity = ""))
            } catch {
              case ex: Exception =>
                complete(HttpResponse(StatusCodes.Conflict, entity = ex.getMessage))
            }
          }
        }
      },
      path("core" / "load") {
        post {
          entity(as[String]) { target =>
            try {
              Await.result(controller.load(target), 10.seconds)
              complete(HttpResponse(StatusCodes.OK, entity = ""))
            } catch {
              case ex: Exception =>
                complete(HttpResponse(StatusCodes.Conflict, entity = ex.getMessage))
            }
          }
        }
      },
    )

  def start(): Unit =
    val binding = Http().newServerAt("localhost", RestUIPort).bind(route)

    binding.onComplete {
      case Success(binding) =>
        println(s"CoreAPI service online at http://localhost:$RestUIPort/")
      case Failure(exception) =>
        println(s"CoreAPI service failed to start: ${exception.getMessage}")
    }

