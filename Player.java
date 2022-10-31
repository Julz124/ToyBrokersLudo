package de.htwg.se.toybrokersludo.model

class Player(color : Color, num : Int) {
  override def toString: String = num + color.toString
}


enum Color:
  case Yellow
  case Blue
  case Green
  case Red