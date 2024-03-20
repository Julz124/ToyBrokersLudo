package de.htwg.se.toybrokersludo.model

import de.htwg.se.toybrokersludo.util.{Command, UndoManager}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TokenSpec extends AnyWordSpec with Matchers  {
  "The Token" should {

    "return playerHouseIndex" in {
      val green = Token(Player.Green, 1)
      val red = Token(Player.Red, 1)
      val blue = Token(Player.Blue, 1)
      val yellow = Token(Player.Yellow, 1)

      green.playerHouseIndex should be (0)
      red.playerHouseIndex should be (4)
      blue.playerHouseIndex should be (12)
      yellow.playerHouseIndex should be (8)
    }
  }
}
