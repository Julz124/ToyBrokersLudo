package de.htwg.se.toybrokersludo.controller.controllerBaseImpl

import de.htwg.se.toybrokersludo.model.Move
import de.htwg.se.toybrokersludo.model.FieldInterface
import de.htwg.se.toybrokersludo.util.Command

case class MoveCommander(field: FieldInterface, move: Move) extends Command[FieldInterface] {
  override def doStep(field: FieldInterface): FieldInterface = {
    var field2: FieldInterface = field
    if (!field.getShouldDice) field2 = field2.invertDice()
    if (field.getDice != 6) field2 = field2.nextPlayer()
    field2.move(move)
}

  override def undoStep(field: FieldInterface): FieldInterface =
    this.field

  override def redoStep(field: FieldInterface): FieldInterface = {
    var field2: FieldInterface = field
    if (! field.getShouldDice) field2 = field2.invertDice ()
    if (field.getDice != 6) field2 = field2.nextPlayer ()
    field.move (move)
  }
}
