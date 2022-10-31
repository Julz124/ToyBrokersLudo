package de.htwg.se.toybrokersludo.model

class Field(matrix: Matrix) {


  val eol: String = "\n"

  def horizontal(list: Vector[Stone], size: Int): List[String] =
    list.map((s: Stone) => if (s.isAField) "+" + "-" * size + "+" else " " + " " * size + " ")
  
  def vertical (list: Vector[Stone], size: Int): List[String] =
    list.map((s: Stone) => if(s.isAField) "|" + player(size, s.player) + "|" else " " + " " * size + " ")
  
  def player(size : Int, player : Option[Player]) : String =
    player match {
      case Some(player) => " " * (size - 2) + player.toString
      case None => " " * size
    }

  def mash(map: Vector[Vector[Stone]] = matrix, size: Int = 4): Vector[Stone] =
    for (list <- matrix) yield horizontal(list, size).mkString + eol + vertical(list, size).mkString + eol + horizontal(list, size).mkString + eol
  
  override def toString: String =
    mash().mkString
}
