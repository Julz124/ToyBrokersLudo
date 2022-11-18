package de.htwg.se.toybrokersludo.model

case class Field(var matrix: Matrix) {

  val player : Int = 0
  val turn : Int = 0;


  val eol: String = "\n"

  def horizontal(list: List[Stone], size: Int): List[String] =
    list.map((s: Stone) => if (s.isAPlayField) "+" + "-" * size + "+" else " " + " " * size + " ")

  def vertical(list: List[Stone], size: Int): List[String] =
    list.map((s: Stone) => if (s.isAPlayField) "|" + player(size, s.player) + "|" else " " + " " * size + " ")

  def player(size: Int, player: Option[Player]): String =
    player match {
      case Some(player) => " " * (size / 2 - 1) + player.toString + " " * (size / 2 - 1)
      case None => " " * size
    }

  def mash(map: List[List[Stone]] = matrix.map, size: Int = 4): List[String] =
    for (list <- map) yield horizontal(list, size).mkString + eol + vertical(list, size).mkString + eol + horizontal(list, size).mkString + eol


  def put(move: Move): Field = this.copy(matrix.put(move))

  def put(moves : List[Move]) =
    for(move <- moves) matrix = matrix.put(move)
    this


  override def toString: String =
    mash().mkString
}
