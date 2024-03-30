package de.htwg.se.toybrokersludo.controller.impl

import de.htwg.se.toybrokersludo.FileIO.FileIO
import de.htwg.se.toybrokersludo.controller.Controller
import de.htwg.se.toybrokersludo.util.UndoManager
import model.Player.{Blue, Green, Red, Yellow}
import model.*

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

class DefaultController(using fileIO: FileIO) extends Controller:
  var gameField: GameField = GameField.init()
  private val  undoManager = UndoManager[GameField]

  override def getGameField: GameField = gameField

  def possibleMoves: Try[List[Move]] = Try {
    if (gameField.gameState.shouldDice) {
      throw new IllegalStateException("You have to Dice")
    } else {
      gameField.possibleMoves()
    }
  }

  override def makeMove(move: Move): Try[Unit] = Try {
    if (gameField.gameState.shouldDice) {
      throw new IllegalStateException("You have to Dice")
    } else {
      gameField = undoManager.doStep(gameField, MoveCommander(generateValidMoveList(move), gameField.gameState))
      notifyObservers()
    }
  }

  override def dice(): Try[Unit] = Try {
    if (!gameField.gameState.shouldDice) {
      throw new IllegalStateException("You have to Move")
    } else {
      gameField = undoManager.doStep(gameField, DiceCommander(gameField.gameState))
      notifyObservers()
    }
  }

  override def undo(): Try[Unit] = Try {
    gameField = undoManager.undoStep(gameField)
    notifyObservers()
  }
  
  override def redo(): Try[Unit] = Try {
    gameField = undoManager.redoStep(gameField)
    notifyObservers()
  }

  override def save(target: String): Try[Unit] = Try {
    fileIO.save(gameField, target)
  }
  
  override def getTargets: Try[List[String]] = Try {
    fileIO.getTargets
  }

  override def load(source: String): Try[Unit] = Try {
    fileIO.load(source).onComplete {
      case Success(gameField) =>
        this.gameField = gameField
        undoManager.clear()
        notifyObservers()
      case Failure(exception) =>
        throw RuntimeException("Cant load from fileIO " + exception.getMessage)
    }
  }
  
  private def generateValidMoveList(move: Move): List[Move] =
    move.toCell(gameField.map).token match
      case Some(token: Token) => List(Move(
        fromIndex = move.toIndex,
        toIndex = gameField.map.values.find {
          cell => cell.index == token.playerHouseIndex
        }.get.index), move)
      case None => List(move)

