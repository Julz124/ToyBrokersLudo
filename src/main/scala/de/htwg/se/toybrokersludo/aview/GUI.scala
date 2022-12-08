package de.htwg.se.toybrokersludo.aview


import scala.swing.*
import de.htwg.se.toybrokersludo.model.Stone
import de.htwg.se.toybrokersludo.model.{Move, PlayToken, Token}
import de.htwg.se.toybrokersludo.controller.Controller
import de.htwg.se.toybrokersludo.util.Observer
import de.htwg.se.toybrokersludo.aview.UI

import javax.swing.SpringLayout.Constraints
import scala.language.postfixOps
import scala.swing
import scala.swing.event.MouseClicked
import scala.util.{Failure, Success, Try}


class GUI(controller: Controller) extends Frame with UI(controller) {

  var dice = 4

  override def inputLoop: Unit = None

  override def analyseInput(input: String): Try[Option[Move]] = Try(None)

  title = "Toy brokers ludo"
  menuBar = new MenuBar {
    contents += new Menu("Menu") {
      contents += new MenuItem(Action("Exit") {
        sys.exit(0)
      })
    }
  }
  pack()
  centerOnScreen()
  open()


  override def update =
    contents = new GridBagPanel {
      def constraints(x: Int, y: Int,
                      gridwidth: Int = 1, gridheight: Int = 1,
                      weightx: Double = 0.0, weighty: Double = 0.0,
                      fill: GridBagPanel.Fill.Value = GridBagPanel.Fill.None)
      : Constraints = {
        val c = new Constraints
        c.gridx = x
        c.gridy = y
        c.gridwidth = gridwidth
        c.gridheight = gridheight
        c.weightx = weightx
        c.weighty = weighty
        c.fill = fill
        c
      }

      /*
      add(new Label(controller.field.player.toString + " ist an der Reihe") {
        border = Swing.EtchedBorder(Swing.Lowered)
      }, constraints(5, 0, fill = GridBagPanel.Fill.Both))
      */


      for(i <- 0 to 10) {
        for(j <- 0 to 10) {
          add(new CellButton(controller.field.matrix.map(i)(j).index),
            constraints(j + 1, i + 1))
        }
      }


    }

  /*
  override def update =
    contents = new BorderPanel {
      add(new Label(controller.field.player.toString + " ist an der Reihe"), BorderPanel.Position.North)
      add(new GridBagPanel {
        //controller.field.matrix.map.flatten.map((stone: Stone) => CellButton(stone.index)).foreach(comp => add(comp))
      }, BorderPanel.Position.Center)
    }
    repaint
  */


  override def menu =
    contents = new BorderPanel {
      add(new Label("Spieleranzahl auswählen"),
        BorderPanel.Position.North
      )
      for (i <- 2 to 4) add(new Button(i + " Player") {
        listenTo(mouse.clicks)
        reactions += {
          case e: MouseClicked =>
            controller.startup(i)
            update
        }
      }, BorderPanel.Position.apply(i))
    }
    pack()
    centerOnScreen()
    open()


  case class CellButton(index: Int) extends Button() {
    val stone = controller.field.matrix.getStone(index)
    if (stone.isAPlayField == false) visible = false
    else text = stone.player match
      case Some(token: Token) => token.getColor() + token.getNumber()
      case None => ""
    reactions += {
      case e: MouseClicked =>
        if (dice != 0 && controller.getPossibleMoves(dice).exists((move: Move) => move.token.equals(stone.player match
          case Some(token: Token) => token
          case None => None))) {
          controller.doAndPublish(controller.move, controller.getPossibleMoves(dice).find((move: Move) => move.token.equals(stone.player match
            case Some(token: Token) => token
            case None => None
          )) match
            case Some(token: Token) => token)
        }
        update
    }
  }
}