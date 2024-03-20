package de.htwg.se.toybrokersludo.controller.impl

import de.htwg.se.toybrokersludo.FileIOStub
import de.htwg.se.toybrokersludo.controller.impl.DefaultController
import de.htwg.se.toybrokersludo.model.{GameField, GameState, Move, Player, Token}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.util.{Failure, Success}

class DefaultControllerSpec extends AnyWordSpec with Matchers {
  val fileIO: FileIOStub = FileIOStub()
  val sut: DefaultController = DefaultController(using fileIO)

  "The Controller" should {

    "return possible moves when shouldDice is false" in {
      val gameField = GameField.init().copy(gameState = GameField.init().gameState.copy(shouldDice = false, diceNumber = 6))
      sut.gameField = gameField

      sut.possibleMoves shouldBe Success(de.htwg.se.toybrokersludo.util.possibleMoves(gameField))
    }

    "return possible moves when shouldDice is false greenPlayerStart" in {
      var gameField = GameField.init()
      gameField = gameField.move(Move(0, Player.Green.firstCellIndex))
      gameField = gameField.copy(gameState = GameField.init().gameState.copy(shouldDice = false, diceNumber = 6))
      sut.gameField = gameField

      sut.possibleMoves shouldBe Success(de.htwg.se.toybrokersludo.util.possibleMoves(gameField))
    }

    "return possible moves when shouldDice is false redPlayerStart" in {
      var gameField = GameField.init()
      gameField = gameField.move(Move(4, Player.Red.firstCellIndex))
      gameField = gameField.copy(gameState = GameField.init().gameState.copy(shouldDice = false, diceNumber = 6))
      sut.gameField = gameField

      sut.possibleMoves shouldBe Success(de.htwg.se.toybrokersludo.util.possibleMoves(gameField))
    }

    "return possible moves when shouldDice is false bluePlayerStart" in {
      var gameField = GameField.init()
      gameField = gameField.move(Move(7, Player.Blue.firstCellIndex))
      gameField = gameField.copy(gameState = GameField.init().gameState.copy(shouldDice = false, diceNumber = 6))
      sut.gameField = gameField

      sut.possibleMoves shouldBe Success(de.htwg.se.toybrokersludo.util.possibleMoves(gameField))
    }

    "return possible moves when shouldDice is false yellowPlayerStart" in {
      var gameField = GameField.init()
      gameField = gameField.move(Move(11, Player.Yellow.firstCellIndex))
      gameField = gameField.copy(gameState = GameField.init().gameState.copy(shouldDice = false, diceNumber = 6))
      sut.gameField = gameField

      sut.possibleMoves shouldBe Success(de.htwg.se.toybrokersludo.util.possibleMoves(gameField))
    }

    "return possible moves when shouldDice is false goOverEnd" in {
      var gameField = GameField.init()
      gameField = gameField.move(Move(4, 58))
      gameField = gameField.copy(gameState = GameField.init().gameState.copy(shouldDice = false, diceNumber = 6))
      sut.gameField = gameField

      sut.possibleMoves shouldBe Success(de.htwg.se.toybrokersludo.util.possibleMoves(gameField))
    }

    "return failure when shouldDice is true in possibleMoves" in {
      val gameField = GameField.init().copy(gameState = GameField.init().gameState.copy(shouldDice = true))
      sut.gameField = gameField

      sut.possibleMoves shouldBe a[Failure[_]]
    }

    "make a move successfully" in {
      val move = Move(0, 1)
      val gameField = GameField.init().copy(gameState = GameField.init().gameState.copy(shouldDice = false, diceNumber = 6))
      sut.gameField = gameField

      sut.makeMove(move) shouldBe Success(())
    }

    "return failure when shouldDice is false in dice" in {
      val gameField = GameField.init().copy(gameState = GameField.init().gameState.copy(shouldDice = false))
      sut.gameField = gameField

      sut.dice() shouldBe a[Failure[_]]
    }

    "dice successfully" in {
      val gameField = GameField.init().copy(gameState = GameField.init().gameState.copy(shouldDice = true))
      sut.gameField = gameField

      sut.dice() shouldBe Success(())
      sut.gameField.gameState.diceNumber should (be >= 1 and be <= 6)
    }

    "undo move successfully" in {
      val move = Move(0, 1)
      val gameField = GameField.init()
      sut.gameField = gameField
      sut.makeMove(move)
      sut.gameField = sut.getGameField.copy(gameState = gameField.gameState.copy(shouldDice = false, diceNumber = 6))

      sut.undo() shouldBe Success(())
    }

    "redo move successfully" in {
      val move = Move(0, 1)
      val gameField = GameField.init()
      sut.gameField = gameField
      sut.makeMove(move)
      sut.gameField = sut.getGameField.copy(gameState = gameField.gameState.copy(shouldDice = false, diceNumber = 6))
      sut.undo()

      sut.redo() shouldBe Success(())
    }

    "undo dice successfully" in {
      val gameField = GameField.init()
      sut.gameField = gameField
      sut.dice()

      sut.undo() shouldBe Success(())
    }

    "redo dice successfully" in {
      val gameField = GameField.init()
      sut.gameField = gameField
      sut.dice()
      sut.undo()

      sut.redo() shouldBe Success(())
    }

    "save successfully" in {
      val target = "test.txt"
      sut.save(target) shouldBe Success(())
    }

    "get targets successfully" in {
      sut.getTargets shouldBe Success(List())
    }

    "load successfully" in {
      val source = "test.txt"
      sut.load(source) shouldBe Success(())
    }

    "getGameFiled return gameField" in {
      sut.getGameField.toString shouldEqual (sut.getGameField.toString)
    }

    "gameField init should return a valid gameField" in {
      val expected = "G1G2OOOR1R2OOOG3G4OOOR3R4OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOY1Y2OOOB1B2OOOY3Y4OOOB3B4Dice:0Greenhavetodice"

      val actual = GameField.init().toString

      actual.replaceAll("\\s+", "") shouldEqual expected.replaceAll("\\s+", "")
    }
  }}