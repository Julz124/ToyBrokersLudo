package de.htwg.se.toybrokersludo.aview

import de.htwg.se.toybrokersludo.controller.ControllerInterface
import de.htwg.se.toybrokersludo.model
import de.htwg.se.toybrokersludo.model.{Move, PlayToken, Token}
import de.htwg.se.toybrokersludo.util.Observer

import scala.util.{Failure, Success, Try}
import scala.collection.mutable
import scala.io.StdIn.readLine

class TUI(using controller: ControllerInterface) extends UI(controller) {

  override def update = println(controller.getField.toString)

  override def menue() =
    println("choose number of player between 1 and 4")
    var input = readLine()
    while(!input.matches("[0-4]")) {
      input = readLine()
    }
    controller.startup(input.toInt)
      

  def analyseInput(input: String): Try[Option[Move]] = Try {
    val pattern = "((B|R|Y|G)\\s[0-4]\\s[0-9]{1,2})".r
    input match
      case "undo" => controller.doAndPublish(controller.undo); None
      case "redo" => controller.doAndPublish(controller.redo); None
      case "dice" => controller.doAndPublish(controller.dice); None
      case "move" => doMove();
      case "load" => load();
      case "save" => save();
      case _ => pattern.findFirstIn(input) match
        case Some(_) => Option(model.Move(PlayToken.apply(input.split(" ")(1).toInt,
          input.split(" ")(0)), input.split(" ")(2).toInt))
  }


  def doMove(): None.type =
    val options = controller.getPossibleMoves(controller.getDice)
    if (options.isEmpty) throw new IllegalStateException()
    println("choose between: " + options)
    var input = readLine().toInt
    while (options.size <= input) {println ("choose one move")
      input = readLine().toInt
    }
    controller.doAndPublish (controller.move, options (input))
    None

  def load() : None.type =
    controller.getTargets().appended("choose between:").reverse.foreach((e : String) => println(e))
    controller.load(readLine())
    None

 def save() : None.type =
   print("target: ")
   controller.save(readLine())
   None

  override def inputLoop() : Unit =
    analyseInput(readLine()) match
      case Success(option : Option[Move]) => option match
          case Some(move) => controller.doAndPublish(controller.move, move)
          case None => inputLoop()
      case Failure(_) => println("False input")
    inputLoop()
}
