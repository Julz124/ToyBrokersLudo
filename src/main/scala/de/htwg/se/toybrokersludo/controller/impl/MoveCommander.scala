package de.htwg.se.toybrokersludo.controller.impl

import de.htwg.se.toybrokersludo.util.Command
import model.{GameField, GameState, Move}

import scala.util.Random

class MoveCommander(moves: List[Move], var gameState: GameState) extends Command[GameField]:
  override def doStep(gameField: GameField): GameField =
    gameState = gameField.gameState
    moves.foldLeft(gameField)((currentGameField, move) => 
      currentGameField.move(move)
    )

  override def undoStep(gameField: GameField): GameField =
    val oldGameState = gameState
    gameState = gameField.gameState
    moves.reverse.foldLeft(gameField)((currentGameField, move) =>
      currentGameField.move(Move(fromIndex = move.toIndex, toIndex = move.fromIndex))
    )

  override def redoStep(gameField: GameField): GameField =
    val oldGameState = gameState
    gameState = gameField.gameState
    moves.foldLeft(gameField)((currentGameField, move) => 
      currentGameField.move(move)
    )

