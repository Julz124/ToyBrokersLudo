package de.htwg.se.toybrokersludo.controller

import de.htwg.se.toybrokersludo.model.{GameField, Move}
import de.htwg.se.toybrokersludo.util.Observable

trait Controller extends Observable{
  def getGameField: GameField
  def possibleMoves(gameField: GameField): List[Move]
  def makeMove(move: Move): Unit
  def dice(): Unit
}
