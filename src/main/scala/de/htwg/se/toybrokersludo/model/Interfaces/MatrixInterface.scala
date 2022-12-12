package de.htwg.se.toybrokersludo.model.Interfaces

import de.htwg.se.toybrokersludo.model.{Matrix, Move, Stone}
trait MatrixInterface {
  def put(move : Move) : Matrix
  def move(move : Move) : Matrix
  def getToken: List[Move]
  def getStone(index : Int): Stone
}
