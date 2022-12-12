package de.htwg.se.toybrokersludo.model

import de.htwg.se.toybrokersludo.controller.ControllerInterface
import de.htwg.se.toybrokersludo.model.MatrixBaseImpl.Matrix
import de.htwg.se.toybrokersludo.model.FieldBaseImpl.Field
import de.htwg.se.toybrokersludo.controller.controllerBaseImpl.Controller
import de.htwg.se.toybrokersludo.model.{FieldInterface, MatrixInterface}
import de.htwg.se.toybrokersludo.model.PlayerBaseImpl.{BluePlayer, GreenPlayer, RedPlayer, YellowPlayer}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.should

class FieldInterfaceSpec extends AnyWordSpec with Matchers {

  val matrix = Matrix()

  val field = Field(matrix)

  val controller = Controller(field)

  val eol = "\n"

  "field" should {

    "can hase next player with 1 player" in {
      val field1 = Field(matrix,GreenPlayer,1)

      field1.nextPlayer() should be(Field(Matrix(),GreenPlayer,1))
      field1.nextPlayer() should be(Field(Matrix(),GreenPlayer,1))
      field1.nextPlayer() should be(Field(Matrix(),GreenPlayer,1))
      field1.nextPlayer() should be(Field(Matrix(),GreenPlayer,1))
    }

    "can hase next player with 2 player" in {
      val field2 = Field(Matrix(), GreenPlayer, 2)

      field2.nextPlayer() should be(Field(Matrix(),RedPlayer,2))
      field2.nextPlayer() should be(Field(Matrix(),GreenPlayer,2))
      field2.nextPlayer() should be(Field(Matrix(),GreenPlayer,2))
      field2.nextPlayer() should be(Field(Matrix(),GreenPlayer,2))
    }

    "can hase next player with 3 player" in {
      val field3 = Field(Matrix(), GreenPlayer, 3)

      field3.nextPlayer() should be(Field(Matrix(), RedPlayer, 3))
      field3.nextPlayer() should be(Field(Matrix(), BluePlayer, 3))
      field3.nextPlayer() should be(Field(Matrix(), GreenPlayer, 3))
      field3.nextPlayer() should be(Field(Matrix(), GreenPlayer, 3))
    }

    "can hase next player with 4 player" in {
      val field4 = Field(Matrix(), GreenPlayer, 4)

      field4.nextPlayer() should be(Field(Matrix(), RedPlayer, 4))
      field4.nextPlayer() should be(Field(Matrix(), BluePlayer, 4))
      field4.nextPlayer() should be(Field(Matrix(), YellowPlayer, 4))
      field4.nextPlayer() should be(Field(Matrix(), GreenPlayer, 4))
    }


    "can convert a map to String" in {
      field.toString() should be(
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "|    |      |    |      |    ||    ||    |      |    |      |    |" + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "                        +----++----++----+                        " + eol +
          "                        |    ||    ||    |                        " + eol +
          "                        +----++----++----+                        " + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "|    |      |    |      |    ||    ||    |      |    |      |    |" + eol +
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
          "Green Player true 6" + eol
      )
    }

    "can insert player into rantom field" in {
      field.put(Move(PlayToken.apply(1, "B"), 20)) should be(
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "|    |      |    |      |    ||    ||    |      |    |      |    |" + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "                        +----++----++----+                        " + eol +
          "                        |    ||    ||    |                        " + eol +
          "                        +----++----++----+                        " + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "|    |      |    |      |    ||    ||    |      |    |      |    |" + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "                        +----++----++----+                        " + eol +
          "                        |    ||    ||    |                        " + eol +
          "                        +----++----++----+                        " + eol +
          "+----++----++----++----++----++----++----++----++----++----++----+" + eol +
          "| B1 ||    ||    ||    ||    ||    ||    ||    ||    ||    ||    |" + eol +
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
          "Green Player true 6" + eol
      )
    }

    "can invert dice" in {
      field.invertDice().getShouldDice should be (false)
    }

    "can dice" in {
      field.getDice should be (6)
    }
  }
}














