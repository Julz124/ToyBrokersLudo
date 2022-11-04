package de.htwg.se.toybrokersludo.aview

import de.htwg.se.toybrokersludo.model.{Field, Matrix, Move, Player, Stone}
import de.htwg.se.toybrokersludo.model
import de.htwg.se.toybrokersludo.controller.Controller
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.should

import scala.collection.mutable

class TUISpec extends AnyWordSpec with Matchers {

  val map : List[List[Stone]] = List(
    List(
      Stone(true, 0, None), Stone(false, -1, None)),
    List(
      Stone(true, 1, None), Stone(false, -1, None)
    ))

  val field = Field(Matrix(map))
  val controller = Controller(field)
  val tui = TUI(controller)

  "The Tui" should {


    
    "have a input loop" in
      tui.inputLoop(mutable.Queue("R 0 0", "q")) == "q"

    "recognize the input B 0 4 as put of move from player B0 to index 4 in field" in
      tui.analyseInput("B 0 4") == (Option(Move(Player(0, "B"), 4)))

    "recognize the input Y 3 4 as put of move from player Y3 to index 20 in field" in
      tui.analyseInput("Y 3 4") == (Some(Move(Player(3, "Y"), 20)))

    "recognize false input as None" in
      tui.analyseInput("xyz") == None
  }
}
