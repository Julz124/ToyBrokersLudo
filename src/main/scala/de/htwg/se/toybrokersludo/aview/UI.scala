package de.htwg.se.toybrokersludo.aview

import de.htwg.se.toybrokersludo.controller.ControllerInterface
import de.htwg.se.toybrokersludo.controller.controllerBaseImpl.Controller
import de.htwg.se.toybrokersludo.model.{Move, PlayToken, Token}
import de.htwg.se.toybrokersludo.util.Observer

import scala.util.{Try,Success,Failure}

trait UI (controller: ControllerInterface) extends Observer {
  controller.add(this)

  def run(makeMenue : Boolean = false): Unit =
    if (makeMenue) menue()
    inputLoop()

  def menue(): Unit

  def inputLoop(): Unit

  def analyseInput(input: String): Try[Option[Move]]
}
