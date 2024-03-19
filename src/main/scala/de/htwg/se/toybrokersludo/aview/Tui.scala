package de.htwg.se.toybrokersludo.aview

import de.htwg.se.toybrokersludo.controller.Controller
import de.htwg.se.toybrokersludo.util.{Observer, possibleMoves}
import scala.io.StdIn.readLine
import scala.util.Try

class Tui(using controller: Controller) extends Observer:
  controller.add(this)
  println(controller.getGameField.toString)

  override def update(): Unit = println(controller.getGameField.toString)

  def inputLoop(): Unit =
    analyseInput(readLine())
    inputLoop()

  private def analyseInput(input: String): Unit =
    input match
      case "undo" => controller.undo()
      case "redo" => controller.redo()
      case "dice" => controller.dice()
      case "move" => doMove()
      case "load" => load()
      case "save" => save()
      case _ => println(input + " is not a valid command")

  private def doMove(): Unit =
    val options = possibleMoves(controller.getGameField)
    if (options.isEmpty) return
    println("choose between: " + options)
    var input = readLine().toInt
    while (options.size <= input) {
      println("choose one move")
      input = readLine().toInt
    }
    controller.makeMove(options(input))

  def load(): Unit =
    print("choose between:")
    println(controller.getTargets.mkString(", "))
    controller.load(readLine())

  def save(): Unit =
    print("target: ")
    controller.save(readLine())

