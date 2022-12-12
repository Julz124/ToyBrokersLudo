package de.htwg.se.toybrokersludo.controller.controllerBaseImpl

import de.htwg.se.toybrokersludo.model.Move
import de.htwg.se.toybrokersludo.model.FieldInterface
import de.htwg.se.toybrokersludo.util.Command

case class PutCommander(field: FieldInterface, move: Move) extends Command[FieldInterface] :
  override def doStep(field: FieldInterface): FieldInterface = field.move(move)

  override def undoStep(field: FieldInterface): FieldInterface = this.field

  override def redoStep(field: FieldInterface): FieldInterface = field.move(move)
