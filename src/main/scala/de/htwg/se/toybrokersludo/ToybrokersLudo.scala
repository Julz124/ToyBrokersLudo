package de.htwg.se.toybrokersludo

import de.htwg.se.toybrokersludo.controller.Controller
import de.htwg.se.toybrokersludo.controller.impl.DefaultController
import de.htwg.se.toybrokersludo.model.{GameField, Move}

import javax.naming.ldap.Control

@main def main(): Unit =
  val controller: Controller = DefaultController()
  val tui = de.htwg.se.toybrokersludo.neu.view.Tui(using controller)

  tui.inputLoop()