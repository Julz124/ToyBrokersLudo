package de.htwg.se.toybrokersludo.controller

import de.htwg.se.toybrokersludo.model.{GameField, Move}
import de.htwg.se.toybrokersludo.util.Observable

import scala.util.Try

trait Controller extends Observable:
  def getGameField: GameField
  def possibleMoves: Try[List[Move]]
  def makeMove(move: Move): Try[Unit]
  def undo(): Try[Unit]
  def redo(): Try[Unit]
  def dice(): Try[Unit]
  def save(target: String): Try[Unit]
  def getTargets: Try[List[String]]
  def load(source: String): Try[Unit]
