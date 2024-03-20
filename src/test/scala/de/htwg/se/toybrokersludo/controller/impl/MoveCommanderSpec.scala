package de.htwg.se.toybrokersludo.controller.impl

import de.htwg.se.toybrokersludo.model.{GameField, Move}
import de.htwg.se.toybrokersludo.util.UndoManager
import org.scalatest.BeforeAndAfterEach
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MoveCommanderSpec extends AnyWordSpec with Matchers with BeforeAndAfterEach {
  var undoManger = UndoManager[GameField]()

  override def beforeEach(): Unit = {
    undoManger = UndoManager[GameField]()
  }

  "MoveCommander" should {
    "undo" in {
      val gameField = GameField.init()
      val newGameField = undoManger.doStep(gameField, MoveCommander(
        List(Move(fromIndex = 0, toIndex = 20)),
        gameField.gameState
      ))

      undoManger.undoStep(newGameField).map shouldEqual(gameField).map
    }

    "redo" in {
      val gameField = GameField.init()
      var firstGameField = undoManger.doStep(gameField, MoveCommander(
        List(Move(fromIndex = 0, toIndex = 20)),
        gameField.gameState
      ))
      val scondGameField = undoManger.undoStep(firstGameField)

      undoManger.redoStep(scondGameField).map shouldEqual(firstGameField.map)
    }
  }
}
