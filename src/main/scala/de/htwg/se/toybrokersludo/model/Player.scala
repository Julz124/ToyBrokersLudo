package de.htwg.se.toybrokersludo.model



case class Player(number : Int, color: Color) {
  override def toString: String = color.toString + number
}

enum Color:
  case B
  case R
  case Y
  case G
  


