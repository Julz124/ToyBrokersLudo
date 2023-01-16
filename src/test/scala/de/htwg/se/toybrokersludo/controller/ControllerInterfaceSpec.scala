package de.htwg.se.toybrokersludo.controller


import de.htwg.se.toybrokersludo.model.{FieldInterface, Move, PlayToken, Stone}
import de.htwg.se.toybrokersludo.model.FieldBaseImpl.{Field, Matrix}
import de.htwg.se.toybrokersludo.controller.controllerBaseImpl.Controller
import de.htwg.se.toybrokersludo.model.FileIO.FileIOInterface
import de.htwg.se.toybrokersludo.model.FileIO.JsonImpl.FileIo
import de.htwg.se.toybrokersludo.util.PlayerBaseImpl.GreenPlayer
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.Unit

class ControllerInterfaceSpec extends AnyWordSpec with Matchers {

  val eol = "\n"

  val map: List[List[Stone]] = List(
    List(
      Stone(true, 0, None), Stone(false, -1, None)),
    List(
      Stone(true, 1, None), Stone(false, -1, None)
    ))

  val matrix : Matrix = Matrix(map)
  val field : FieldInterface = Field(matrix)
  val fileIO : FileIOInterface = FileIo()
  val controller : ControllerInterface= Controller(using field)(using fileIO)

  "The Controller" should  {
    "get should dice" in {
      controller.getShouldDice should be (true)
    }

    "get player" in {
      controller.getPlayer should be (GreenPlayer)
    }

    "get dice" in {
      controller.getDice should be (6)
    }

    "get matrix" in {
      controller.getMatrix should be (matrix)
    }

    "get field" in {
      controller.getField should be (field)
    }

    "can startup player count 1" in {
      val field2 : FieldInterface = Field(Matrix())
      val controller2 : ControllerInterface = Controller(using field2)(using fileIO)
      controller2.startup(1)
      controller2.getField.toString should be(
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
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "Green Player have to dice"
      )
    }

    "can startup player count 2" in {
      val field2: FieldInterface = Field(Matrix())
      val controller2: ControllerInterface = Controller(using field2)(using fileIO)
      controller2.startup(2)
      controller2.getField.toString should be(
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "| G1 |      | G2 |      |    ||    ||    |      | R1 |      | R2 |" + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "                        +----++----++----+                        " + eol +
          "                        |    ||    ||    |                        " + eol +
          "                        +----++----++----+                        " + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "| G3 |      | G4 |      |    ||    ||    |      | R3 |      | R4 |" + eol +
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
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "Green Player have to dice"
      )
    }
    "can startup player count 3" in {
      val field2: FieldInterface = Field(Matrix())
      val controller2: ControllerInterface = Controller(using field2)(using fileIO)
      controller2.startup(3)
      controller2.getField.toString should be(
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "| G1 |      | G2 |      |    ||    ||    |      | R1 |      | R2 |" + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "                        +----++----++----+                        " + eol +
          "                        |    ||    ||    |                        " + eol +
          "                        +----++----++----+                        " + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "| G3 |      | G4 |      |    ||    ||    |      | R3 |      | R4 |" + eol +
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
          "|    |      |    |      |    ||    ||    |      | B1 |      | B2 |" + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "                        +----++----++----+                        " + eol +
          "                        |    ||    ||    |                        " + eol +
          "                        +----++----++----+                        " + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "|    |      |    |      |    ||    ||    |      | B3 |      | B4 |" + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "Green Player have to dice"
      )
    }
    "can startup player count 4" in {
      val field2: FieldInterface = Field(Matrix())
      val controller2: ControllerInterface = Controller(using field2)(using fileIO)
      controller2.startup(4)
      controller2.getField.toString should be(
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "| G1 |      | G2 |      |    ||    ||    |      | R1 |      | R2 |" + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "                        +----++----++----+                        " + eol +
          "                        |    ||    ||    |                        " + eol +
          "                        +----++----++----+                        " + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "| G3 |      | G4 |      |    ||    ||    |      | R3 |      | R4 |" + eol +
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
          "| Y1 |      | Y2 |      |    ||    ||    |      | B1 |      | B2 |" + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "                        +----++----++----+                        " + eol +
          "                        |    ||    ||    |                        " + eol +
          "                        +----++----++----+                        " + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "| Y3 |      | Y4 |      |    ||    ||    |      | B3 |      | B4 |" + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "Green Player have to dice"
      )
    }

    "can dice" in {
      controller.doAndPublish(controller.dice)
      (1 to 6).contains(controller.getField.getDice)
    }

    "invert should dice" in {
      controller.getField.getShouldDice should be (true)
    }

    "get possibleMoves" in {
      val controller2 = Controller(using Field())(using fileIO)
      controller2.startup(1)
      controller2.getPossibleMoves(6) === (
        (List(Move(PlayToken(1, "G"),20), Move(PlayToken(2, "G"),20), Move(PlayToken(3, "G"),20), Move(PlayToken(4, "G"),20)))
      )
    }


    "can publish_1" in {
      val move = Move(PlayToken.apply(1, "B"), 1)
      controller.doAndPublish(controller.put, move).toString should equal("()")
    }

    "can publish_2" in {
      controller.doAndPublish(controller.undo).toString should equal("()")
    }

    "have fuc put" in {
      val controller2 : ControllerInterface = Controller(using Field(Matrix()))(using fileIO)
      val controller3 : ControllerInterface = Controller(using Field(Matrix()))(using fileIO)
      controller2.startup(1)
      controller3.startup(1)
      val field2 : FieldInterface = controller3.getField
      controller2.doAndPublish(controller2.put, Move(PlayToken(1, "G"), 20))
      controller2.getField === (field2.put(Move(PlayToken(1, "G"), 20)))
    }

    "have fuc move" in {
      val controller2: ControllerInterface = Controller(using Field(Matrix()))(using fileIO)
      val controller3: ControllerInterface = Controller(using Field(Matrix()))(using fileIO)
      controller2.startup(1)
      controller3.startup(1)
      val field2: FieldInterface = controller3.getField
      controller2.doAndPublish(controller2.move, Move(PlayToken(1, "G"), 20))
      controller2.getField === (field2.move(Move(PlayToken(1, "G"), 20)))
    }

    "have fuc undo" in {
      val controller2: ControllerInterface = Controller(using Field(Matrix()))(using fileIO)
      controller2.startup(1)
      controller2.doAndPublish(controller2.move, Move(PlayToken(1, "G"), 20))
      controller2.doAndPublish(controller2.undo)
      controller2.getField.toString should be(
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
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "Green Player have to dice"
        )
    }

    "have fuc redo" in {
      val controller2: ControllerInterface = Controller(using Field(Matrix()))(using fileIO)
      controller2.startup(1)
      controller2.doAndPublish(controller2.move, Move(PlayToken(1, "G"), 20))
      controller2.doAndPublish(controller2.undo)
      controller2.doAndPublish(controller2.redo)
      controller2.getField.toString should be(
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "|    |      | G2 |      |    ||    ||    |      |    |      |    |" + eol +
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
          "| G1 ||    ||    ||    ||    ||    ||    ||    ||    ||    ||    |" + eol +
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
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "Green Player have to dice"
      )
    }

      "have fuc redo for dice" in {
        val controller2: ControllerInterface = Controller(using Field(Matrix()))(using fileIO)
        controller2.startup(1)
        controller2.doAndPublish(controller.dice)
        while (!controller2.getShouldDice) {
          controller2.doAndPublish(controller.dice)
        }
        controller2.doAndPublish(controller2.undo)
        controller2.doAndPublish(controller2.redo)
        controller2.getField.toString should equal(
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
            "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
            "Green Player have to dice"
        )
    }


    "can save and load" in {
      controller.save("SpecTestController")
      controller.load("SpecTestController").toString should equal ("()")
    }

    "can getTargets" in {
      controller.getTargets() should contain ("SpecTestController")
    }

  }


}
