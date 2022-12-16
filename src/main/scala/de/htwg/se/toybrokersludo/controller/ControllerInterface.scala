package de.htwg.se.toybrokersludo.controller


import de.htwg.se.toybrokersludo.model.{FieldInterface, MatrixInterface, Move, PlayerInterface}
import de.htwg.se.toybrokersludo.util.Observable

trait ControllerInterface extends Observable{
  
  def getShouldDice : Boolean
  
  def getPlayer : PlayerInterface
  
  def getDice : Int
  
  def getMatrix : MatrixInterface
  
  def getField: FieldInterface
  
  def startup (spieler: Int): Unit
  
  def dice() : Unit
  
  def update() : Unit
  
  def invertDice() : Unit

  def getPossibleMoves(dice : Int): List[Move]

  def nextPlayer() : Unit

  def doAndPublish(doThis: Move => FieldInterface, move: Move) : Unit

  def doAndPublish(doThis: FieldInterface => FieldInterface) : Unit

  def put(move: Move): FieldInterface

  def move(move : Move) : FieldInterface

  def undo(field : FieldInterface): FieldInterface

  def redo(field : FieldInterface): FieldInterface

}
