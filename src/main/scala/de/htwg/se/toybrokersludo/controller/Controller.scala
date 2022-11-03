package de.htwg.se.toybrokersludo.controller

import de.htwg.se.toybrokersludo.model.{Field, Move, Player}


case class Controller(var field : Field) {


  def doAndPublish(doThis: Move => Field, move: Move, tui: TUI) =
    field = doThis(move)
    tui.update()

  def put(move: Move): Field =
    field = field.put(move)

}
