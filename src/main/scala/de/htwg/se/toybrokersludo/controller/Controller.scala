package de.htwg.se.toybrokersludo.controller

import de.htwg.se.toybrokersludo.model.{Field, Move, Player}
import de.htwg.se.toybrokersludo.aview.TUI


case class Controller(var field : Field) {


  def doAndPublish(doThis: Move => Field, move: Move, tui: TUI) =
    field = doThis(move)
    tui.update()

  def put(move: Move): Field =
    field.put(move)

}
