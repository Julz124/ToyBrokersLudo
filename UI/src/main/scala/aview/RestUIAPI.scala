package aview



import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives.{entity, *}
import akka.stream.ActorMaterializer
import model.GameField
import play.api.libs.json.Json
import util.Observable
import util.json.JsonReaders.*
import util.json.JsonWriters.*

import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}

class RestUIAPI extends Observable:
  implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "RestUIAPI")
  implicit val executionContext: ExecutionContextExecutor = system.executionContext
  
  private val RestUIPort = 8083
  private val routes: String =
    """
      <h1>Welcome to the REST UI API service!</h1>
      <h2>Available routes:</h2>

      <p><a href="/ui/notify">GET           ->     ui/notify</a></p>
      <br>
    """.stripMargin

  private val route =
    concat(
      pathSingleSlash {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, routes))
      },
      path("ui" / "notify") {
        post {
          notifyObservers()
          complete(HttpResponse(StatusCodes.OK, entity = ""))
        }
      },
    )

  def start(): Unit =
    val binding = Http().newServerAt("localhost", RestUIPort).bind(route)

    binding.onComplete {
      case Success(binding) =>
        println(s"UI API service online at http://localhost:$RestUIPort/")
      case Failure(exception) =>
        println(s"UI API service failed to start: ${exception.getMessage}")
    }

