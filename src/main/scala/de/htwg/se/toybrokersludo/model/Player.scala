package de.htwg.se.toybrokersludo.model

import scala.language.postfixOps


case class Player(number : Int, color : String) {
  override def toString: String = color.toString + number
}

  


