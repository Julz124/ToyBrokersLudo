package de.htwg.se.toybrokersludo.model.Interfaces

import de.htwg.se.toybrokersludo.model.*

trait FieldInterface {
    def put(move: Move): Field
    def numberPlayer(number : Int) : Field
    def dice (dice : Int) : Field
    def invertDice() : Field
    def nextPlayer(player2: Player): Field
    def move(move: Move): Field
    def toString: String
}