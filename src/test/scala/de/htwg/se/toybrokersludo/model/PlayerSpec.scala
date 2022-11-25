package de.htwg.se.toybrokersludo.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PlayerSpec extends AnyWordSpec with Matchers{

  val green = GreenPlayer
  val red = RedPlayer
  val blue = BluePlayer
  val yellow = YellowPlayer

  "player" should {
    "green should have" in {
      green.playerString should be ("G")
      green.endFields() should equal (List(70, 71, 72, 73))
      green.startField() should be (20)
      green.defaultField() should equal (List(0, 1, 2, 3))
    }
    "yellow should have" in {
      yellow.playerString should be ("Y")
      yellow.endFields() should equal (List(82, 83, 84, 85))
      yellow.startField() should be (50)
      yellow.defaultField() should equal (List(8, 9, 10, 11))
    }
    "red should have" in {
      red.playerString should be ("R")
      red.endFields() should equal (List(74, 75, 76, 77))
      red.startField() should be (30)
      red.defaultField() should equal (List(4, 5, 6, 7))
    }
    "blue should have" in {
      blue.playerString should be ("B")
      blue.endFields() should equal (List(78, 79, 80, 81))
      blue.startField() should be (40)
      blue.defaultField() should equal (List(12, 13, 14, 15))
    }
  }

}
