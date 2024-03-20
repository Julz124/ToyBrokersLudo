package de.htwg.se.toybrokersludo.controller.impl

import de.htwg.se.toybrokersludo.FileIOStub
import de.htwg.se.toybrokersludo.controller.impl.DefaultController
import de.htwg.se.toybrokersludo.model.{GameField, Move, Token}
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

    "undo successfully" in {
      val move = Move(0, 1)
      val gameField = GameField.init()
      sut.gameField = gameField
      sut.makeMove(move)

      sut.undo() shouldBe Success(())
    }

    "redo successfully" in {
      val move = Move(0, 1)
      val gameField = GameField.init()
      sut.gameField = gameField
      sut.makeMove(move)
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
  }
}

