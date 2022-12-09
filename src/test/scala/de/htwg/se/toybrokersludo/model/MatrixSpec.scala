package de.htwg.se.toybrokersludo.model

import de.htwg.se.toybrokersludo.model.{Matrix,PlayToken,Move}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class MatrixSpec extends AnyWordSpec with Matchers{

  val matrix = Matrix(List(
    List(Stone(true, 0, Option(PlayToken(1, "B"))), Stone(false, -1, None)),
    List(Stone(true, 1, None), Stone(false, -1, None))))

  "matrix" should {
    "build Matrix correctly" in {
      matrix.put(Move(PlayToken.apply(2, "Y"),0)).toString should equal (
        Matrix(List(
        List(Stone(true, 0, Option(PlayToken(2, "Y"))), Stone(false, -1, None)),
        List(Stone(true, 1, None), Stone(false, -1, None)))).toString
      )
    }

    "get's Token correctly" in {
      matrix.getToken.toString() should equal ("List(Move(B1,0))")
    }

  }
}
