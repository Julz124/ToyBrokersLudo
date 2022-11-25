package de.htwg.se.toybrokersludo.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MoveSpec extends AnyWordSpec with Matchers {
  
  "move" should {

    "have a Player and number" in {
      val move = Move(PlayToken(1,"B"), 1)
      move.player == (PlayToken(1,"B"))
      move.number should be(1)
    }
  }
}
