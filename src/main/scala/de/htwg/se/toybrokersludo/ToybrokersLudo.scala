package de.htwg.se.toybrokersludo

import de.htwg.se.toybrokersludo.model.FieldBaseImpl.Field
import de.htwg.se.toybrokersludo.model.FileIO.JsonImpl.FileIo
import de.htwg.se.toybrokersludo.aview.{TUI}
import de.htwg.se.toybrokersludo.controller.Controller

object Main {
  def main(args: Array[String]): Unit = {
    println("welcome to Toybrokersludo")

    val field: Field = Field()

    val fileIO: FileIo = FileIo()

    val controller: Controller = new Controller(field, fileIO)

    val tui = new TUI(controller)
  }
}
