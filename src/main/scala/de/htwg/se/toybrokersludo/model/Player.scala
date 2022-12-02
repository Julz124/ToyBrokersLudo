package de.htwg.se.toybrokersludo.model

import scala.language.postfixOps
//Strategy-Pattern

abstract class Player {
  def defaultField(): List[Int]

  def startField(): Int

  def endFields(): List[Int]

  def lastField() : Int

  def playerString: String


  def possibleMoves(diceroll: Int, field: Field): List[Move] =
    val tokens: List[Move] = field.matrix.getToken
    var possible: List[Move] = Nil
    if (diceroll == 6) {
      possible = possible ::: tokens.filter((move: Move) => (move.player.getColor().equals(playerString)
        && defaultField().contains(move.number)
        && !getTokens(field).exists((move : Move) => move.number == startField())))
        .map((move: Move) => move.copy(number = startField()))
    }
    possible = possible ::: tokens.filter((move: Move) => move.player.getColor().equals(playerString)
      && !getTokens(field).exists((move2 : Move) => move.number + diceroll == move2.number)
      && !defaultField().contains(move.number))
      .map((move: Move) => move.copy(number = move.number + diceroll))
    possible


  def getTokens(field: Field) =
    field.matrix.getToken.filter((move : Move) => move.player.getColor().equals(playerString))


  def add(from : Int, dice : Int) : Int =
    if (from + dice <= lastField()) lastField() - from + endFields().reverse.last
    2
}

object GreenPlayer extends Player {
  override def defaultField(): List[Int] = List(0, 1, 2, 3)

  override def startField(): Int = 20

  override def endFields(): List[Int] = List(70, 71, 72, 73)

  override def lastField(): Int = 59

  override def playerString = "G"
}

object RedPlayer extends Player {
  override def defaultField(): List[Int] = List(4, 5, 6, 7)

  override def startField(): Int = 30

  override def endFields(): List[Int] = List(74, 75, 76, 77)

  override def lastField(): Int = 29

  override def playerString = "R"

}

object BluePlayer extends Player {
  override def defaultField(): List[Int] = List(12, 13, 14, 15)

  override def startField(): Int = 40

  override def endFields(): List[Int] = List(78, 79, 80, 81)

  override def lastField(): Int = 49

  override def playerString = "B"
}

object YellowPlayer extends Player {
  override def defaultField(): List[Int] = List(8, 9, 10, 11)

  override def startField(): Int = 50

  override def endFields(): List[Int] = List(82, 83, 84, 85)

  override def lastField(): Int = 39

  override def playerString = "Y"
}



