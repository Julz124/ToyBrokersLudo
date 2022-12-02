package de.htwg.se.toybrokersludo.aview
import de.htwg.se.toybrokersludo.model
import de.htwg.se.toybrokersludo.model.{Move, PlayToken, Token}
import de.htwg.se.toybrokersludo.controller.Controller
import de.htwg.se.toybrokersludo.util.Observer
import scala.util.{Try,Success,Failure}

import scala.collection.mutable
import scala.io.StdIn.readLine

class TUI(controller: Controller) extends Observer {

  controller.add(this)

  def run(test: Int = 0) : String =
    test match {
      case 0 =>
        menu()
        println(controller.field.toString)
        inputLoop()
      case _ =>
        menu(test).toString
        
    }

  def dice() =
    controller.dice()

  def update = println(controller.field.toString)

  def menu(test: Int = 0) : Unit =
    test match {
      case 0 => 
        println("Select number of players between 1 and 4")
        controller.startup(readLine().toInt)
      case _ =>
        controller.startup(test)
    }

  /*
  def analyseInput (input: String): Option[Move] =
    val pattern = "((B|R|Y|G)\\s[0-4]\\s[0-9]{1,2})".r
    input match
      case "undo" => controller.doAndPublish(controller.undo); None
      case "redo" => controller.doAndPublish(controller.redo); None
      case _ => pattern.findFirstIn(input) match
        case Some(_) => Option(model.Move(PlayToken.apply(input.split(" ")(1).toInt,
          input.split(" ")(0)), input.split(" ")(2).toInt))
        case None => None
*/

  def analyseInput(input: String): Try[Option[Move]] = Try {
    val pattern = "((B|R|Y|G)\\s[0-4]\\s[0-9]{1,2})".r
    input match
      case "undo" => controller.doAndPublish(controller.undo); None
      case "redo" => controller.doAndPublish(controller.redo); None
      case _ => pattern.findFirstIn(input) match
        case Some(_) => Option(model.Move(PlayToken.apply(input.split(" ")(1).toInt,
          input.split(" ")(0)), input.split(" ")(2).toInt))
  }
  /*

  val suc = analyseInput(_)
  suc: scala.util.Try[Option[Move]] = Success(_)

  val fail = analyseInput("")
  fail: scala.util.Try[Int] = Failure(java.lang.NumberFormatException: For input string: "")
  */

  def inputLoop(): String =
    analyseInput(readLine()) match
      case Success(option : Option[Move]) => option match
          case Some(move) => controller.doAndPublish(controller.put, move)
          case None => inputLoop()
      case Failure(_) => println("False input")
    inputLoop()
}
