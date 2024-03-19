package de.htwg.se.toybrokersludo.controller.impl

import de.htwg.se.toybrokersludo.controller.Controller
import de.htwg.se.toybrokersludo.controller.FileIO.FileIO
import de.htwg.se.toybrokersludo.model.{Cell, GameField, Move, Token}
import de.htwg.se.toybrokersludo.model.Player.{Blue, Green, Red, Yellow}
import de.htwg.se.toybrokersludo.util.UndoManager

class DefaultController(using fileIO: FileIO) extends Controller:
  private var gameField: GameField = GameField.init()
  private val  undoManager = UndoManager[GameField]

  override def getGameField: GameField = gameField

  override def makeMove(move: Move): Unit =
    if (gameField.gameState.shouldDice) return
    gameField = undoManager.doStep(gameField, MoveCommander(generateValidMoveList(move), gameField.gameState))
    notifyObservers()

  override def dice(): Unit =
    if (!gameField.gameState.shouldDice) return
    gameField = undoManager.doStep(gameField, DiceCommander(gameField.gameState))
    notifyObservers()

  override def undo(): Unit =
    gameField = undoManager.undoStep(gameField)
    notifyObservers()

  override def redo(): Unit =
    gameField = undoManager.redoStep(gameField)
    notifyObservers()

  override def save(target: String): Unit = 
    fileIO.save(gameField, target)

  override def getTargets: List[String] =
    fileIO.getTargets

  override def load(source: String): Unit =
    gameField = fileIO.load(source)
    undoManager.clear()
    notifyObservers()

  private def generateValidMoveList(move: Move): List[Move] =
    move.toCell(gameField.map).token match
      case Some(token: Token) => List(Move(
        fromIndex = move.toIndex,
        toIndex = gameField.map.find { 
          cell => cell._2.index == token.playerHouseIndex 
        }.get._2.index), move)
      case None => List(move)

