package de.htwg.se.toybrokersludo.model.FileIO

import de.htwg.se.toybrokersludo.model.FieldInterface

trait FileIOInterface {
  
  def save(field : FieldInterface, target : String) : Unit
  
  def load(source : String) : FieldInterface
  
  def getTargets() : List[String]
}
