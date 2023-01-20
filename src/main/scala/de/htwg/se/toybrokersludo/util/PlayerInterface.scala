package de.htwg.se.toybrokersludo.util

import de.htwg.se.toybrokersludo.model.{FieldInterface, Move}

trait PlayerInterface {

  def possibleMoves(diceroll: Int, field: FieldInterface): List[Move]

  def defaultField(): List[Int]

  def startField(): Int

  def endFields(): List[Int]

  def lastField(): Int

  def playerString: String
}
