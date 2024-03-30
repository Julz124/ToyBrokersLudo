package de.htwg.se.toybrokersludo

import FileIO.{FileIO, JsonFileIO}
import aview.Tui
import controller.{Controller, DefaultController}
import model.{GameField, Move}

import javax.naming.ldap.Control

@main def main(): Unit =
  val fileIO: FileIO = JsonFileIO()
  val controller: Controller = DefaultController(using fileIO)
  val tui = Tui(using controller)

  tui.inputLoop()