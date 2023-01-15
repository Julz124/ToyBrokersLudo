package de.htwg.se.toybrokersludo.model

import de.htwg.se.toybrokersludo.controller.ControllerInterface
import de.htwg.se.toybrokersludo.controller.controllerBaseImpl.Controller
import de.htwg.se.toybrokersludo.model.FieldInterface
import de.htwg.se.toybrokersludo.model.FieldBaseImpl.{Field, Matrix}
import de.htwg.se.toybrokersludo.model.FileIO.FileIOInterface
import de.htwg.se.toybrokersludo.model.FileIO.JsonImpl.FileIo
import de.htwg.se.toybrokersludo.util.PlayerBaseImpl.{BluePlayer, GreenPlayer, RedPlayer, YellowPlayer}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.should

class FieldInterfaceSpec extends AnyWordSpec with Matchers {

  val matrix: Matrix = Matrix()
  val field: FieldInterface = Field(matrix)
  val fileIO : FileIOInterface = FileIo()
  val controller: ControllerInterface = Controller(using field)(using fileIO)

  val eol = "\n"

  "field" should {

    "can hase next player with 1 player" in {
      val field1: FieldInterface = Field(matrix,GreenPlayer,1)

      field1.nextPlayer() should be(Field(Matrix(),GreenPlayer,1))
    }

    "can hase next player with 2 player" in {
      val field2: FieldInterface = Field(matrix,GreenPlayer,2)
      val field2_1: FieldInterface = Field(matrix,RedPlayer,2)

      field2.nextPlayer() should be(Field(Matrix(),RedPlayer,2))
      field2_1.nextPlayer() should be(Field(Matrix(),GreenPlayer,2))
    }

    "can hase next player with 3 player" in {
      val field3: FieldInterface = Field(matrix,GreenPlayer,3)
      val field3_1: FieldInterface = Field(matrix,RedPlayer,3)
      val field3_2: FieldInterface = Field(matrix,BluePlayer,3)

      field3.nextPlayer() should be(Field(Matrix(), RedPlayer, 3))
      field3_1.nextPlayer() should be(Field(Matrix(), BluePlayer, 3))
      field3_2.nextPlayer() should be(Field(Matrix(), GreenPlayer, 3))
    }

    "can hase next player with 4 player" in {
      val field4: FieldInterface = Field(matrix,GreenPlayer,4)
      val field4_1: FieldInterface = Field(matrix,RedPlayer,4)
      val field4_2: FieldInterface = Field(matrix,BluePlayer,4)
      val field4_3: FieldInterface = Field(matrix,YellowPlayer,4)

      field4.nextPlayer() should be(Field(Matrix(), RedPlayer, 4))
      field4_1.nextPlayer() should be(Field(Matrix(), BluePlayer, 4))
      field4_2.nextPlayer() should be(Field(Matrix(), YellowPlayer, 4))
      field4_3.nextPlayer() should be(Field(Matrix(), GreenPlayer, 4))
    }


    "can convert a field to String" in {
      val field_tS: FieldInterface = Field(matrix,GreenPlayer,1)
      field_tS.toString() should be(
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "|    |      |    |      |    ||    ||    |      |    |      |    |" + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "                        +----++----++----+                        " + eol +
          "                        |    ||    ||    |                        " + eol +
          "                        +----++----++----+                        " + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "|    |      |    |      |    ||    ||    |      |    |      |    |" + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "                        +----++----++----+                        " + eol +
          "                        |    ||    ||    |                        " + eol +
          "                        +----++----++----+                        " + eol +
          "+----++----++----++----++----++----++----++----++----++----++----+" + eol +
          "|    ||    ||    ||    ||    ||    ||    ||    ||    ||    ||    |" + eol +
          "+----++----++----++----++----++----++----++----++----++----++----+" + eol +
          "+----++----++----++----++----+      +----++----++----++----++----+" + eol +
          "|    ||    ||    ||    ||    |      |    ||    ||    ||    ||    |" + eol +
          "+----++----++----++----++----+      +----++----++----++----++----+" + eol +
          "+----++----++----++----++----++----++----++----++----++----++----+" + eol +
          "|    ||    ||    ||    ||    ||    ||    ||    ||    ||    ||    |" + eol +
          "+----++----++----++----++----++----++----++----++----++----++----+" + eol +
          "                        +----++----++----+                        " + eol +
          "                        |    ||    ||    |                        " + eol +
          "                        +----++----++----+                        " + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "|    |      |    |      |    ||    ||    |      |    |      |    |" + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "                        +----++----++----+                        " + eol +
          "                        |    ||    ||    |                        " + eol +
          "                        +----++----++----+                        " + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "|    |      |    |      |    ||    ||    |      |    |      |    |" + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "Green Player have to dice"
      )
    }

    "can insert player into random field" in {
      var field_p: FieldInterface = Field(matrix,GreenPlayer,1)
      field_p = field_p.put(Move(PlayToken.apply(1, "B"), 20))
      field_p.toString should be(
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "|    |      |    |      |    ||    ||    |      |    |      |    |" + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "                        +----++----++----+                        " + eol +
          "                        |    ||    ||    |                        " + eol +
          "                        +----++----++----+                        " + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "|    |      |    |      |    ||    ||    |      |    |      |    |" + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "                        +----++----++----+                        " + eol +
          "                        |    ||    ||    |                        " + eol +
          "                        +----++----++----+                        " + eol +
          "+----++----++----++----++----++----++----++----++----++----++----+" + eol +
          "| B1 ||    ||    ||    ||    ||    ||    ||    ||    ||    ||    |" + eol +
          "+----++----++----++----++----++----++----++----++----++----++----+" + eol +
          "+----++----++----++----++----+      +----++----++----++----++----+" + eol +
          "|    ||    ||    ||    ||    |      |    ||    ||    ||    ||    |" + eol +
          "+----++----++----++----++----+      +----++----++----++----++----+" + eol +
          "+----++----++----++----++----++----++----++----++----++----++----+" + eol +
          "|    ||    ||    ||    ||    ||    ||    ||    ||    ||    ||    |" + eol +
          "+----++----++----++----++----++----++----++----++----++----++----+" + eol +
          "                        +----++----++----+                        " + eol +
          "                        |    ||    ||    |                        " + eol +
          "                        +----++----++----+                        " + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "|    |      |    |      |    ||    ||    |      |    |      |    |" + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "                        +----++----++----+                        " + eol +
          "                        |    ||    ||    |                        " + eol +
          "                        +----++----++----+                        " + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "|    |      |    |      |    ||    ||    |      |    |      |    |" + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "Green Player have to dice"
      )
    }

    "can beat other player" in {
      var field_bop: FieldInterface = Field(matrix)
      field_bop = field_bop.put(Move(PlayToken.apply(1, "B"), 25))
      field_bop = field_bop.put(Move(PlayToken.apply(1, "G"), 20))
      field_bop = field_bop.move(Move(PlayToken.apply(1, "G"), 25))
      field_bop.toString should be (
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "|    |      |    |      |    ||    ||    |      |    |      |    |" + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "                        +----++----++----+                        " + eol +
          "                        |    ||    ||    |                        " + eol +
          "                        +----++----++----+                        " + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "|    |      |    |      |    ||    ||    |      |    |      |    |" + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "                        +----++----++----+                        " + eol +
          "                        | G1 ||    ||    |                        " + eol +
          "                        +----++----++----+                        " + eol +
          "+----++----++----++----++----++----++----++----++----++----++----+" + eol +
          "|    ||    ||    ||    ||    ||    ||    ||    ||    ||    ||    |" + eol +
          "+----++----++----++----++----++----++----++----++----++----++----+" + eol +
          "+----++----++----++----++----+      +----++----++----++----++----+" + eol +
          "|    ||    ||    ||    ||    |      |    ||    ||    ||    ||    |" + eol +
          "+----++----++----++----++----+      +----++----++----++----++----+" + eol +
          "+----++----++----++----++----++----++----++----++----++----++----+" + eol +
          "|    ||    ||    ||    ||    ||    ||    ||    ||    ||    ||    |" + eol +
          "+----++----++----++----++----++----++----++----++----++----++----+" + eol +
          "                        +----++----++----+                        " + eol +
          "                        |    ||    ||    |                        " + eol +
          "                        +----++----++----+                        " + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "|    |      |    |      |    ||    ||    |      | B1 |      |    |" + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "                        +----++----++----+                        " + eol +
          "                        |    ||    ||    |                        " + eol +
          "                        +----++----++----+                        " + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "|    |      |    |      |    ||    ||    |      |    |      |    |" + eol +
          "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
          "Green Player have to dice"
      )
    }

    "can get stone" in {
      var field_gs: FieldInterface = Field(matrix)
      field_gs = field_gs.put(Move(PlayToken.apply(1, "B"), 20))
      matrix.getStone(20) should be (Stone(true,20,None))
      matrix.getStone(30) should be (Stone(true,30,None))
    }

    "can invert dice" in {
      val field_iD: FieldInterface = Field(matrix,GreenPlayer,1)
      field_iD.invertDice() should be (Field(matrix,GreenPlayer,1,6,false))
    }

    "can get dice" in {
      val field_gD: FieldInterface = Field(matrix,GreenPlayer,1)
      field_gD.getDice should be (6)
    }

    "can dice" in {
      val field_d: FieldInterface = Field(matrix,GreenPlayer,1)
      field_d.dice(1) should be (Field(matrix,GreenPlayer,1,1))
    }

    "can set playerNumber" in {
      val field_pN: FieldInterface = Field(matrix,GreenPlayer,1)
      field_pN.numberPlayer(1) should be (Field(matrix,GreenPlayer,1))
    }

    /*
    "can move" in {
      val field_m: FieldInterface = Field(matrix)
      field_m.move(Move(PlayToken.apply(1, "B"), 20)) should be (field_m)
    }
    */

    "can get Matrix" in {
      val field_gM: FieldInterface = Field(matrix,GreenPlayer,1)
      field_gM.getMatrix should be (matrix)
    }

    "can get Player" in {
      val field_gP: FieldInterface = Field(matrix,GreenPlayer,1)
      field_gP.getPlayer should be (GreenPlayer)
    }

    "can get ShouldDice" in {
      val field_gSD: FieldInterface = Field(matrix,GreenPlayer,1)
      field_gSD.getShouldDice should be (true)
    }

  }
}














