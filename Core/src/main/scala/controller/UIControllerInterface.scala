package controller

import scala.concurrent.Future

trait UIControllerInterface:
  def notifyObservers(): Future[Unit]


