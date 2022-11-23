package de.htwg.se.toybrokersludo.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PlayerSpec extends AnyWordSpec with Matchers {

  "player" should {
    "have a number and color" in {
      val player = Player(0,"Y")
      player.number should be (0)
      player.color should be ("Y")
    }

    "can convert Color and Number to String" in {
      val player = Player(1,"B")
      player.toString() should equal("B1")
    }
  }
}
