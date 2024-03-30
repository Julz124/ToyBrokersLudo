package de.htwg.se.toybrokersludo

import de.htwg.se.toybrokersludo.FileIO.FileIO
import de.htwg.se.toybrokersludo.FileIO.impl.JsonFileIO
import de.htwg.se.toybrokersludo.aview.Tui
import de.htwg.se.toybrokersludo.controller.Controller
import de.htwg.se.toybrokersludo.controller.impl.DefaultController
import model.{GameField, Move}

import javax.naming.ldap.Control

@main def main(): Unit =
  val fileIO: FileIO = JsonFileIO()
  val controller: Controller = DefaultController(using fileIO)
  val tui = Tui(using controller)

  tui.inputLoop()