package de.htwg.se.toybrokersludo.model

import de.htwg.se.toybrokersludo.controller.controllerBaseImpl
import de.htwg.se.toybrokersludo.controller.controllerBaseImpl.Controller
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.should

class FieldSpec extends AnyWordSpec with Matchers {

  val field = Field(Matrix())

  val controller = controllerBaseImpl.Controller(field)

  val eol = "\n"

  "field" should {
    "have scale horizontal" in {
      field.horizontal(List(Stone(true, 0, None), Stone(false, -1, None), Stone(true, 1, None), Stone(false, -1, None)), 2) should be(List("+--+", "    ", "+--+", "    "))
      field.horizontal(List(Stone(false, -1, None), Stone(true, 0, None), Stone(false, -1, None), Stone(true, 1, None)), 4) should be(List("      ", "+----+", "      ", "+----+"))
      field.horizontal(List(Stone(true, 0, None), Stone(true, 1, None), Stone(false, -1, None), Stone(false, -1, None)), 6) should be(List("+------+", "+------+", "        ", "        "))
    }
    "have scale vertical" in {
      field.vertical(List(Stone(true, 0, None), Stone(false, -1, None), Stone(true, 1, None), Stone(false, -1, None)), 2) should be(List("|  |", "    ", "|  |", "    "))
      field.vertical(List(Stone(false, -1, None), Stone(true, 0, None), Stone(false, -1, None), Stone(true, 1, None)), 4) should be(List("      ", "|    |", "      ", "|    |"))
      field.vertical(List(Stone(true, 0, None), Stone(true, 1, None), Stone(false, -1, None), Stone(false, -1, None)), 6) should be(List("|      |", "|      |", "        ", "        "))
    }

    "can merge the lines" in {
      field.mash(List(
        List(Stone(true, 0, None), Stone(false, -1, None)),
        List(Stone(false, -1, None), Stone(true, 1, None))), 4) should be(
        List(
          "+----+      \n|    |      \n+----+      \n",
          "      +----+\n      |    |\n      +----+\n"))
      field.mash(List(
        List(Stone(false, -1, None), Stone(true, 0, None)),
        List(Stone(true, 1, None), Stone(false, -1, None))), 6) should be(
        List(
          "        +------+\n        |      |\n        +------+\n",
          "+------+        \n|      |        \n+------+        \n"))
    }

    "can insert player into field" in {
      field.player(2, Option(PlayToken(1, "B"))) should be("B1")
      field.player(4, Option(PlayToken(2, "R"))) should be(" R2 ")
      field.player(6, Option(PlayToken(3, "Y"))) should be("  Y3  ")
      field.player(8, Option(PlayToken(4, "G"))) should be("   G4   ")
    }

    "can hase next player with 1 player" in {
      val field1 = Field(Matrix(),GreenPlayer,1)

      field1.nextPlayer(GreenPlayer) should be(Field(Matrix(),GreenPlayer,1))
      field1.nextPlayer(RedPlayer) should be(Field(Matrix(),GreenPlayer,1))
      field1.nextPlayer(BluePlayer) should be(Field(Matrix(),GreenPlayer,1))
      field1.nextPlayer(YellowPlayer) should be(Field(Matrix(),GreenPlayer,1))
    }

    "can hase next player with 2 player" in {
      val field2 = Field(Matrix(), GreenPlayer, 2)

      field2.nextPlayer(GreenPlayer) should be(Field(Matrix(),RedPlayer,2))
      field2.nextPlayer(RedPlayer) should be(Field(Matrix(),GreenPlayer,2))
      field2.nextPlayer(BluePlayer) should be(Field(Matrix(),GreenPlayer,2))
      field2.nextPlayer(YellowPlayer) should be(Field(Matrix(),GreenPlayer,2))
    }

    "can hase next player with 3 player" in {
      val field3 = Field(Matrix(), GreenPlayer, 3)

      field3.nextPlayer(GreenPlayer) should be(Field(Matrix(), RedPlayer, 3))
      field3.nextPlayer(RedPlayer) should be(Field(Matrix(), BluePlayer, 3))
      field3.nextPlayer(BluePlayer) should be(Field(Matrix(), GreenPlayer, 3))
      field3.nextPlayer(YellowPlayer) should be(Field(Matrix(), GreenPlayer, 3))
    }

    "can hase next player with 4 player" in {
      val field4 = Field(Matrix(), GreenPlayer, 4)

      field4.nextPlayer(GreenPlayer) should be(Field(Matrix(), RedPlayer, 4))
      field4.nextPlayer(RedPlayer) should be(Field(Matrix(), BluePlayer, 4))
      field4.nextPlayer(BluePlayer) should be(Field(Matrix(), YellowPlayer, 4))
      field4.nextPlayer(YellowPlayer) should be(Field(Matrix(), GreenPlayer, 4))

      //field4.nextPlayer(BluePlayer) should be(Field(Matrix(), YellowPlayer, 4))
      //field4.nextPlayer(YellowPlayer) should be(Field(Matrix(), GreenPlayer, 4))
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
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol
      )
    }

    "can insert player into rantom field" in {
      field.put(Move(PlayToken.apply(1, "B"), 20)).toString should be(
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
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol
      )
    }

    "can invert dice" in {
      field.invertDice().shouldDice should be (false)
    }

    "can dice" in {
      field.dice should be (6)
    }
  }
}














