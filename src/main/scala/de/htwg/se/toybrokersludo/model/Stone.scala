package de.htwg.se.toybrokersludo.model


class Stone(isAPlayField: Boolean, index : Int, player : Option[Player]) {

  def printField(index : Int): Unit ={

  }

  def field(size : Int, player : Player): List[List[String]] = {
    val map: List[List[String]];
    if(Player = -1)
      list.map[0]("+" + "-" * size + "+")
      list.map[1]("|" + " " * size + "|")
      list.map[2]("+" + "-" * size + "+")
      
    if (Player = 1)
      list.map[0]("+" + "-" * size + "+")
      list.map[1]("|" + " " * size + "|")
      list.map[2]("+" + "-" * size + "+")
  }

  def noField()

}
