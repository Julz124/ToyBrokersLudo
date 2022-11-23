package de.htwg.se.toybrokersludo.model

import de.htwg.se.toybrokersludo.model.Player
import de.htwg.se.toybrokersludo.model.Stone

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class StoneSpec extends AnyWordSpec with Matchers {

  "move" should {
    "have a Bool, a Index and Player" in {
      val stone = Stone(true, 0, Option(Player(1,"B")))
      stone.isAPlayField should be (true)
      stone.index should be (0)
      stone.player should equal (Some(Player(1,"B")))
    }
  }
}
