package de.htwg.se.toybrokersludo.model

import de.htwg.se.toybrokersludo.model.FieldInterface

trait PlayerInterface {
  def possibleMoves(diceroll: Int, field: FieldInterface): List[Move]

  def defaultField(): List[Int]

  def startField(): Int

  def endFields(): List[Int]

  def lastField(): Int

  def fooFields(): List[Int]

  def playerString: String
}
