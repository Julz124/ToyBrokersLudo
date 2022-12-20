package de.htwg.se.toybrokersludo.model.FieldBaseImpl

import de.htwg.se.toybrokersludo.model.{FieldInterface, Stone, Token, Move}
import de.htwg.se.toybrokersludo.util.PlayerBaseImpl.{BluePlayer, GreenPlayer, RedPlayer, YellowPlayer}
import de.htwg.se.toybrokersludo.util.PlayerInterface

case class Field(matrix: Matrix = Matrix() , player: PlayerInterface = GreenPlayer, playerNumber: Int = 0, dice: Int = 6, shouldDice: Boolean = true) extends FieldInterface {

  def nextPlayer(): FieldInterface = //Interator Pattern
    player match
      case GreenPlayer if (playerNumber < 2) => this.copy(player = GreenPlayer)
      case GreenPlayer => this.copy(player = RedPlayer)
      case RedPlayer if (playerNumber < 3) => this.copy(player = GreenPlayer)
      case RedPlayer => this.copy(player = BluePlayer)
      case BluePlayer if (playerNumber < 4) => this.copy(player = GreenPlayer)
      case BluePlayer => this.copy(player = YellowPlayer)
      case YellowPlayer => this.copy(player = GreenPlayer)

  def numberPlayer(number: Int): FieldInterface =
    this.copy(playerNumber = number)

  def dice(dice: Int): FieldInterface = this.copy(dice = dice)

  def invertDice(): FieldInterface = this.copy(shouldDice = !shouldDice)

  val eol: String = "\n"

  def horizontal(list: List[Stone], size: Int): List[String] =
    list.map((s: Stone) => if (s.isAPlayField) "+" + "-" * size + "+" else " " + " " * size + " ")

  def vertical(list: List[Stone], size: Int): List[String] =
    list.map((s: Stone) => if (s.isAPlayField) "|" + player(size, s.token) + "|" else " " + " " * size + " ")

  def player(size: Int, player: Option[Token]): String =
    player match {
      case Some(player) => " " * (size / 2 - 1) + player.toString + " " * (size / 2 - 1)
      case None => " " * size
    }

  def mash(map: List[List[Stone]] = matrix.getMap, size: Int = 4): List[String] =
    for (list <- map) yield horizontal(list, size).mkString + eol + vertical(list, size).mkString + eol + horizontal(list, size).mkString + eol

  def put(move: Move): Field = this.copy(matrix.put(move))

  def move(move: Move): Field = this.copy(matrix.move(move))

  override def toString: String =
    mash().mkString.appendedAll(shouldDice match
      case true => player.toString + " have to dice"
      case false => player.toString + "have to move")

  def getMatrix: MatrixInterface = matrix

  def getPlayer: PlayerInterface = player

  def getDice: Int = dice

  def getShouldDice: Boolean = shouldDice
}
