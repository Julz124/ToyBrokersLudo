package de.htwg.se.toybrokersludo.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.toybrokersludo.model.field

class field_test extends WordSpec with Matchers{
  val field = new field
  val eol = "\n"

  "have scale horizontal" in {
    field.horizontal(List[true, false, true], 2) should ("+--+    +--+")
    field.horizontal(List[true, false, true], 4) should ("+----+      +----+")
    field.horizontal(List[true, false, true], 6) should ("+------+        +------+")
  }

  "have scale vertical" in {
    field.vertical(List[true, false, true], 2) should ("|  |    |  |")
    field.vertical(List[true, false, true], 4) should ("|    |      |    |")
    field.vertical(List[true, false, true], 6) should ("|      |        |      |")
  }

  "can merge the lines" in {
    field.mash(List(List(true, false), List(false, true)), 4) should (
      List(
        "+----+      ",
        "|    |      ",
        "+----+      ",
        "      +----+",
        "      |    |",
        "      +----+")
  }

  "can convert to String" in {
    field.toString(field.mash(List(List(true, false), List(false, true))) should (
      "+----+      " + eol +
      "|    |      " + eol +
      "+----+      " + eol +
      "      +----+" + eol +
      "      |    |" + eol +
      "      +----+" + eol)
  }
}
