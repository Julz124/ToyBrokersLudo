package de.htwg.se.toybrokersludo.model

case class Field(matrix: Matrix) {
  
  val eol: String = "\n"

  def horizontal(list: Vector[Stone], size: Int): Vector[String] =
    list.map((s: Stone) => if (s.isAPlayField) "+" + "-" * size + "+" else " " + " " * size + " ")

  def vertical (list: Vector[Stone], size: Int): Vector[String] =
    list.map((s: Stone) => if(s.isAPlayField) "|" + player(size, s.player) + "|" else " " + " " * size + " ")

  def player(size : Int, player : Option[Player]) : String =
    player match {
      case Some(player) => " " * (size - 2) + player.toString
      case None => " " * size
    }

  def mash(map: Vector[Vector[Stone]] = matrix.map, size: Int = 4): Vector[String] =
    for (list <- map) yield horizontal(list, size).mkString + eol + vertical(list, size).mkString + eol + horizontal(list, size).mkString + eol

  def put(stone: Stone, index : Int) = copy(matrix.replaceCell(index, stone))


  override def toString: String =
    mash().mkString
}
