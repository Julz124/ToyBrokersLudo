package de.htwg.se.toybrokersludo.model

import de.htwg.se.toybrokersludo.model.Move
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec



class MatrixSpec extends AnyWordSpec with Matchers{

  val matrix = Matrix(List(
    List(Stone(true, 0, None), Stone(false, -1, None)),
    List(Stone(true, 1, None), Stone(false, -1, None))))

  "matrix" should be {
    "build Matrix correctly" in
      matrix.put(Move(Player(1,"B"), 0)) should be (List(
        List(Stone(true, 0, Option(Player(1, "B"))), Stone(false, -1, None)),
        List(Stone(true, 1, None), Stone(false, -1, None))))
      matrix.put(Move(Player(2, "Y"), 1)) should be (List(
        List(Stone(true, 0, None), Stone(false, -1, None)),
        List(Stone(true, 1, Option(Player(2, "Y"))), Stone(false, -1, None))))
  }
}
