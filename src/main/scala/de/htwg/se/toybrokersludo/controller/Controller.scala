package de.htwg.se.toybrokersludo.controller

import de.htwg.se.toybrokersludo.model.{Field, Move, Player}
import de.htwg.se.toybrokersludo.aview.TUI
import de.htwg.se.toybrokersludo.util.Observable


case class Controller(var field : Field) extends Observable{


  def startup(spieler : Int) : Field =

    var Green: List[Move] = List(Move(Player(1, "G"), 0), Move(Player(2, "G"), 1), Move(Player(3, "G"), 2), Move(Player(4, "G"), 3))
    var Red: List[Move] = List(Move(Player(1, "R"), 4), Move(Player(2, "R"), 5), Move(Player(3, "R"), 6), Move(Player(4, "R"), 7))
    var Yellow: List[Move] = List(Move(Player(1, "Y"), 8), Move(Player(2, "Y"), 9), Move(Player(3, "Y"), 10), Move(Player(4, "Y"), 11))
    var Blue: List[Move] = List(Move(Player(1, "B"), 12), Move(Player(2, "B"), 13), Move(Player(3, "B"), 14), Move(Player(4, "B"), 15))

    spieler match{
      case 1 => field.put(Green)
      case 2 => field.put(Green)
                field.put(Red)
      case 3 => field.put(Green)
                field.put(Red)
                field.put(Yellow)
      case 4 => field.put(Green)
                field.put(Red)
                field.put(Yellow)
                field.put(Blue)
    }


  def doAndPublish(doThis: Move => Field, move: Move) =
    field = doThis(move)
    notifyObservers

  def put(move: Move): Field =
    field.put(move)

}
