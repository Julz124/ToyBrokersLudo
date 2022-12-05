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
    println(diceroll)
    val tokens: List[Move] = field.matrix.getToken
    var possible: List[Move] = Nil
    if (diceroll == 6) {
      possible = possible ::: tokens.filter((move: Move) => (move.token.getColor().equals(playerString)
        && defaultField().contains(move.number)
        && !getTokens(field).exists((move : Move) => move.number == startField())))
        .map((move: Move) => move.copy(number = startField()))
    }
    possible = possible ::: tokens.filter((move: Move) => move.token.getColor().equals(playerString)
      && !getTokens(field).exists((move2 : Move) => move.number + diceroll == move2.number)
      && !defaultField().contains(move.number))
      .map((move: Move) => move.copy(number = move.number + diceroll))
    possible

  def getTokens(field: Field) =
    field.matrix.getToken.filter((move : Move) => move.token.getColor().equals(playerString))

  def add(from: Int, dice: Int): Option[Int] =
    val result = from + dice
    val lastindex = lastField() + 1
    playerString match
      case "G" =>
        if (result <= lastField()) Option(result)
        else if (result > lastField() && result - lastindex <= 3) Option(endFields()(result - lastindex))
        else None
      case "R" =>
        if (result <= 59 && (30 to 59 contains from)) Option(result)
        else if (result > 59 && (30 to 59 contains from)) Option(result - 40)
        else if (result <= lastField() && (20 to 29 contains from)) Option(result)
        else if (result > lastField() && (20 to 29 contains from) && result - lastindex <= 3) Option(endFields()(result - lastindex))
        else None
      case "B" =>
        if (result <= 59 && (40 to 59 contains from)) Option(result)
        else if (result > 59 && (40 to 59 contains from)) Option(result - 40)
        else if (result <= lastField() && (30 to 39 contains from)) Option(result)
        else if (result > lastField() && (30 to 39 contains from) && result - lastindex <= 3) Option(endFields()(result - lastindex))
        else None
      case "Y" =>
        if (result <= 59 && (50 to 59 contains from)) Option(result)
        else if (result > 59 && (50 to 59 contains from)) Option(result - 40)
        else if (result <= lastField() && (40 to 49 contains from)) Option(result)
        else if (result > lastField() && (40 to 49 contains from) && result - lastindex <= 3) Option(endFields()(result - lastindex))
        else None
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

/*

+----+      +----+      +----++----++----+      +----+      +----+
| G0 |      | G1 |      | 28 || 29 || 30 |      | R4 |      | R5 |
+----+      +----+      +----++----++----+      +----+      +----+
                        +----++----++----+
                        | 27 || 74 || 31 |
                        +----++----++----+
+----+      +----+      +----++----++----+      +----+      +----+
| G2 |      | G3 |      | 26 || 75 || 32 |      | R6 |      | R7 |
+----+      +----+      +----++----++----+      +----+      +----+
                        +----++----++----+
                        | 25 || 76 || 33 |
                        +----++----++----+
+----++----++----++----++----++----++----++----++----++----++----+
| 20 || 21 || 22 || 23 || 24 || 77 || 34 || 35 || 36 || 37 || 38 |
+----++----++----++----++----++----++----++----++----++----++----+
+----++----++----++----++----+      +----++----++----++----++----+
| 59 || 70 || 71 || 72 || 73 |      | 81 || 80 || 79 || 78 || 39 |
+----++----++----++----++----+      +----++----++----++----++----+
+----++----++----++----++----++----++----++----++----++----++----+
| 58 || 57 || 56 || 55 || 54 || 85 || 44 || 43 || 42 || 41 || 40 |
+----++----++----++----++----++----++----++----++----++----++----+
                        +----++----++----+
                        | 53 || 84 || 45 |
                        +----++----++----+
+----+      +----+      +----++----++----+      +----+      +----+
| Y8 |      | Y9 |      | 52 || 83 || 46 |      | B12|      | B13|
+----+      +----+      +----++----++----+      +----+      +----+
                        +----++----++----+
                        | 51 || 82 || 47 |
                        +----++----++----+
+----+      +----+      +----++----++----+      +----+      +----+
| Y10|      | Y11|      | 50 || 49 || 48 |      | B14|      | B15|
+----+      +----+      +----++----++----+      +----+      +----+

*/



