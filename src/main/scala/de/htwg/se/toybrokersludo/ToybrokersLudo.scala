package de.htwg.se.toybrokersludo

import model.Field
import model.Matrix
import controller.Controller
import aview.TUI

@main def run: Unit =
  println("welcome to Toybrokersludo")
  val field = Field(Matrix())
  val controller = Controller(field)
  val tui = TUI(controller)
  tui.run()





