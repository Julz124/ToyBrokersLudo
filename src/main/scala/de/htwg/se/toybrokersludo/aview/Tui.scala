package de.htwg.se.toybrokersludo.aview

import de.htwg.se.toybrokersludo.controller.Controller
import de.htwg.se.toybrokersludo.model.Move
import de.htwg.se.toybrokersludo.util.{Observer, possibleMoves}

import scala.io.StdIn.readLine
import scala.util.{Failure, Success, Try}

class Tui(using controller: Controller) extends Observer:
  controller.add(this)
  println(controller.getGameField.toString)

  override def update(): Unit = println(controller.getGameField.toString)

  def inputLoop(): Unit =
    analyseInput(readLine())
    inputLoop()

  private def analyseInput(input: String): Unit =
    input match
      case "undo" => doAction(controller.undo)
      case "redo" => doAction(controller.redo)
      case "dice" => doAction(controller.dice)
      case "move" => findMoves()
      case "load" => load()
      case "save" => save()
      case _ => println(input + " is not a valid command")

  private def findMoves(): Unit =
    controller.possibleMoves match
      case scala.util.Failure(exception) => println(exception.getMessage)
      case Success(moves) => doMove(moves)

  private def doMove(options: List[Move]): Unit =
    if (options.isEmpty) return
    println("choose between: " + options)
    var input = readLine().toIntOption
    while (options.size <= input.getOrElse(0)) {
      println("choose one move")
      input = readLine().toIntOption
    }
    doAction(() => controller.makeMove(options(input.get)))

  private def load(): Unit =
    doAction(
      action = () => {
        print("Choose between: ")
        controller.getTargets
      },
      onSuccess = (list: List[String]) => {
        println(list.mkString(", "))
        doAction(() => controller.load(readLine()))
      }
    )

  private def save(): Unit =
    print("target: ")
    doAction(() => controller.save(readLine()))

  private def doAction[A](action: () => Try[A], onSuccess: A => Unit = (_: A) => ()): Unit =
    action() match {
      case scala.util.Success(result) => onSuccess(result)
      case scala.util.Failure(exception) => println(exception.getMessage)
    }

