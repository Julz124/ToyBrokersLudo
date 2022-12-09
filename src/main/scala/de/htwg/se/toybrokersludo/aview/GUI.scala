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
    else text = stone.token match
      case Some(token: Token) => token.getColor() + token.getNumber()
      case None => " "

    reactions += {
      case e: MouseClicked => fieldClicked(stone)
    }
  }

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