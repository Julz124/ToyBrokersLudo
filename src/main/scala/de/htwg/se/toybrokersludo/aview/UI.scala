package de.htwg.se.toybrokersludo.aview

import de.htwg.se.toybrokersludo.controller.ControllerInterface
import de.htwg.se.toybrokersludo.model.{Move, PlayToken, Token}
import de.htwg.se.toybrokersludo.util.Observer

import scala.util.{Try,Success,Failure}

trait UI extends Observer {

  def menue(): Unit

  def inputLoop(): Unit

  def analyseInput(input: String): Try[Option[Move]]
}
