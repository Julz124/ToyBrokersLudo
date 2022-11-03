package de.htwg.se.toybrokersludo.model.model

import de.htwg.se.toybrokersludo.model.Matrix
import de.htwg.se.toybrokersludo.model.Stone
import de.htwg.se.toybrokersludo.model.Move
import de.htwg.se.toybrokersludo.model.Player
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec



class MatrixSpec extends AnyWordSpec with Matchers{

  val matrix = Matrix(List(
    List(Stone(true, 0, None), Stone(false, -1, None)),
    List(Stone(true, 1, None), Stone(false, -1, None))))

  "matrix" should {

    "build Matrix correctly" in
      matrix.put(Move(Player(1,"B"), 0) == List(
        List(Stone(true, 0, move.player), Stone(false, -1, None)),
        List(Stone(true, 1, None), Stone(false, -1, None))))
      matrix.put(Move(Player(2,"Y"), 1) == List(
        List(Stone(true, 0, None), Stone(false, -1, None)),
        List(Stone(true, 1, move.player), Stone(false, -1, None))))
  }
}
