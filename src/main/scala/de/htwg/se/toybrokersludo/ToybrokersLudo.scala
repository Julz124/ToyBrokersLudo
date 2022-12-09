package de.htwg.se.toybrokersludo

import de.htwg.se.toybrokersludo.aview.TUI
import de.htwg.se.toybrokersludo.controller.Controller
import de.htwg.se.toybrokersludo.model.{Field, Matrix}
import de.htwg.se.toybrokersludo.aview.GUI

@main def run: Unit =
  println("welcome to Toybrokersludo")
  val field = Field(Matrix())
  val controller = Controller(field)
  val gui = GUI(controller)
  val tui = TUI(controller)
  gui.run
  tui.run
