package de.htwg.se.toybrokersludo

import javax.naming.ldap.Control
import de.htwg.se.toybrokersludo.model.FieldInterface
import de.htwg.se.toybrokersludo.model.FieldBaseImpl.Field
import de.htwg.se.toybrokersludo.model.FileIO.FileIOInterface
import de.htwg.se.toybrokersludo.model.FileIO.XmlImpl.FileIo
import de.htwg.se.toybrokersludo.controller.ControllerInterface
import de.htwg.se.toybrokersludo.controller.controllerBaseImpl.Controller
import de.htwg.se.toybrokersludo.aview.{GUI, TUI}
import de.htwg.se.toybrokersludo.neu.model.{GameField, Move}

@main def main(): Unit =
  println("welcome to Toybrokersludo")

  val controller: de.htwg.se.toybrokersludo.neu.controller.Controller = de.htwg.se.toybrokersludo.neu.controller.impl.DefaultController()
  val tui = de.htwg.se.toybrokersludo.neu.view.Tui(using controller)

  tui.inputLoop()

  /*
  
  given FieldInterface = Field()
  val field : Field = Field()

  given FileIOInterface : FileIo()
  val fileIO : FileIo = FileIo()
  
  given ControllerInterface = Controller(using field) (using fileIO)
  val controller : Controller = Controller(using field) (using fileIO)
  
  val gui = GUI(using controller)
  val tui = TUI(using controller)

  // true for a menue and false for no one
  gui.run(true)
  tui.run(false)
   */