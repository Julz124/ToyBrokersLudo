import aview.{CoreController, RestUIAPI, Tui}

@main def ui(): Unit =
  val coreController = CoreController()
  val observable = RestUIAPI()
  observable.start()
  
  val tui = Tui(coreController, observable)
  tui.inputLoop()


