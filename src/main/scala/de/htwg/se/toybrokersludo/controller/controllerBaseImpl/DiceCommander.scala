package de.htwg.se.toybrokersludo.controller.controllerBaseImpl

import de.htwg.se.toybrokersludo.model.Move
import de.htwg.se.toybrokersludo.model.FieldInterface
import de.htwg.se.toybrokersludo.util.Command

class DiceCommander(field: FieldInterface, dice : Int) extends Command[FieldInterface] :
  override def doStep(field: FieldInterface): FieldInterface =
    var field2 : FieldInterface = field
    field2 = field2.dice(dice)
    if (field2.getPlayer.possibleMoves(dice, field2).nonEmpty) field2 = field2.invertDice()
    else if (field2.getDice != 6) field2 = field2.nextPlayer()
    field2

  override def undoStep(field: FieldInterface): FieldInterface =
    this.field

  override def redoStep(field: FieldInterface): FieldInterface =
    var field2: FieldInterface = field
    field2 = field2.dice(dice)
    if (field2.getPlayer.possibleMoves(dice, field2).nonEmpty) field2 = field2.invertDice()
    else if (field2.getDice != 6) field2.nextPlayer()
    field2

