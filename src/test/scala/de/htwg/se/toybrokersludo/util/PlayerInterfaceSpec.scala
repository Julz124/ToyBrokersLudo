package de.htwg.se.toybrokersludo.util

import de.htwg.se.toybrokersludo.controller.ControllerInterface
import de.htwg.se.toybrokersludo.controller.controllerBaseImpl.Controller
import de.htwg.se.toybrokersludo.model.FieldBaseImpl.{Field, Matrix}
import de.htwg.se.toybrokersludo.model.FileIO.FileIOInterface
import de.htwg.se.toybrokersludo.model.FileIO.JsonImpl.FileIo
import de.htwg.se.toybrokersludo.model.{FieldInterface, Move, PlayToken}
import de.htwg.se.toybrokersludo.util.PlayerBaseImpl.{BluePlayer, GreenPlayer, RedPlayer, YellowPlayer}
import de.htwg.se.toybrokersludo.util.PlayerInterface
import org.scalatest.matchers.should.Matchers
import org.scalatest.matchers.should.Matchers.should
import org.scalatest.wordspec.AnyWordSpec

class PlayerInterfaceSpec extends AnyWordSpec with Matchers {

  val matrix: Matrix = Matrix()
  val field: FieldInterface = Field(matrix,GreenPlayer,1)
  val fileIO : FileIOInterface = FileIo()
  val controller: ControllerInterface = Controller(using field)(using fileIO)

  val eol = "\n"

  "player" should {

    var field: FieldInterface = Field(matrix)
    val playerG_pM: PlayerInterface = GreenPlayer
    val playerR_pM: PlayerInterface = RedPlayer
    val playerB_pM: PlayerInterface = BluePlayer
    val playerY_pM: PlayerInterface = YellowPlayer
    field = field.put(Move(PlayToken.apply(1, "G"), 21))
    field = field.put(Move(PlayToken.apply(1, "R"), 31))
    field = field.put(Move(PlayToken.apply(1, "B"), 41))
    field = field.put(Move(PlayToken.apply(1, "Y"), 51))
    field = field.put(Move(PlayToken.apply(2, "G"), 0))
    field = field.put(Move(PlayToken.apply(2, "R"), 4))
    field = field.put(Move(PlayToken.apply(2, "B"), 12))
    field = field.put(Move(PlayToken.apply(2, "Y"), 8))

    "get's possible moves with diceroll  1" in {
      playerG_pM.possibleMoves(1, field).toString() should equal("List(Move(G1,22))")
      playerR_pM.possibleMoves(1, field).toString() should equal("List(Move(R1,32))")
      playerB_pM.possibleMoves(1, field).toString() should equal("List(Move(B1,42))")
      playerY_pM.possibleMoves(1, field).toString() should equal("List(Move(Y1,52))")
    }

    "get's possible moves with diceroll  2" in {
      playerG_pM.possibleMoves(2, field).toString() should equal("List(Move(G1,23))")
      playerR_pM.possibleMoves(2, field).toString() should equal("List(Move(R1,33))")
      playerB_pM.possibleMoves(2, field).toString() should equal("List(Move(B1,43))")
      playerY_pM.possibleMoves(2, field).toString() should equal("List(Move(Y1,53))")
    }

    "get's possible moves with diceroll  3" in {
      playerG_pM.possibleMoves(3, field).toString() should equal("List(Move(G1,24))")
      playerR_pM.possibleMoves(3, field).toString() should equal("List(Move(R1,34))")
      playerB_pM.possibleMoves(3, field).toString() should equal("List(Move(B1,44))")
      playerY_pM.possibleMoves(3, field).toString() should equal("List(Move(Y1,54))")
    }

    "get's possible moves with diceroll  4" in {
      playerG_pM.possibleMoves(4, field).toString() should equal("List(Move(G1,25))")
      playerR_pM.possibleMoves(4, field).toString() should equal("List(Move(R1,35))")
      playerB_pM.possibleMoves(4, field).toString() should equal("List(Move(B1,45))")
      playerY_pM.possibleMoves(4, field).toString() should equal("List(Move(Y1,55))")
    }

    "get's possible moves with diceroll  5" in {
      playerG_pM.possibleMoves(5, field).toString() should equal("List(Move(G1,26))")
      playerR_pM.possibleMoves(5, field).toString() should equal("List(Move(R1,36))")
      playerB_pM.possibleMoves(5, field).toString() should equal("List(Move(B1,46))")
      playerY_pM.possibleMoves(5, field).toString() should equal("List(Move(Y1,56))")
    }

    "get's possible moves with diceroll  6" in {
      playerG_pM.possibleMoves(6, field).toString() should equal("List(Move(G2,20), Move(G1,27))")
      playerR_pM.possibleMoves(6, field).toString() should equal("List(Move(R2,30), Move(R1,37))")
      playerB_pM.possibleMoves(6, field).toString() should equal("List(Move(B2,40), Move(B1,47))")
      playerY_pM.possibleMoves(6, field).toString() should equal("List(Move(Y2,50), Move(Y1,57))")
    }

    "get's possible moves into endfields" in {
      val matrix2: Matrix = Matrix()
      matrix2.put(Move(PlayToken.apply(1,"G"),59))
      matrix2.put(Move(PlayToken.apply(1,"R"),29))
      matrix2.put(Move(PlayToken.apply(1,"B"),39))
      matrix2.put(Move(PlayToken.apply(1,"Y"),49))
      val field2: FieldInterface = Field(matrix2)

      val playerG_pM: PlayerInterface = GreenPlayer
      playerG_pM.possibleMoves(2, field2) should be(List())
      val playerR_pM: PlayerInterface = RedPlayer
      playerR_pM.possibleMoves(2, field2) should be(List())
      val playerB_pM: PlayerInterface = BluePlayer
      playerB_pM.possibleMoves(2, field2) should be(List())
      val playerY_pM: PlayerInterface = YellowPlayer
      playerY_pM.possibleMoves(2, field2) should be(List())
    }

    "get's default Fields" in {
      val playerG_dF: PlayerInterface = GreenPlayer
      playerG_dF.defaultField() should be (GreenPlayer.defaultField())
      val playerR_dF: PlayerInterface = RedPlayer
      playerR_dF.defaultField() should be(RedPlayer.defaultField())
      val playerB_dF: PlayerInterface = BluePlayer
      playerB_dF.defaultField() should be(BluePlayer.defaultField())
      val playerY_dF: PlayerInterface = YellowPlayer
      playerY_dF.defaultField() should be(YellowPlayer.defaultField())
    }

    "get's start Field" in {
      val playerG_sF: PlayerInterface = GreenPlayer
      playerG_sF.startField() should be(GreenPlayer.startField())
      val playerR_sF: PlayerInterface = RedPlayer
      playerR_sF.startField() should be(RedPlayer.startField())
      val playerB_sF: PlayerInterface = BluePlayer
      playerB_sF.startField() should be(BluePlayer.startField())
      val playerY_sF: PlayerInterface = YellowPlayer
      playerY_sF.startField() should be(YellowPlayer.startField())
    }

    "get's end Fields" in {
      val playerG_eF: PlayerInterface = GreenPlayer
      playerG_eF.endFields() should be (GreenPlayer.endFields())
      val playerR_eF: PlayerInterface = RedPlayer
      playerR_eF.endFields() should be(RedPlayer.endFields())
      val playerB_eF: PlayerInterface = BluePlayer
      playerB_eF.endFields() should be(BluePlayer.endFields())
      val playerY_eF: PlayerInterface = YellowPlayer
      playerY_eF.endFields() should be(YellowPlayer.endFields())
    }

    "get's last Field" in {
      val playerG_lF: PlayerInterface = GreenPlayer
      playerG_lF.lastField() should be(GreenPlayer.lastField())
      val playerR_lF: PlayerInterface = RedPlayer
      playerR_lF.lastField() should be(RedPlayer.lastField())
      val playerB_lF: PlayerInterface = BluePlayer
      playerB_lF.lastField() should be(BluePlayer.lastField())
      val playerY_lF: PlayerInterface = YellowPlayer
      playerY_lF.lastField() should be(YellowPlayer.lastField())
    }

    "get's foo Fields" in {
      val playerG_fF: PlayerInterface = GreenPlayer
      playerG_fF.fooFields() should be(GreenPlayer.fooFields())
      val playerR_fF: PlayerInterface = RedPlayer
      playerR_fF.fooFields() should be(RedPlayer.fooFields())
      val playerB_fF: PlayerInterface = BluePlayer
      playerB_fF.fooFields() should be(BluePlayer.fooFields())
      val playerY_fF: PlayerInterface = YellowPlayer
      playerY_fF.fooFields() should be(YellowPlayer.fooFields())
    }

    "get's player String" in {
      val playerG_pS: PlayerInterface = GreenPlayer
      playerG_pS.playerString should be(GreenPlayer.playerString)
      val playerR_pS: PlayerInterface = RedPlayer
      playerR_pS.playerString should be(RedPlayer.playerString)
      val playerB_pS: PlayerInterface = BluePlayer
      playerB_pS.playerString should be(BluePlayer.playerString)
      val playerY_pS: PlayerInterface = YellowPlayer
      playerY_pS.playerString should be(YellowPlayer.playerString)
    }

    "convert player toString" in {
      val playerG_ptS: PlayerInterface = GreenPlayer
      playerG_ptS.toString should be(GreenPlayer.toString)
      val playerR_ptS: PlayerInterface = RedPlayer
      playerR_ptS.toString should be(RedPlayer.toString)
      val playerB_ptS: PlayerInterface = BluePlayer
      playerB_ptS.toString should be(BluePlayer.toString)
      val playerY_ptS: PlayerInterface = YellowPlayer
      playerY_ptS.toString should be(YellowPlayer.toString)
    }


  }
}