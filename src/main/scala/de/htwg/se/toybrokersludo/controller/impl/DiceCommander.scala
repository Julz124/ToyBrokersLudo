package de.htwg.se.toybrokersludo.controller.impl

import de.htwg.se.toybrokersludo.model.{GameField, GameState, Player}
import de.htwg.se.toybrokersludo.util.{Command, possibleMoves}

import scala.util.Random

class DiceCommander(var gameState: GameState) extends Command[GameField]:
  override def doStep(gameField: GameField): GameField =
     gameState = gameField.gameState
     gameField.rollDice

  override def undoStep(gameField: GameField): GameField =
    val oldGameState = gameState
    gameState = gameField.gameState
    GameField(gameField.map, oldGameState)

  override def redoStep(gameField: GameField): GameField =
    val oldGameState = gameState
    gameState = gameField.gameState
    GameField(gameField.map, oldGameState)