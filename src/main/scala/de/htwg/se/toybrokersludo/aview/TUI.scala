package de.htwg.se.toybrokersludo.aview

import de.htwg.se.toybrokersludo.controller.Controller
import de.htwg.se.toybrokersludo.model.Move
import de.htwg.se.toybrokersludo.model.Player


import scala.io.StdIn.readLine

class TUI(controller: Controller) {

  def run() =
    println(controller.field.toString)
    inputLoop()

  def update = println(controller.field.toString)

  def inputLoop(): Unit =
    analyseInput(readLine) match
      case Some(move) => controller.doAndPublish(controller.put, move)
      case None =>
    inputLoop()

  def analyseInput(input: String): Option[Move] =
    input match
      case "" => None
      case "R 1 0" => Option(Move(Player(1), 0))
}
