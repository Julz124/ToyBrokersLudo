package de.htwg.se.toybrokersludo.model

import de.htwg.se.toybrokersludo.model.{FieldInterface, PlayerInterface, MatrixInterface}

trait FieldInterface {
  def put(move: Move): FieldInterface

  def numberPlayer(number: Int): FieldInterface

  def dice(dice: Int): FieldInterface

  def invertDice(): FieldInterface

  def nextPlayer(): FieldInterface

  def move(move: Move): FieldInterface

  def toString: String

  def getMatrix: MatrixInterface

  def getPlayer: PlayerInterface

  def getDice: Int
  
  def getShouldDice: Boolean
  
}
