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

    "get's a Token" in {
      PlayToken.apply(1, "Y").toString should be ("Y1")
    }

    "can convert Color and Number to String" in {
      val player = PlayToken.apply(1,"B")
      player.toString() should equal("B1")
    }
  }
}
