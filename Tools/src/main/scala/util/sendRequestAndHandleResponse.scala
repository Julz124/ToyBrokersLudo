package util

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.model.HttpRequest

import scala.concurrent.{ExecutionContextExecutor, Future}


def sendRequestAndHandleResponse[T](request: HttpRequest)(block: String => T): Future[T] = {
  implicit val system: ActorSystem[Any] = ActorSystem(Behaviors.empty, "SingleRequest")
  implicit val executionContext: ExecutionContextExecutor = system.executionContext
  
  sendHttpRequest(request).flatMap { response =>
    handleResponse(response)(block)
  }
}

 
