package de.htwg.se.toybrokersludo.controller.impl

import de.htwg.se.toybrokersludo.controller.Controller
import de.htwg.se.toybrokersludo.model.{Cell, GameField, Move, Token}
import de.htwg.se.toybrokersludo.model.Player.{Blue, Green, Red, Yellow}
import de.htwg.se.toybrokersludo.util.UndoManager

class DefaultController extends Controller {
  private var gameField: GameField = GameField.init()
  private val  undoManager = UndoManager[GameField]

  def getGameField: GameField = gameField

  def makeMove(move: Move): Unit =
    gameField = undoManager.doStep(gameField, MoveCommander(generateValidMoveList(move), gameField.gameState))
    notifyObservers()

  def dice(): Unit =
    if (!gameField.gameState.shouldDice) return
    gameField = undoManager.doStep(gameField, DiceCommander(gameField.gameState))
    notifyObservers()

  def undo(): Unit =
    gameField = undoManager.undoStep(gameField)
    notifyObservers()

  def redo(): Unit =
    gameField = undoManager.redoStep(gameField)
    notifyObservers()

  private def generateValidMoveList(move: Move): List[Move] =
    move.toCell(gameField.map).token match
      case Some(token: Token) => List(Move(
        fromIndex = move.toIndex,
        toIndex = gameField.map.find { cell => cell._2.index == token.playerHouseIndex }.get._2.index)
        , move)
      case None => List(move)
}
