package de.htwg.se.toybrokersludo.controller

import de.htwg.se.toybrokersludo.model.{Field, Matrix, Move, Player, Stone}
import de.htwg.se.toybrokersludo.aview.TUI
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ControllerSpec extends AnyWordSpec with Matchers  {

  val eol = "\n"

  val map: List[List[Stone]] = List(
    List(
      Stone(true, 0, None), Stone(false, -1, None)),
    List(
      Stone(true, 1, None), Stone(false, -1, None)
    ))

  val field = Field(Matrix(map))
  val controller = Controller(field)
  val tui = TUI(controller)

  controller.doAndPublish(controller.put, Move(Player(0, "B"), 0))

  "The Controller" should  {
    "can put" in {
      controller.field.matrix.map should equal(List(
        List(Stone(true, 0, Option(Player(0, "B"))), Stone(false, -1, None)),
        List(Stone(true, 1, None), Stone(false, -1, None)))
      )
    }

    "can startup" in {
      controller.startup(1).toString should equal(
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
        "| G1 |      | G2 |      |    ||    ||    |      |    |      |    |" + eol +
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
        "                        +----++----++----+                        " + eol +
        "                        |    ||    ||    |                        " + eol +
        "                        +----++----++----+                        " + eol +
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
        "| G3 |      | G4 |      |    ||    ||    |      |    |      |    |" + eol +
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
        "                        +----++----++----+                        " + eol +
        "                        |    ||    ||    |                        " + eol +
        "                        +----++----++----+                        " + eol +
        "+----++----++----++----++----++----++----++----++----++----++----+" + eol +
        "|    ||    ||    ||    ||    ||    ||    ||    ||    ||    ||    |" + eol +
        "+----++----++----++----++----++----++----++----++----++----++----+" + eol +
        "+----++----++----++----++----+      +----++----++----++----++----+" + eol +
        "|    ||    ||    ||    ||    |      |    ||    ||    ||    ||    |" + eol +
        "+----++----++----++----++----+      +----++----++----++----++----+" + eol +
        "+----++----++----++----++----++----++----++----++----++----++----+" + eol +
        "|    ||    ||    ||    ||    ||    ||    ||    ||    ||    ||    |" + eol +
        "+----++----++----++----++----++----++----++----++----++----++----+" + eol +
        "                        +----++----++----+                        " + eol +
        "                        |    ||    ||    |                        " + eol +
        "                        +----++----++----+                        " + eol +
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
        "|    |      |    |      |    ||    ||    |      |    |      |    |" + eol +
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
        "                        +----++----++----+                        " + eol +
        "                        |    ||    ||    |                        " + eol +
        "                        +----++----++----+                        " + eol +
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
        "|    |      |    |      |    ||    ||    |      |    |      |    |" + eol +
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol
      )
    }

  }
}
