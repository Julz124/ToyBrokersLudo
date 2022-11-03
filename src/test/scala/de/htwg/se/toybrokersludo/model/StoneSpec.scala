package de.htwg.se.toybrokersludo.model

import de.htwg.se.toybrokersludo.{Player, Stone}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class StoneSpec extends AnyWordSpec with Matchers {

  "move" should {

    "have a Bool, a Index and Player" in {
      val stone = Stone(true, 0, Player(1,"B"))
      stone.isAPlayField should be (true)
      stone.index should be (1)
      stone.player should be (Player(1,"B"))
    }

  }

}
