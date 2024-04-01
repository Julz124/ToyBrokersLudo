package controller

import controller.Controller
import model.*
import model.Player.{Blue, Green, Red, Yellow}
import util.UndoManager

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success, Try}

class DefaultController(persistenceController: PersistenceController, uiController: UIController) extends Controller:
  var gameField: GameField = GameField.init()
  private val  undoManager = UndoManager[GameField]

  override def getGameField: GameField = gameField

  override def possibleMoves: Try[List[Move]] = Try {
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
      uiController.notifyObservers()
    }
  }

  override def dice(): Try[Unit] = Try {
    if (!gameField.gameState.shouldDice) {
      throw new IllegalStateException("You have to Move")
    } else {
      gameField = undoManager.doStep(gameField, DiceCommander(gameField.gameState))
      uiController.notifyObservers()
    }
  }

  override def undo(): Try[Unit] = Try {
    gameField = undoManager.undoStep(gameField)
    uiController.notifyObservers()
  }
  
  override def redo(): Try[Unit] = Try {
    gameField = undoManager.redoStep(gameField)
    uiController.notifyObservers()
  }

  override def save(target: String): Future[Unit] = 
    persistenceController.save(gameField, target)
  
  
  override def getTargets: Future[List[String]] = 
    persistenceController.getTargets


  override def load(source: String): Future[Unit] =
    persistenceController.load(source).map { loadedGameField =>
      gameField = loadedGameField
      undoManager.clear()
      uiController.notifyObservers()
    }
  
  private def generateValidMoveList(move: Move): List[Move] =
    move.toCell(gameField.map).token match
      case Some(token: Token) => List(Move(
        fromIndex = move.toIndex,
        toIndex = gameField.map.values.find {
          cell => cell.index == token.playerHouseIndex
        }.get.index), move)
      case None => List(move)

