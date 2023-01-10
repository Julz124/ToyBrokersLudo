package de.htwg.se.toybrokersludo

import javax.naming.ldap.Control

import de.htwg.se.toybrokersludo.model.FieldInterface
import de.htwg.se.toybrokersludo.model.FieldBaseImpl.Field

import de.htwg.se.toybrokersludo.model.FileIO.FileIOInterface
import de.htwg.se.toybrokersludo.model.FileIO.JsonImpl.FileIo

import de.htwg.se.toybrokersludo.controller.ControllerInterface
import de.htwg.se.toybrokersludo.controller.controllerBaseImpl.Controller

import de.htwg.se.toybrokersludo.aview.{GUI, TUI}

@main def run: Unit =
  println("welcome to Toybrokersludo")
  
  given FieldInterface = Field()
  val field : Field = Field()

  given FileIOInterface : FileIo()
  val fileIO : FileIo = FileIo()
  
  given ControllerInterface = Controller(using field) (using fileIO)
  val controller : Controller = Controller(using field) (using fileIO)
  
  val gui = GUI(using controller)
  val tui = TUI(using controller)

  gui.run(true)
  tui.run()