package de.htwg.se.toybrokersludo.model.PlayerBaseImpl

import de.htwg.se.toybrokersludo.model.{Move, PlayerInterface, FieldInterface}

import scala.language.postfixOps

/*
class GPlayer(number: Int) {
  
  val player: List[PlayerInterface] = List(GreenPlayer,RedPlayer,BluePlayer,YellowPlayer)
  def getPlayer(): PlayerInterface
    player(number)
}
*/

class Player(number: Int) extends PlayerInterface {

  def defaultField() = number match
    case 0 => GreenPlayer.defaultField()
    case 1 => RedPlayer.defaultField()
    case 2 => BluePlayer.defaultField()
    case 3 => YellowPlayer.defaultField()

  def startField() = number match
    case 0 => GreenPlayer.startField()
    case 1 => RedPlayer.startField()
    case 2 => BluePlayer.startField()
    case 3 => YellowPlayer.startField()

  def endFields() = number match
    case 0 => GreenPlayer.endFields()
    case 1 => RedPlayer.endFields()
    case 2 => BluePlayer.endFields()
    case 3 => YellowPlayer.endFields()

  def lastField() = number match
    case 0 => GreenPlayer.lastField()
    case 1 => RedPlayer.lastField()
    case 2 => BluePlayer.lastField()
    case 3 => YellowPlayer.lastField()

  def fooFields() = number match
    case 0 => GreenPlayer.fooFields()
    case 1 => RedPlayer.fooFields()
    case 2 => BluePlayer.fooFields()
    case 3 => YellowPlayer.fooFields()

  def playerString() = number match
    case 0 => GreenPlayer.playerString
    case 1 => RedPlayer.playerString
    case 2 => BluePlayer.playerString
    case 3 => YellowPlayer.playerString

  def toString() = number match
    case 0 => GreenPlayer.toString()
    case 1 => RedPlayer.toString()
    case 2 => BluePlayer.toString()
    case 3 => YellowPlayer.toString()

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
      case Some(result: Int) => result.equals(move2.number)
      case None => true)
      && !defaultField().contains(move.number))
      .map((move: Move) => move.copy(number = add(move.number, diceroll) match
        case Some(result: Int) => result
      ))
    possible

  def getTokens(field: FieldInterface) =
    field.getMatrix.getToken.filter((move: Move) => move.token.getColor().equals(playerString))


  def add(from: Int, dice: Int): Option[Int] =
    val result = from + dice
    if (goOverEnd(from, result))
      result - lastField() <= endFields().size match
        case true => Some(endFields()(result - lastField() - 1))
        case false => None
    else if (result > 59) Some(result - 40)
    else Some(result)

  def goOverEnd(from: Int, to: Int) =
    var fromNew = from
    var toNew = to
    if (fooFields().contains(from - 40)) fromNew = from - 40
    if (fooFields().contains(to - 40)) toNew = to - 40
    from < lastField() && to > lastField()

}

private object GreenPlayer {
  override def defaultField(): List[Int] = List(0, 1, 2, 3)
  override def startField(): Int = 20
  override def endFields(): List[Int] = List(70, 71, 72, 73)
  override def lastField(): Int = 59
  override def fooFields(): List[Int] = Nil
  override def playerString = "G"
  override def toString(): String = "Green Player"
}

private object RedPlayer {
  override def defaultField(): List[Int] = List(4, 5, 6, 7)
  override def startField(): Int = 30
  override def endFields(): List[Int] = List(74, 75, 76, 77)
  override def lastField(): Int = 29
  override def fooFields(): List[Int] = (20 to 29).toList
  override def playerString = "R"
  override def toString(): String = "Red Player"
}

private object BluePlayer {
  override def defaultField(): List[Int] = List(12, 13, 14, 15)
  override def startField(): Int = 40
  override def endFields(): List[Int] = List(78, 79, 80, 81)
  override def lastField(): Int = 39
  override def fooFields(): List[Int] = (30 to 39).toList
  override def playerString = "B"
  override def toString(): String = "Blue Player"
}

private object YellowPlayer {
  override def defaultField(): List[Int] = List(8, 9, 10, 11)
  override def startField(): Int = 50
  override def endFields(): List[Int] = List(82, 83, 84, 85)
  override def lastField(): Int = 49
  override def fooFields(): List[Int] = (40 to 49).toList
  override def playerString = "Y"
  override def toString(): String = "Yellow Player"
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
