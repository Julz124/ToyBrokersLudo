package de.htwg.se.toybrokersludo.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MoveSpec extends AnyWordSpec with Matchers {
  
  "move" should {

    "have a Player and number" in {
      val move = Move(Token(1,"B"), 0)
      move.player should be(Token(1,"B"))
      move.number should be(0)
    }
  }
}
