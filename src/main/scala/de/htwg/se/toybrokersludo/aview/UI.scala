package de.htwg.se.toybrokersludo.aview

import de.htwg.se.toybrokersludo.controller.ControllerInterface
import de.htwg.se.toybrokersludo.controller.controllerBaseImpl.Controller
import de.htwg.se.toybrokersludo.model
import de.htwg.se.toybrokersludo.model.{Move, PlayToken, Token}
import de.htwg.se.toybrokersludo.util.Observer
import scala.util.{Try,Success,Failure}

trait UI (controller: ControllerInterface) extends Observer {
  controller.add(this)

  def run: Unit =
    menue
    inputLoop

  def inputLoop: Unit

  def menue: Unit

  def analyseInput(input: String): Try[Option[Move]]
}
