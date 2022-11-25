package de.htwg.se.toybrokersludo.model

import de.htwg.se.toybrokersludo.model.Move
import de.htwg.se.toybrokersludo.model.{Matrix}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class MatrixSpec extends AnyWordSpec with Matchers{

  val matrix = Matrix(List(
    List(Stone(true, 0, None), Stone(false, -1, None)),
    List(Stone(true, 1, Option(PlayToken(2, "B"))), Stone(false, -1, None))))

  "matrix" should {
    "build Matrix correctly" in {
      matrix.put(Move(PlayToken(1, "B"), 0)).map == (List(
        List(Stone(true, 0, Option(PlayToken(1, "B"))), Stone(false, -1, None)),
        List(Stone(true, 1, Option(PlayToken(1, "B"))), Stone(false, -1, None))))
      matrix.put(Move(PlayToken(1, "B"), 0)).map == (List(
        List(Stone(true, 0, None), Stone(false, -1, None)),
        List(Stone(true, 1, Option(PlayToken(2, "B"))), Stone(false, -1, None))))
    }
  }


}
