import controller.impl.UIController

import scala.concurrent.Future
import concurrent.ExecutionContext.Implicits.global

class UIControllerStub extends UIController:
  var notifyObserversCalls = 0
  
  override def notifyObservers(): Future[Unit] =
    notifyObserversCalls = notifyObserversCalls + 1
    Future{}
    
