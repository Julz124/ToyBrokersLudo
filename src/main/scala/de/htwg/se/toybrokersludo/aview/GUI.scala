package de.htwg.se.toybrokersludo.aview


import scala.swing.*
import de.htwg.se.toybrokersludo.model.{BluePlayer, GreenPlayer, Move, PlayToken, RedPlayer, Stone, Token, YellowPlayer}
import de.htwg.se.toybrokersludo.controller.Controller
import de.htwg.se.toybrokersludo.util.Observer
import de.htwg.se.toybrokersludo.aview.UI

import javax.swing.SpringLayout.Constraints
import scala.language.postfixOps
import scala.swing
import scala.swing.event.MouseClicked
import scala.util.{Failure, Success, Try}
import javax.imageio.*
import java.io.File
import javax.swing.ImageIcon
import java.awt.Color


class GUI(controller: Controller) extends Frame with UI(controller) {

  override def inputLoop: Unit = None

  override def analyseInput(input: String): Try[Option[Move]] = Try(None)

  override def menue =
    contents = new BorderPanel {
      add(new Label("Spieleranzahl auswählen"),
        BorderPanel.Position.North
      )
      for (i <- 2 to 4) add(new Button(i + " Player") {
        listenTo(mouse.clicks)
        reactions += {
          case e: MouseClicked =>
            controller.startup(i)
            controller.update
        }
      }, BorderPanel.Position.apply(i))
    }
    pack()
    centerOnScreen()
    open()

  title = "Toy brokers ludo"
  menuBar = new MenuBar {
    contents += new Menu("Menu") {
      contents += new MenuItem(Action("Exit") {
        sys.exit(0)
      })
    }
  }
  resizable = false
  pack()
  centerOnScreen()
  open()
  

  override def update =
    contents = new BorderPanel {
      background = Color.white
      add(label, BorderPanel.Position.North)
      add(dice, BorderPanel.Position.South)
      add(gamePanal, BorderPanel.Position.Center)
    }

  def label : Label =
    if (controller.field.shouldDice) {
      new Label(controller.field.player.toString + " have to dice")
    } else {
      new Label(controller.field.player.toString + " have to move")
    }

  def dice : Button = new Button(controller.field.dice.toString) {
    listenTo(mouse.clicks)
    reactions += {
      case e: MouseClicked =>
        if (controller.field.shouldDice) {
          controller.dice()
          if (controller.getPossibleMoves(controller.field.dice).nonEmpty) {
            controller.invertDice
          } else {
            if (controller.field.dice != 6) {
              controller.nextPlayer()
            }
          }
          update
        }
    }
  }


  def gamePanal : GridBagPanel =
    new GridBagPanel {
      background = Color.white
      def constraints(x: Int, y: Int,
                      gridwidth: Int = 1, gridheight: Int = 1,
                      weightx: Double = 0, weighty: Double = 1,
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
      for (i <- 0 to 10) {
        for (j <- 0 to 10) {
          add(new CellButton(controller.field.matrix.map(i)(j).index),
            constraints(j + 1, i + 1))
        }
      }
    }




  case class CellButton(index: Int) extends Button() {
    preferredSize = new Dimension(40,40)
    listenTo(mouse.clicks)
    val stone = controller.field.matrix.getStone(index)
    if (stone.isAPlayField == false) visible = false
    icon = getIcon(stone)
    reactions += {
      case e: MouseClicked => fieldClicked(stone)
    }
  }

  def getIcon(stone : Stone): ImageIcon =
    stone.token match
      case Some(token: Token) => new ImageIcon("src/main/resources/" + token.getColor() + token.getNumber() + ".png")
      case None =>
        for (player <- List(GreenPlayer, RedPlayer, BluePlayer, YellowPlayer)) {
          if (player.endFields().contains(stone.index)) {
            return new ImageIcon("src/main/resources/" + player.playerString + "End.png")
          }
        }
        new ImageIcon("src/main/resources/Empty_Field.png")

  def fieldClicked(stone: Stone): Unit =
    if (!controller.field.shouldDice) {
      if (!controller.field.shouldDice && controller.getPossibleMoves(controller.field.dice).exists((move: Move) => move.token.equals(stone.token match
        case Some(token: Token) => token
        case None => None))) {
        controller.invertDice
        controller.doAndPublish(controller.move, controller.getPossibleMoves(controller.field.dice).find((move: Move) => move.token.equals(stone.token match
          case Some(token: Token) => token
          case None => None
        )) match
          case Some(move: Move) => move)
        if (controller.field.dice != 6) {
          controller.nextPlayer()
        }
      }
      update
    }
}