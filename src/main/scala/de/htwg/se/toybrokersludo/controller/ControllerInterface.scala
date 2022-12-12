package de.htwg.se.toybrokersludo.controller

import de.htwg.se.toybrokersludo.model.{Field, Matrix, Move, Player}
import de.htwg.se.toybrokersludo.util.Observable

trait ControllerInterface extends Observable{
  
  def getShouldDice : Boolean
  
  def getPlayer : Player
  
  def getDice : Int
  
  def getMatrix : Matrix
  
  def getField: Field
  
  def startup (spieler: Int): Unit
  
  def dice() : Unit
  
  def update() : Unit
  
  def invertDice() : Unit

  def getPossibleMoves(dice : Int): List[Move]

  def nextPlayer() : Unit

  def doAndPublish(doThis: Move => Field, move: Move) : Unit

  def doAndPublish(doThis: Field => Field) : Unit

  def put(move: Move): Field

  def move(move : Move) : Field

  def undo(field : Field): Field

  def redo(field : Field): Field

}
