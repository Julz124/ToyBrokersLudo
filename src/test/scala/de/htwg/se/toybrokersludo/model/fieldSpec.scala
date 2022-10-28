package de.htwg.se.toybrokersludo.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.toybrokersludo.model.field
import org.scalatest.matchers.should.Matchers.should

class fieldSpec extends AnyWordSpec with Matchers {
  val field = new field
  val eol = "\n"

  "field" should {
    "have scale horizontal" in
      field.horizontal(List(true, false, true), 2) == (List("+--+", "    ", "+--+"))
    field.horizontal(List(true, false, true), 4) == (List("+----+", "      ", "+----+"))
    field.horizontal(List(true, false, true), 6) == (List("+------+", "        ", "+------+"))

    "have scale vertical" in
      field.vertical(List(true, false, true), 2) == (List("|  |", "    ", "|  |"))
    field.vertical(List(true, false, true), 4) == (List("|    |", "      ", "|    |"))
    field.vertical(List(true, false, true), 6) == (List("|      |", "        ", "|      |"))


    "can merge the lines" in field.mash(List(List(true, false), List(false, true)), 4) == (
      List(
        "+----+      ",
        "|    |      ",
        "+----+      ",
        "      +----+",
        "      |    |",
        "      +----+")
      )

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
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
        "|    |      |    |      |    ||    ||    |      |    |      |    |" + eol +
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
        "+----+      +----+      +----+      +----+      +----+      +----+" + eol +
        "|    |      |    |      |    |      |    |      |    |      |    |" + eol +
        "+----+      +----+      +----+      +----+      +----+      +----+" + eol +
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
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
        "|    |      |    |      |    ||    ||    |      |    |      |    |" + eol +
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol
    )
  }
}












