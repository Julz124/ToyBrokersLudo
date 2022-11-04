package de.htwg.se.toybrokersludo.aview

import de.htwg.se.toybrokersludo.model
import de.htwg.se.toybrokersludo.model.{Move, Player}
import de.htwg.se.toybrokersludo.controller.Controller
import de.htwg.se.toybrokersludo.util.Observer

import scala.collection.mutable
import scala.io.StdIn.readLine

class TUI(controller: Controller) extends Observer {

  controller.add(this)

  def run() =
    println(controller.field.toString)
    inputLoop()

  override def update = println(controller.field.toString)

  def inputLoop(testparameter : mutable.Queue[String] = mutable.Queue[String]()): String =
    var input = ""
    if (testparameter.isEmpty) input = readLine() else input = testparameter.dequeue()
    if (input.equals("q")) return input
    analyseInput(input) match
      case Some(move) => controller.doAndPublish(controller.put, move)
      case None =>
    inputLoop(testparameter)

  def analyseInput(input: String): Option[Move] =
    val pattern = "((B|R|Y|G)\\s[0-4]\\s[0-9]{1,2})".r
    pattern.findFirstIn(input) match
      case Some(_) => Option(model.Move(Player(input.split(" ")(1).toInt,
        input.split(" ")(0)), input.split(" ")(2).toInt))
      case None => None


}
