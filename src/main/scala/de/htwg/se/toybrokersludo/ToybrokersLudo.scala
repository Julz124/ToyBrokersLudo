package de.htwg.se.toybrokersludo

import de.htwg.se.toybrokersludo.aview.TUI
import de.htwg.se.toybrokersludo.model.FieldBaseImpl
import de.htwg.se.toybrokersludo.aview.GUI
import de.htwg.se.toybrokersludo.controller.controllerBaseImpl
import de.htwg.se.toybrokersludo.controller.controllerBaseImpl.Controller
import de.htwg.se.toybrokersludo.model.FieldBaseImpl.Field
import de.htwg.se.toybrokersludo.model.MatrixBaseImpl.Matrix

@main def run: Unit =
  println("welcome to Toybrokersludo")
  val field = FieldBaseImpl.Field(Matrix())
  val controller = controllerBaseImpl.Controller(field)
  val gui = GUI(controller)
  val tui = TUI(controller)
  gui.run
  tui.run
