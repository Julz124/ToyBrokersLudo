package de.htwg.se.toybrokersludo.model

import de.htwg.se.toybrokersludo.util.possibleMoves

import scala.util.Random

case class GameState(shouldDice: Boolean, diceNumber: Int, currentPlayer: Player) {
  def rollDice(map: Map[(Int, Int), Cell]): GameState = {
    val newDiceNumber = Random.nextInt(6) + 1
    if (possibleMoves(GameField(map, this.copy(diceNumber = newDiceNumber))).isEmpty) {
      this.copy(shouldDice = true, diceNumber = newDiceNumber, currentPlayer = currentPlayer.next)
    } else {
      this.copy(shouldDice = false, diceNumber = newDiceNumber)
    }
  }

  def computeMove(player: Player): GameState = {
    if (player != currentPlayer && diceNumber != 6) {
      this.copy(currentPlayer = currentPlayer.next)
    } else {
      this.copy(shouldDice = true)
    }
  }
}
