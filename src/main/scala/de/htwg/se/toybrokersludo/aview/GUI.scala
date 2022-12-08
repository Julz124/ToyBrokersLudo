package de.htwg.se.toybrokersludo.aview


import scala.swing.{ComboBox, *}
import de.htwg.se.toybrokersludo.model
import de.htwg.se.toybrokersludo.model.{Move, PlayToken, Token}
import de.htwg.se.toybrokersludo.controller.Controller
import de.htwg.se.toybrokersludo.util.Observer
import de.htwg.se.toybrokersludo.aview.UI

import scala.language.postfixOps
import scala.swing.event.ButtonClicked
import scala.util.{Failure, Success, Try}


class GUI(controller: Controller) extends Frame with UI(controller) {

  override def gameloop: Unit = None

  override def analyseInput(input: String): Option[Move] = None

  title = "Toy brokers ludo"
  menuBar = new MenuBar {
    contents += new Menu("Menu") {
      contents += new MenuItem(Action("Exit") {
        sys.exit(0)
      })
    }
  }

/*
  contents = new BorderPanel {

    add(new Label(controller.field.player.toString + " ist an der Reihe"), BorderPanel.Position.North)
    add(new GridPanel(11, 11) {
      contents += paint('grey')
    })

  }
*/
  pack()
  centerOnScreen()
  open()


  override def update =
    contents = new BorderPanel {
      add(new Label(controller.field.player.toString + " ist an der Reihe"), BorderPanel.Position.North)
    }
    repaint


  override def menu =
    contents = new BorderPanel {
      add(new Label("Spieleranzahl auswählen"),
        BorderPanel.Position.North
      )
      add(new ComboBox(1 to 4) {
        listenTo(this)
        reactions += {
          case ButtonClicked(button) => {
            println("Event triggered: " + button.text)
          }
        }
      },
        BorderPanel.Position.South
      )
    }
    pack()
    centerOnScreen()
    open()

}