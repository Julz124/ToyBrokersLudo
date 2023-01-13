package de.htwg.se.toybrokersludo.controller.controllerBaseImpl

import de.htwg.se.toybrokersludo.controller.ControllerInterface
import de.htwg.se.toybrokersludo.model.FieldBaseImpl.Field
import de.htwg.se.toybrokersludo.model.FileIO.FileIOInterface
import de.htwg.se.toybrokersludo.model.FileIO.JsonImpl.FileIo
import de.htwg.se.toybrokersludo.model.{FieldInterface, Move, PlayToken}
import de.htwg.se.toybrokersludo.util.UndoManager
import de.htwg.se.toybrokersludo.model.given FieldInterface
import de.htwg.se.toybrokersludo.model.given FileIOInterface

import scala.util.Random

class Controller(using var field: FieldInterface) (using val fieleIO : FileIOInterface) extends ControllerInterface {
  
  override def getShouldDice = field.getShouldDice

  override def getPlayer = field.getPlayer

  override def getDice = field.getDice

  override def getMatrix = field.getMatrix

  override def getField = field

  override def startup(spieler: Int) =
    spieler match {
      case 1 =>
        for (move <- startGreen()) field = field.put(move)
      case 2 =>
        for (move <- startGreen()) field = field.put(move)
        for (move <- startRed()) field = field.put(move)
      case 3 =>
        for (move <- startGreen()) field = field.put(move)
        for (move <- startRed()) field = field.put(move)
        for (move <- startBlue()) field = field.put(move)
      case 4 =>
        for (move <- startGreen()) field = field.put(move)
        for (move <- startRed()) field = field.put(move)
        for (move <- startBlue()) field = field.put(move)
        for (move <- startYellow()) field = field.put(move)
    }
    /*
    field = move(Move(PlayToken.apply(1, "G"), 54))
    field = move(Move(PlayToken.apply(1, "R"), 28))
    field = move(Move(PlayToken.apply(1, "B"), 36))
    field = move(Move(PlayToken.apply(1, "Y"), 53))
    */
    field = field.numberPlayer(spieler)
    notifyObservers


  override def getPossibleMoves(dice: Int): List[Move] =
    if (getShouldDice) Nil
    else field.getPlayer.possibleMoves(dice, field)

  override def dice() =
    if (!getShouldDice) return
    field = undoManager.doStep(field, DiceCommander(field, (Random().nextDouble() * 6).toInt + 1))
    notifyObservers

  override def doAndPublish(doThis: Move => FieldInterface, move: Move) =
    field = doThis(move)
    notifyObservers

  override def doAndPublish(doThis: FieldInterface => FieldInterface) =
    field = doThis(field)
    notifyObservers

  val undoManager = UndoManager[FieldInterface]

  def put(move: Move): FieldInterface = field.put(move)

  override def move(move: Move): FieldInterface =
    undoManager.doStep(field, MoveCommander(field, move))

  override def undo(field: FieldInterface): FieldInterface = undoManager.undoStep(field)

  override def redo(field: FieldInterface): FieldInterface = undoManager.redoStep(field)

  override def save(target: String): Unit =
    fieleIO.save(field, target)

  override def getTargets(): List[String] =
    fieleIO.getTargets()

  override def load(source: String): Unit =
    field = fieleIO.load(source)
    notifyObservers

  
  def startGreen(): List[Move] = List(Move(PlayToken.apply(1, "G"), 0), Move(PlayToken.apply(2, "G"), 1), Move(PlayToken.apply(3, "G"), 2), Move(PlayToken.apply(4, "G"), 3))
  
  def startRed(): List[Move] = List(Move(PlayToken.apply(1, "R"), 4), Move(PlayToken.apply(2, "R"), 5), Move(PlayToken.apply(3, "R"), 6), Move(PlayToken.apply(4, "R"), 7))
  
  def startYellow(): List[Move] = List(Move(PlayToken.apply(1, "Y"), 8), Move(PlayToken.apply(2, "Y"), 9), Move(PlayToken.apply(3, "Y"), 10), Move(PlayToken.apply(4, "Y"), 11))

  def startBlue(): List[Move] = List(Move(PlayToken.apply(1, "B"), 12), Move(PlayToken.apply(2, "B"), 13), Move(PlayToken.apply(3, "B"), 14), Move(PlayToken.apply(4, "B"), 15))
}
