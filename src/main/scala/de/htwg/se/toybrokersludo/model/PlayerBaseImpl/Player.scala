package de.htwg.se.toybrokersludo.model.PlayerBaseImpl

import de.htwg.se.toybrokersludo.model.{Move, PlayerInterface, FieldInterface}

import scala.language.postfixOps
//Strategy-Pattern

abstract class Player extends PlayerInterface {

  def possibleMoves(diceroll: Int, field: FieldInterface): List[Move] =
    val tokens: List[Move] = field.getMatrix.getToken
    var possible: List[Move] = Nil
    if (diceroll == 6) {
      possible = possible ::: tokens.filter((move: Move) => (move.token.getColor().equals(playerString)
        && defaultField().contains(move.number)
        && !getTokens(field).exists((move: Move) => move.number == startField())))
        .map((move: Move) => move.copy(number = startField()))
    }
    possible = possible ::: tokens.filter((move: Move) => move.token.getColor().equals(playerString)
      && !getTokens(field).exists((move2: Move) => add(move.number, diceroll) match
        case Some(result : Int) => result.equals(move2.number)
        case None => true)
      && !defaultField().contains(move.number))
      .map((move: Move) => move.copy(number = add(move.number, diceroll) match
        case Some(result : Int) => result
      ))
    possible

  def getTokens(field: FieldInterface) =
    field.getMatrix.getToken.filter((move : Move) => move.token.getColor().equals(playerString))


  def add (from: Int, dice: Int): Option[Int] =
    val result = from + dice
    if (goOverEnd(from, result))
      result - lastField() <= endFields().size match
        case true => Some(endFields()(result - lastField() - 1))
        case false => None
    else if (result >GreenPlayer.lastField()) Some(result - 40)
    else Some(result)


  def goOverEnd(from: Int, to: Int) =
    var fromNew = from
    var toNew = to
    if(fooFields().contains(from - 40)) fromNew = from - 40
    if(fooFields().contains(to - 40)) toNew = to - 40
    from < lastField() && to > lastField()
}


object GreenPlayer extends Player {
  override def defaultField(): List[Int] = List(0, 1, 2, 3)

  override def startField(): Int = 20

  override def endFields(): List[Int] = List(70, 71, 72, 73)

  override def lastField(): Int = 59

  override def fooFields(): List[Int] = Nil

  override def playerString = "G"

  override def toString() : String = "Green Player"
}

object RedPlayer extends Player {
  override def defaultField(): List[Int] = List(4, 5, 6, 7)

  override def startField(): Int = 30

  override def endFields(): List[Int] = List(74, 75, 76, 77)

  override def lastField(): Int = 29

  override def fooFields(): List[Int] = (20 to 30).toList

  override def playerString = "R"

  override def toString() : String = "Red Player"

}

object BluePlayer extends Player {
  override def defaultField(): List[Int] = List(12, 13, 14, 15)

  override def startField(): Int = 40

  override def endFields(): List[Int] = List(78, 79, 80, 81)

  override def lastField(): Int = 49

  override def fooFields(): List[Int] = (39 to 40).toList

  override def playerString = "B"

  override def toString() : String = "Blue Player"
}

object YellowPlayer extends Player {
  override def defaultField(): List[Int] = List(8, 9, 10, 11)

  override def startField(): Int = 50

  override def endFields(): List[Int] = List(82, 83, 84, 85)

  override def lastField(): Int = 39

  override def fooFields(): List[Int] = (40 to 50).toList

  override def playerString = "Y"

  override def toString() : String = "YellowPlayer Player"
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
