package de.htwg.se.toybrokersludo.model

import de.htwg.se.toybrokersludo.model.FieldInterface
import de.htwg.se.toybrokersludo.util.PlayerInterface
import de.htwg.se.toybrokersludo.model.FieldBaseImpl.Matrix

trait FieldInterface {
  def put(move: Move): FieldInterface

  def numberPlayer(number: Int): FieldInterface

  def dice(dice: Int): FieldInterface

  def invertDice(): FieldInterface

  def nextPlayer(): FieldInterface

  def move(move: Move): FieldInterface

  def toString: String

  def getMatrix: Matrix

  def getPlayer: PlayerInterface

  def getDice: Int
  
  def getShouldDice: Boolean
  
}
