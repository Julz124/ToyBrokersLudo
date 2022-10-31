package de.htwg.se.toybrokersludo.model

class Field(matrix: Matrix) {


  val eol: String = "\n"

  def horizontal(list: List[Boolean], size: Int): List[String] =
    list.map((b: Boolean) => if (b) "+" + "-" * size + "+" else " " + " " * size + " ")

  def vertical(list: List[Boolean], size: Int): List[String] =
    list.map(s: Stone) => if(s.isAField && s.player == null) "|" + " " * size + "|"
      else if(s.isAfield && player != null) "|" + " " s.player.toString + "|"
      else " " + " " * size + " ")

  def mash(map: List[List[Boolean]] = map, size: Int = 4): List[String] =
    for (list <- map) yield horizontal(list, size).mkString + eol + vertical(list, size).mkString + eol + horizontal(list, size).mkString + eol
  
  override def toString: String =
    mash().mkString
}
