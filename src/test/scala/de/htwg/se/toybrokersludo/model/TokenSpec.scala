package de.htwg.se.toybrokersludo.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TokenSpec extends AnyWordSpec with Matchers {

  "token" should {
    "have a number and color" in {
      val player = PlayToken(1,"Y")
      player.getNumber() should be (1)
      player.getColor() should be ("Y")
    }

    "get's a Green Token" in {
      PlayToken.apply(1, "G").toString should be ("G1")
      PlayToken.apply(2, "G").toString should be ("G2")
      PlayToken.apply(3, "G").toString should be ("G3")
      PlayToken.apply(4, "G").toString should be ("G4")
    }
    "get's a Red Token" in {
      PlayToken.apply(1, "R").toString should be("R1")
      PlayToken.apply(2, "R").toString should be("R2")
      PlayToken.apply(3, "R").toString should be("R3")
      PlayToken.apply(4, "R").toString should be("R4")
    }
    "get's a Blue Token" in {
      PlayToken.apply(1, "B").toString should be("B1")
      PlayToken.apply(2, "B").toString should be("B2")
      PlayToken.apply(3, "B").toString should be("B3")
      PlayToken.apply(4, "B").toString should be("B4")
    }
    "get's a Yellow Token" in {
      PlayToken.apply(1, "Y").toString should be("Y1")
      PlayToken.apply(2, "Y").toString should be("Y2")
      PlayToken.apply(3, "Y").toString should be("Y3")
      PlayToken.apply(4, "Y").toString should be("Y4")
    }

    "can convert Color and Number to String" in {
      val player = PlayToken.apply(1,"B")
      player.toString() should equal("B1")
    }
  }
}
