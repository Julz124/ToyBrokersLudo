package de.htwg.se.toybrokersludo.model

import de.htwg.se.toybrokersludo.controller.ControllerInterface
import de.htwg.se.toybrokersludo.model.MatrixBaseImpl.Matrix
import de.htwg.se.toybrokersludo.model.FieldBaseImpl.Field
import de.htwg.se.toybrokersludo.controller.controllerBaseImpl.Controller
import de.htwg.se.toybrokersludo.model.{FieldInterface, MatrixInterface}
import de.htwg.se.toybrokersludo.model.PlayerBaseImpl.{BluePlayer, GreenPlayer, RedPlayer, YellowPlayer}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.should

class MatrixInterfaceSpec extends AnyWordSpec with Matchers {

  val map: List[List[Stone]] = List(
    List(Stone(true, 0, None), Stone(false, -1, None)),
    List(Stone(true, 1, None), Stone(false, -1, None)))

  val matrix: MatrixInterface = Matrix(map)
  val field: FieldInterface = Field(matrix)
  val controller: ControllerInterface = Controller(field)

  val eol = "\n"

  "matrix" should {

    "can put" in {
      val matrix_p: MatrixInterface = Matrix(map)
      matrix_p.put(Move(PlayToken(2, "Y"), 0)).toString should equal(
        Matrix(List(
          List(Stone(true, 0, Option(PlayToken(2, "Y"))), Stone(false, -1, None)),
          List(Stone(true, 1, None), Stone(false, -1, None)))).toString
      )
    }

    /*
    "can move" in {
      val matrix_m: MatrixInterface = Matrix(map)
      matrix_m.move(Move(PlayToken(2, "Y"), 0)).toString should equal(
        Matrix(List(
          List(Stone(true, 0, Option(PlayToken(2, "Y"))), Stone(false, -1, None)),
          List(Stone(true, 1, None), Stone(false, -1, None)))).toString
      )
    }
    */

    "can get Token" in {
      val matrix_gT: MatrixInterface = Matrix(map)
      matrix_gT.getToken should be (List())
    }

    "can get Stone" in {
      val matrix_gS: MatrixInterface = Matrix(map)
      matrix_gS.getStone(0) should be(Stone(true, 0, None))
    }

    "can get Map" in {
      val matrix_gM: MatrixInterface = Matrix(map)
      matrix_gM.getMap should be (
        List(
          List(Stone(true, 0, None), Stone(false, -1, None)),
          List(Stone(true, 1, None), Stone(false, -1, None)))
      )
    }

  }
}