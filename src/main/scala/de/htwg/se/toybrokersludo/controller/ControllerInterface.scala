package de.htwg.se.toybrokersludo.controller


import de.htwg.se.toybrokersludo.model.{FieldInterface, Move}
import de.htwg.se.toybrokersludo.model.FieldBaseImpl.Matrix
import de.htwg.se.toybrokersludo.util.{Observable, PlayerInterface}

trait ControllerInterface extends Observable{
  
  def getShouldDice : Boolean
  
  def getPlayer : PlayerInterface
  
  def getDice : Int
  
  def getMatrix : Matrix
  
  def getField: FieldInterface
  
  def startup (spieler: Int): Unit
  
  def dice() : Unit

  def getPossibleMoves(dice : Int): List[Move]

  def doAndPublish(doThis: Move => FieldInterface, move: Move) : Unit

  def doAndPublish(doThis: FieldInterface => FieldInterface) : Unit

  def put(move: Move): FieldInterface

  def move(move : Move) : FieldInterface

  def undo(field : FieldInterface): FieldInterface

  def redo(field : FieldInterface): FieldInterface

}
