package de.htwg.se.toybrokersludo.controller.impl

import de.htwg.se.toybrokersludo.controller.Controller
import de.htwg.se.toybrokersludo.model.{Cell, Dice, GameField, Move, Token}
import de.htwg.se.toybrokersludo.model.Player.{Blue, Green, Red, Yellow}

class DefaultController extends Controller with PossibleMovesExtension {

  private var gameField: GameField = GameField.init()

  def getGameField: GameField = gameField

  def makeMove(move: Move): Unit =
    generateValidMoveList(gameField.map, move).foreach((move: Move) =>
      gameField = gameField.move(move)
    )
    gameField = getGameField.nextPlayer()
    notifyObservers

  def dice(): Unit =
    if (!gameField.dice.shouldDice) return
    gameField = gameField.rollDice()
    if (possibleMoves(getGameField).isEmpty) {
      gameField = getGameField.nextPlayer()
    }
    notifyObservers

  private def generateValidMoveList(map: Map[(Int, Int), Cell], move: Move): List[Move] =
    move.toCell(map).token match
      case Some(token: Token) => List(Move(
        fromIndex = move.toIndex,
        toIndex = map.find { cell => cell._2.index == token.playerHouseIndex }.get._2.index)
        , move)
      case None => List(move)
}
