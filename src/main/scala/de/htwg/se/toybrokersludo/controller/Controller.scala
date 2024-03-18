package de.htwg.se.toybrokersludo.controller

import de.htwg.se.toybrokersludo.model.{GameField, Move}
import de.htwg.se.toybrokersludo.util.Observable

trait Controller extends Observable{
  def getGameField: GameField
  def makeMove(move: Move): Unit
  def undo(): Unit
  def redo(): Unit
  def dice(): Unit
}
