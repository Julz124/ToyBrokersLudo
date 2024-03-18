package de.htwg.se.toybrokersludo.neu.view

import de.htwg.se.toybrokersludo.controller.Controller
import de.htwg.se.toybrokersludo.util.Observer

import scala.io.StdIn.readLine
import scala.util.Try

class Tui(using controller: Controller) extends Observer {
  controller.add(this)
  println(controller.getGameField.toString)

  override def update = println(controller.getGameField.toString)

  def inputLoop(): Unit =
    analyseInput(readLine())
    inputLoop()

  def analyseInput(input: String): Unit =
    input match
      case "undo" =>
      case "redo" =>
      case "dice" => controller.dice()
      case "move" => doMove()
      case "load" =>
      case "save" =>
      case _ => println(input + " is not a valid command")

  def doMove(): Unit =
    val options = controller.possibleMoves(controller.getGameField)
    if (options.isEmpty) return
    println("choose between: " + options)
    var input = readLine().toInt
    while (options.size <= input) {
      println("choose one move")
      input = readLine().toInt
    }
    controller.makeMove(options(input))
}
