package de.htwg.se.toybrokersludo.aview


/*
import de.htwg.se.toybrokersludo.aview.UI
import de.htwg.se.toybrokersludo.controller.ControllerInterface
import de.htwg.se.toybrokersludo.model.{Move, PlayToken, Stone, Token}
import de.htwg.se.toybrokersludo.util.Observer
import de.htwg.se.toybrokersludo.util.PlayerBaseImpl.{BluePlayer, GreenPlayer, RedPlayer, YellowPlayer}

import scala.util.{Failure, Success, Try}
import scala.swing.{BorderPanel, Dimension, Frame, *}
import javax.swing.SpringLayout.{Constraints, VERTICAL_CENTER}
import scala.swing
import scala.swing.event.{ButtonClicked, MouseClicked}
import javax.imageio.*
import java.io.File
import javax.swing.{ImageIcon, JButton, JLabel, JPanel}
import java.awt.Color
import scala.runtime.BoxesRunTime.add


class GUI(controller: ControllerInterface) extends Frame with UI(controller) {

  override def inputLoop(): Unit = None

  override def analyseInput(input: String): Try[Option[Move]] = Try(None)

  def menue() =
    contents = new BorderPanel {
      add(new Label("how many players?"),
        BorderPanel.Position.North
      )
      for (i <- 2 to 4) add(new Button(i + " Player") {
        listenTo(mouse.clicks)
        reactions += {
          case e: MouseClicked => controller.startup(i)
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
      contents += new MenuItem(Action("Load") {
        load()
      })
      contents += new MenuItem(Action("Save") {
        save()
      })
    }
  }
  resizable = false
  pack()
  centerOnScreen()
  open()

  def load() =
    new MainFrame {
      contents = new BorderPanel{
        add(new Label("load"), BorderPanel.Position.North)
        add(new BoxPanel(Orientation.Vertical) {
          controller.getTargets().foreach((string : String) =>
            contents += new Button(string) {
              reactions += {
                case e: ButtonClicked => controller.load(string); dispose()
              }
              listenTo(this)
            }
          )
          contents += new Button("cancel") {
            this.foreground = Color.BLUE
            listenTo(this)
            reactions += {
              case e: ButtonClicked => dispose()
            }
          }
        }, BorderPanel.Position.West)
      resizable = false
      pack()
      centerOnScreen()
      open()
    }}

  
  def save() =
    controller.save(Dialog.showInput(contents.head, "save", initial = "") match
      case Some(string: String) => string)

  override def update =
    contents = new BorderPanel {
      background = Color.white
      add(label, BorderPanel.Position.North)
      add(dice, BorderPanel.Position.South)
      add(gamePanal, BorderPanel.Position.Center)
    }

  def label : Label =
    if (controller.getShouldDice) {
      new Label(controller.getPlayer.toString + " have to dice")
    } else {
      new Label(controller.getPlayer.toString + " have to move")
    }

  def dice : Button = new Button(controller.getDice.toString) {
    listenTo(this)
    reactions += {
      case e: ButtonClicked =>
        controller.doAndPublish(controller.dice)
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
          add(new CellButton(controller.getMatrix.getMap(i)(j).index),
            constraints(j + 1, i + 1))
        }
      }
    }


  case class CellButton(index: Int) extends Button() {
    preferredSize = new Dimension(40,40)
    listenTo(this)
    val stone = controller.getMatrix.getStone(index)
    if (stone.isAPlayField == false) visible = false
    icon = getIcon(stone)
    reactions += {
      case e: ButtonClicked => fieldClicked(stone)
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
    if (!controller.getShouldDice) {
      if (!controller.getShouldDice && controller.getPossibleMoves(controller.getDice).exists((move: Move) => move.token.equals(stone.token match
        case Some(token: Token) => token
        case None => None))) {
        controller.doAndPublish(controller.move, controller.getPossibleMoves(controller.getDice).find((move: Move) => move.token.equals(stone.token match
          case Some(token: Token) => token
          case None => None
        )) match
          case Some(move: Move) => move)
      }
    }


}
*/
