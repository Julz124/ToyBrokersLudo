package de.htwg.se.toybrokersludo.controller

import de.htwg.se.toybrokersludo.model.{Field, Move, Player}
import de.htwg.se.toybrokersludo.aview.TUI
import de.htwg.se.toybrokersludo.util.Observable


case class Controller(var field : Field) extends Observable{


  def doAndPublish(doThis: Move => Field, move: Move) =
    field = doThis(move)
    notifyObservers

  def put(move: Move): Field =
    field.put(move)

}
