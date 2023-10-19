package de.htwg.se.toybrokersludo.model

import java.awt.Color

trait Token {
  def toString(): String
  def getNumber() : Int
  def getColor() : String
  
  def equals(token : Token) : Boolean =
    getNumber() == token.getNumber() && getColor().equals(token.getColor())
}

object PlayToken {
  def apply(number : Int, color : String) : Token = {
    (List (1, 2, 3, 4).contains (number) && List ("G", "R", "B", "Y").contains (color)) match {
      case true => new Valid(number, color)
    }
  }
}

private class Valid(number : Int, color : String) extends Token {
  override def toString(): String =
    color + number

  override def getNumber(): Int = number

  override def getColor(): String = color
}





