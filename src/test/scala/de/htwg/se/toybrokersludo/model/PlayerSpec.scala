package de.htwg.se.toybrokersludo.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PlayerSpec extends AnyWordSpec with Matchers{

  val green = GreenPlayer
  val red = RedPlayer
  val blue = BluePlayer
  val yellow = YellowPlayer

  "player" should {

    "class Player should return possibleMoves" in {
      var field = Field(Matrix())
      def startRed(): List[Move] = List(Move(PlayToken.apply(1, "R"), 4), Move(PlayToken.apply(2, "R"), 5), Move(PlayToken.apply(3, "R"), 6), Move(PlayToken.apply(4, "R"), 7))
      for (move <- startRed()) field = field.put(move)
      RedPlayer.possibleMoves(6, field) ===
        (List(Move(PlayToken(1, "R"),30), Move(PlayToken(2, "R"),30), Move(PlayToken(3, "R"),30), Move(PlayToken(4, "R"),30)))
    }

    "can calculate the next field" in {
      GreenPlayer.add(58, 2) should equal(Some(70))
      GreenPlayer.add(58, 5) should equal(Some(73))
      RedPlayer.add(58,3) should equal(Some(21))
      RedPlayer.add(35,5) should equal(Some(40))
    }

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
