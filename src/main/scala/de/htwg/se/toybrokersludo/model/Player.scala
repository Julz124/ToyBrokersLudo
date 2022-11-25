package de.htwg.se.toybrokersludo.model
//Strategy-Pattern

abstract class Player {
  def defaultField(): List[Int]

  def startField(): Int

  def endFields(): List[Int]

  def playerString: String

  /*
  def possibleMoves(diceroll: Int, field: Field): List[Move] = {
    val tokens : List[Move] = field.matrix.getToken()
    val possible : List[Move] = Nil
    diceroll match {
      case 6 =>
        for (move <- tokens if move.player.color == playerString && move.number == defaultField()[0] | defaultField()[1] | defaultField()[2] | defaultField()[3])
          possible.:: Move(move.player, startField())
      case _ =>
        for (move <- tokens if move.player.color == playerString)
          for (move1 <- tokens if move1.player.color == playerString && move.number + diceroll != move1.number)
            possible.:: Move(move.player, move.number + diceroll)
    }
    possible
  }
  */
}

object GreenPlayer extends Player {
  override def defaultField(): List[Int] = List(0, 1, 2, 3)

  override def startField(): Int = 20

  override def endFields(): List[Int] = List(70, 71, 72, 73)

  override def playerString = "G"
}

object RedPlayer extends Player {
  override def defaultField(): List[Int] = List(4, 5, 6, 7)

  override def startField(): Int = 30

  override def endFields(): List[Int] = List(74, 75, 76, 77)

  override def playerString = "R"

}

object BluePlayer extends Player {
  override def defaultField(): List[Int] = List(12, 13, 14, 15)

  override def startField(): Int = 40

  override def endFields(): List[Int] = List(78, 79, 80, 81)

  override def playerString = "B"
}

object YellowPlayer extends Player {
  override def defaultField(): List[Int] = List(8, 9, 10, 11)

  override def startField(): Int = 50

  override def endFields(): List[Int] = List(82, 83, 84, 85)

  override def playerString = "Y"
}



