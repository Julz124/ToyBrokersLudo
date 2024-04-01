package controller

import model.{GameField, Move}
import util.Observable

import scala.concurrent.Future
import scala.util.Try

trait Controller extends Observable:
  def getGameField: GameField
  def possibleMoves: Try[List[Move]]
  def makeMove(move: Move): Try[Unit]
  def undo(): Try[Unit]
  def redo(): Try[Unit]
  def dice(): Try[Unit]
  def save(target: String): Future[Unit]
  def getTargets: Future[List[String]]
  def load(source: String): Future[Unit]
