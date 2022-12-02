package de.htwg.se.toybrokersludo.controller

import de.htwg.se.toybrokersludo.model.{Field, Move, Stone}
import de.htwg.se.toybrokersludo.util.Command

case class PutCommander(field : Field, move: Move) extends Command[Field]:
  override def doStep(field: Field): Field = field.move(move)

  override def undoStep(field: Field): Field = this.field

  override def redoStep(field: Field): Field = field.move(move)
