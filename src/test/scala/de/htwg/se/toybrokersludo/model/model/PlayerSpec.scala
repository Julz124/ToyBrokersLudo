package de.htwg.se.toybrokersludo.model.model

import de.htwg.se.toybrokersludo.model.Player
import de.htwg.se.toybrokersludo.model.Stone
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PlayerSpec extends AnyWordSpec with Matchers {

  "player" should {

    "have a number and color" in {
      val player = Player(0,"Y")
      player.number should be (0)
      player.color should be ("Y")
    }

  }

}
