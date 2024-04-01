package controller

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.*
import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.unmarshalling.Unmarshal
import model.GameField
import play.api.libs.json.Json
import util.json.JsonReaders.*
import util.json.JsonWriters.*
import util.{handleResponse, sendHttpRequest}

import scala.concurrent.duration.*
import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util.{Failure, Success}

class PersistenceController:
  implicit val system: ActorSystem[Any] = ActorSystem(Behaviors.empty, "system")
  implicit val executionContext: ExecutionContextExecutor = system.executionContext

  def getTargets: Future[List[String]] =
    val request = HttpRequest(uri = "http://localhost:8081/persistence/getTargets")
    sendHttpRequest(request).flatMap { response =>
      handleResponse(response)(jsonStr => Json.parse(jsonStr).as[List[String]])
    }

  def load(fileName: String): Future[GameField] =
    val request = HttpRequest(uri = s"http://localhost:8081/persistence/load?file=$fileName")
    sendHttpRequest(request).flatMap { response =>
      handleResponse(response)(jsonStr => Json.parse(jsonStr).as[GameField])
    }

  def save(gameField: GameField, fileName: String): Future[Unit] =
    val jsonBody = Json.toJson(gameField).toString()
    val request = HttpRequest(
      method = HttpMethods.POST,
      uri = s"http://localhost:8081/persistence/save?file=$fileName",
      entity = HttpEntity(ContentTypes.`application/json`, jsonBody)
    )
    sendHttpRequest(request).map { response =>
      handleResponse(response)(jsonStr => Json.parse(jsonStr))
    }






