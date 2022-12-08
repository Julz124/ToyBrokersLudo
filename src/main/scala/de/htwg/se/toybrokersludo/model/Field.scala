package de.htwg.se.toybrokersludo.model

case class Field(matrix: Matrix, player : Player = GreenPlayer, playerNumber : Int = 0, dice : Int = 6) {

  def nextPlayer(player2: Player = player): Field = //Interator Pattern
    player2 match
      case GreenPlayer if (playerNumber < 2) => this.copy(player = GreenPlayer)
      case GreenPlayer => this.copy(player = RedPlayer)
      case RedPlayer if (playerNumber < 3) => this.copy(player = GreenPlayer)
      case RedPlayer => this.copy(player = BluePlayer)
      case BluePlayer if (playerNumber < 4) => this.copy(player = GreenPlayer)
      case BluePlayer => this.copy(player = YellowPlayer)
      case YellowPlayer => this.copy(player = GreenPlayer)

  def numberPlayer(number : Int) : Field =
    this.copy(playerNumber = number)


  
  val eol: String = "\n"

  def horizontal(list: List[Stone], size: Int): List[String] =
    list.map((s: Stone) => if (s.isAPlayField) "+" + "-" * size + "+" else " " + " " * size + " ")

  def vertical(list: List[Stone], size: Int): List[String] =
    list.map((s: Stone) => if (s.isAPlayField) "|" + player(size, s.player) + "|" else " " + " " * size + " ")

  def player(size: Int, player: Option[Token]): String =
    player match {
      case Some(player) => " " * (size / 2 - 1) + player.toString + " " * (size / 2 - 1)
      case None => " " * size
    }

  def mash(map: List[List[Stone]] = matrix.map, size: Int = 4): List[String] =
    for (list <- map) yield horizontal(list, size).mkString + eol + vertical(list, size).mkString + eol + horizontal(list, size).mkString + eol

  def put(move: Move): Field = this.copy(matrix.put(move))

  def move(move: Move): Field = this.copy(matrix.move(move))

  override def toString: String =
    mash().mkString
}
