package de.htwg.se.toybrokersludo.model

import de.htwg.se.toybrokersludo.{Field, Matrix, Move, Player, Stone}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.should

class FieldSpec extends AnyWordSpec with Matchers {

  val field = Field(Matrix())

  val eol = "\n"

  "field" should {
    "have scale horizontal" in
      field.horizontal(List(Stone(true, 0, None), Stone(false, -1, None), Stone(true, 1, None), Stone(false, -1, None)), 2) == (List("+--+","    ","+--+","    "))
    field.horizontal(List(Stone(false, -1, None), Stone(true, 0, None), Stone(false, -1, None), Stone(true, 1, None)), 4) == (List("      ","+----+","      ","+----+"))
    field.horizontal(List(Stone(true, 0, None), Stone(true, 1, None), Stone(false, -1, None), Stone(false, -1, None)), 6) == (List("+------+","+------+","        ","        "))

    "have scale vertical" in
      field.vertical(List(Stone(true, 0, None), Stone(false, -1, None), Stone(true, 1, None), Stone(false, -1, None)), 2) == (List("|  |","    ","|  |","    "))
    field.vertical(List(Stone(false, -1, None), Stone(true, 0, None), Stone(false, -1, None), Stone(true, 1, None)), 4) == (List("      ","|    |","      ","|    |"))
    field.vertical(List(Stone(true, 0, None), Stone(true, 1, None), Stone(false, -1, None), Stone(false, -1, None)), 6) == (List("|      |", "|      |", "        ", "        "))


    "can merge the lines" in
      field.mash(List(
        List(Stone(true, 0, None), Stone(false, -1, None)),
        List(Stone(false, -1, None), Stone(true, 1, None))), 4) == (
        List(
          "+----+      ",
          "|    |      ",
          "+----+      ",
          "      +----+",
          "      |    |",
          "      +----+"))
    field.mash(List(
      List(Stone(false, -1, None), Stone(true, 0, None)),
      List(Stone(true, 1, None), Stone(false, -1, None))), 6) == (
      List(
        "        +------+",
        "        |      |",
        "        +------+",
        "+------+        ",
        "|      |        ",
        "+------+        "))

    "can insert player into field" in
      field.player(2,Option(Player(1,"B"))) == "B1"
    field.player(4,Option(Player(2,"R"))) == " R2 "
    field.player(6,Option(Player(3,"Y"))) == "  Y3  "
    field.player(8,Option(Player(4,"G"))) == "   G4   "

    "can insert player into rantom field" in
      field.put(Move(Player(1,"B"), 20)).toString ==
        "+----+" + eol +
          "| B1 |" + eol +
          "+----+" + eol

    "can convert a map to String" in
      field.toString() == (
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
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol)
  }
}














