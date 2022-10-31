package de.htwg.se.toybrokersludo.controller

import de.htwg.se.toybrokersludo.model.Field
import de.htwg.se.toybrokersludo.model.Move
import de.htwg.se.toybrokersludo.model.Matrix
import de.htwg.se.toybrokersludo.model.Player
import de.htwg.se.toybrokersludo.aview.TUI


case class Controller(var field : Field) {


  def doAndPublish(move : Move, tui: TUI): Unit =
    field


}
