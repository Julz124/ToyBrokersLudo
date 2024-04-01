package aview

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.model.*
import akka.http.scaladsl.unmarshalling.Unmarshal
import model.{GameField, Move}
import play.api.libs.json.Json
import util.json.JsonReaders.*
import util.json.JsonWriters.*
import util.{handleResponse, sendHttpRequest, sendRequestAndHandleResponse}

import scala.concurrent.{ExecutionContextExecutor, Future}

class CoreController:
  implicit val system: ActorSystem[Any] = ActorSystem(Behaviors.empty, "system")
  implicit val executionContext: ExecutionContextExecutor = system.executionContext

  def gameField: Future[GameField] =
    val request = HttpRequest(uri = "http://localhost:8082/core/gameField")
    sendRequestAndHandleResponse(request)(jsonStr => Json.parse(jsonStr).as[GameField])

  def possibleMoves: Future[List[Move]] =
    val request = HttpRequest(uri = "http://localhost:8082/core/possibleMoves")
    sendRequestAndHandleResponse(request)(jsonStr => Json.parse(jsonStr).as[List[Move]])

  def move(move: Move): Future[Unit] =
    val jsonBody = Json.toJson(move).toString()
    val request = HttpRequest(
      method = HttpMethods.POST,
      uri = s"http://localhost:8082/core/move",
      entity = HttpEntity(ContentTypes.`application/json`, jsonBody)
    )
    sendRequestAndHandleResponse(request)(jsonStr => Json.parse(jsonStr))


  def dice(): Future[Unit] =
    val request = HttpRequest(
      method = HttpMethods.POST,
      uri = s"http://localhost:8082/core/dice"
    )
    sendRequestAndHandleResponse(request)(jsonStr => Json.parse(jsonStr))

  def undo(): Future[Unit] =
    val request = HttpRequest(
      method = HttpMethods.POST,
      uri = s"http://localhost:8082/core/undo"
    )
    sendRequestAndHandleResponse(request)(jsonStr => Json.parse(jsonStr))

  def redo(): Future[Unit] =
    val request = HttpRequest(
      method = HttpMethods.POST,
      uri = s"http://localhost:8082/core/redo"
    )
    sendRequestAndHandleResponse(request)(jsonStr => Json.parse(jsonStr))

  def save(fileName: String): Future[Unit] = {
    val request = HttpRequest(
      method = HttpMethods.POST,
      uri = "http://localhost:8082/core/save",
      entity = HttpEntity(ContentTypes.`application/json`, fileName)
    )
    sendRequestAndHandleResponse(request)(jsonStr => Json.parse(jsonStr))
  }

  def load(fileName: String): Future[Unit] =
    val request = HttpRequest(
      method = HttpMethods.POST,
      uri = s"http://localhost:8082/core/load?target=$fileName"
    )
    sendRequestAndHandleResponse(request)(jsonStr => Json.parse(jsonStr))

  def getTargets: Future[List[String]] =
    val request = HttpRequest(uri = "http://localhost:8082/core/getTargets")
    sendRequestAndHandleResponse(request)(jsonStr => Json.parse(jsonStr).as[List[String]])




