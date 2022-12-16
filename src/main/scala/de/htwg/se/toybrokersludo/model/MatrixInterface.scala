package de.htwg.se.toybrokersludo.model

import de.htwg.se.toybrokersludo.model.MatrixInterface

trait MatrixInterface {
  def put(move: Move): MatrixInterface

  def move(move: Move): MatrixInterface

  def getToken: List[Move]

  def getStone(index: Int): Stone
  
  def getMap : List[List[Stone]]
}
