package de.htwg.se.toybrokersludo

import de.htwg.se.toybrokersludo.aview.{GUI, TUI}
import de.htwg.se.toybrokersludo.model.{FieldBaseImpl, FieldInterface}
import de.htwg.se.toybrokersludo.controller.ControllerInterface
import de.htwg.se.toybrokersludo.controller.controllerBaseImpl
import de.htwg.se.toybrokersludo.controller.controllerBaseImpl.Controller
import de.htwg.se.toybrokersludo.model.FieldBaseImpl.Field

import javax.naming.ldap.Control

@main def run: Unit =
  println("welcome to Toybrokersludo")
  
  given FieldInterface = Field()
  val field : Field = Field()
  
  given ControllerInterface = Controller(using Field())
  val controller : Controller = Controller(using field)
  
  val gui = GUI(controller)
  val tui = TUI(controller)
  gui.run
  tui.run
