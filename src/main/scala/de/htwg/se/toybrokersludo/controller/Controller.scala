package de.htwg.se.toybrokersludo.controller

import de.htwg.se.toybrokersludo.model.{Field, Move, PlayToken, Token}
import de.htwg.se.toybrokersludo.aview.TUI
import de.htwg.se.toybrokersludo.util.Observable

import scala.util.Random


case class Controller(var field: Field) extends Observable {

  def startGreen(): List[Move] = List(Move(PlayToken.apply(1, "G"), 0), Move(PlayToken.apply(2, "G"), 1), Move(PlayToken.apply(3, "G"), 2), Move(PlayToken.apply(4, "G"), 3))

  def startRed(): List[Move] = List(Move(PlayToken.apply(1, "R"), 4), Move(PlayToken.apply(2, "R"), 5), Move(PlayToken.apply(3, "R"), 6), Move(PlayToken.apply(4, "R"), 7))

  def startYellow(): List[Move] = List(Move(PlayToken.apply(1, "Y"), 8), Move(PlayToken.apply(2, "Y"), 9), Move(PlayToken.apply(3, "Y"), 10), Move(PlayToken.apply(4, "Y"), 11))

  def startBlue(): List[Move] = List(Move(PlayToken.apply(1, "B"), 12), Move(PlayToken.apply(2, "B"), 13), Move(PlayToken.apply(3, "B"), 14), Move(PlayToken.apply(4, "B"), 15))


  def startup(spieler: Int): Field =
    spieler match {
      case 1 =>
        for (move <- startGreen()) field = field.put(move)
      case 2 =>
        for (move <- startGreen()) field = field.put(move)
        for (move <- startRed()) field = field.put(move)
      case 3 =>
        for (move <- startGreen()) field = field.put(move)
        for (move <- startRed()) field = field.put(move)
        for (move <- startYellow()) field = field.put(move)
      case 4 =>
        for (move <- startGreen()) field = field.put(move)
        for (move <- startRed()) field = field.put(move)
        for (move <- startYellow()) field = field.put(move)
        for (move <- startBlue()) field = field.put(move)
    }
    field

  def dice() =
    val dice = new Random().nextDouble() * 6

  def doAndPublish(doThis: Move => Field, move: Move) =
    field = doThis(move)
    notifyObservers

  def put(move: Move): Field =
    field.put(move)

}
