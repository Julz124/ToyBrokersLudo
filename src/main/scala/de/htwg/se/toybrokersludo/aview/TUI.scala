package de.htwg.se.toybrokersludo.aview
import de.htwg.se.toybrokersludo.model
import de.htwg.se.toybrokersludo.model.{Move, PlayToken, Token}
import de.htwg.se.toybrokersludo.controller.Controller
import de.htwg.se.toybrokersludo.util.Observer
import scala.util.{Try,Success,Failure}
import scala.collection.mutable
import scala.io.StdIn.readLine

class TUI(controller: Controller) extends UI(controller) {


  override def update = println(controller.field.toString)

  override def menue =
    println("Select number of players between 1 and 4")
    while(controller.field.playerNumber == 0) {

      controller.startup(readLine().toInt)
      controller.update
    }


  def analyseInput(input: String): Try[Option[Move]] = Try {
    val pattern = "((B|R|Y|G)\\s[0-4]\\s[0-9]{1,2})".r
    input match
      case "undo" => controller.doAndPublish(controller.undo); None
      case "redo" => controller.doAndPublish(controller.redo); None
      case "dice" => dice(); None
      case _ => pattern.findFirstIn(input) match
        case Some(_) => Option(model.Move(PlayToken.apply(input.split(" ")(1).toInt,
          input.split(" ")(0)), input.split(" ")(2).toInt))
  }

  def dice(): Unit =
    if (!controller.field.shouldDice) {
      println("not dice")
      return
    }
    val pattern = "((B|R|Y|G)\\s[0-4]\\s[0-9]{1,2})".r
    controller.dice()
    val dice = controller.field.dice
    println(controller.field.player.playerString + " " + dice)
    val options = controller.getPossibleMoves(dice)
    if (!options.isEmpty) {
      println("choise between: " + options)
      var input = readLine().toInt
      while (options.size <= input) {
        println("choise one")
        input = readLine().toInt
      }
      controller.doAndPublish(controller.move, options(input))
    }
    if (dice != 6) controller.nextPlayer()
    controller.invertDice


  override def inputLoop : Unit =
    analyseInput(readLine()) match
      case Success(option : Option[Move]) => option match
          case Some(move) => controller.doAndPublish(controller.move, move)
          case None => inputLoop
      case Failure(_) => println("False input")
    inputLoop
}
