package de.htwg.se.toybrokersludo.aview

import de.htwg.se.toybrokersludo.model
import de.htwg.se.toybrokersludo.model.{Move, Player}
import de.htwg.se.toybrokersludo.controller.Controller
import scala.io.StdIn.readLine

class TUI(controller: Controller) {

  def run() =
    println(controller.field.toString)
    inputLoop()

  def update() = println(controller.field.toString)

  def inputLoop(): Unit =
    analyseInput(readLine) match
      case Some(move) => controller.doAndPublish(controller.put, move, this)
      case None =>
    inputLoop()

  def analyseInput(input: String): Option[Move] =
    val pattern = "((B|R|Y|G)\\s[0-4]\\s[0-9]{1,2})".r
    pattern.findFirstIn(input) match
      case Some(_) => Option(model.Move(Player(input.split(" ")(1).toInt,
        input.split(" ")(0)), input.split(" ")(2).toInt))
      case None => None


}
