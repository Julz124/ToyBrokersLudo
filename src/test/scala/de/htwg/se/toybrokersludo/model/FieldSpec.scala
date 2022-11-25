package de.htwg.se.toybrokersludo.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.should

class FieldSpec extends AnyWordSpec with Matchers {

  val field = Field(Matrix())

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
      field.player(2, Option(PlayToken(0, "B"))) should be("B0")
      field.player(4, Option(PlayToken(1, "R"))) should be(" R1 ")
      field.player(6, Option(PlayToken(2, "Y"))) should be("  Y2  ")
      field.player(8, Option(PlayToken(3, "G"))) should be("   G3   ")
    }

    "can hase next player" in {
      field.nextPlayer(GreenPlayer, 1) should equal (GreenPlayer)
      field.nextPlayer(GreenPlayer, 2) should equal (RedPlayer)
      field.nextPlayer(RedPlayer, 2) should equal (RedPlayer)
      field.nextPlayer(RedPlayer, 3) should equal (BluePlayer)
      field.nextPlayer(BluePlayer, 3) should equal (BluePlayer)
      field.nextPlayer(BluePlayer, 4) should equal (YellowPlayer)
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
  }
}














