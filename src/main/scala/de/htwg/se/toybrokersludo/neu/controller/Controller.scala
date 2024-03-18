package de.htwg.se.toybrokersludo.neu.controller

import de.htwg.se.toybrokersludo.neu.model.{GameField, Move}
import de.htwg.se.toybrokersludo.util.Observable

trait Controller extends Observable{
  def getGameField: GameField
  def possibleMoves(gameField: GameField): List[Move]
  def makeMove(move: Move): Unit
  def dice(): Unit
}
