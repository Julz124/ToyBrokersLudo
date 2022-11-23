package de.htwg.se.toybrokersludo.model

case class Token(number: Int, color: String):
  override def toString(): String =
    color + number