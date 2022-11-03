package de.htwg.se.toybrokersludo.model

case class Player(number: Int, color: String):
  override def toString(): String =
    color + number
