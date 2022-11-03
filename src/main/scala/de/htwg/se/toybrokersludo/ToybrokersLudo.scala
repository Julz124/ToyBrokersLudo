package de.htwg.se.toybrokersludo

import de.htwg.se.toybrokersludo.model.{Field, Matrix}

@main def run: Unit =
  println("welcome to Toybrokersludo")
  val field = Field(Matrix())
  val controller = Controller(field)
  val tui = TUI(controller)
  tui.run()





