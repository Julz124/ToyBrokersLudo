package de.htwg.se.toybrokersludo.model.Interfaces

import de.htwg.se.toybrokersludo.model.{Field, Move}

trait PlayerInterface {
    def possibleMoves(diceroll: Int, field: Field): List[Move]
  
    def defaultField(): List[Int]

    def startField(): Int

    def endFields(): List[Int]

    def lastField(): Int

    def fooFields(): List[Int]

    def playerString: String    
}