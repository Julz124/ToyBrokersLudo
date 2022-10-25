package de.htwg.se.toybrokersludo

class Field {
  val map: List[List[Boolean]] = List(
    List(true, false, true, false, true, true, true, false, true, false, true),
    List(false, false, false, false, true, true, true, false, false, false, false),
    List(true, false, true, false, true, true, true, false, true, false, true),
    List(false, false, false, false, true, true, true, false, false, false, false),
    List(true, true, true, true, true, true, true, true, true, true, true),
    List(true, true, true, true, true, false, true, true, true, true, true),
    List(true, true, true, true, true, true, true, true, true, true, true),
    List(false, false, false, false, true, true, true, false, false, false, false),
    List(true, false, true, false, true, true, true, false, true, false, true),
    List(false, false, false, false, true, true, true, false, false, false, false),
    List(true, false, true, false, true, true, true, false, true, false, true))

  val eol: String = "\n"

  def horizontal(list: List[Boolean], size: Int): List[String] =
    list.map((b: Boolean) => if (b) "+" + "-" * size + "+" else " " + " " * size + " ")

  def vertical(list: List[Boolean], size: Int): List[String] =
    list.map((b: Boolean) => if (b) "|" + " " * size + "|" else " " + " " * size + " ")

  def mash(map: List[List[Boolean]] = map, size: Int = 4): List[String] =
    for (list <- map) yield horizontal(list, size).mkString + eol + vertical(list, size).mkString + eol + horizontal(list, size).mkString + eol

  def toString2(): String =
    mash().mkString

  print(toString2())
}
