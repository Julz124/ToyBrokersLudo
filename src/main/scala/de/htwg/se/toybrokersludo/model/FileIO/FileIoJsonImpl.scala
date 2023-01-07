package de.htwg.se.toybrokersludo.model.FileIO

import de.htwg.se.toybrokersludo.model.{FieldInterface, Stone, Move}
import de.htwg.se.toybrokersludo.model.FieldBaseImpl.Field
import java.io._
import java.nio.file.Files
import java.nio.file.Paths

import play.api.libs.json._

class FileIoJsonImpl extends FileIOInterface {

  val path = "saveGameJson"

  def save(field : FieldInterface, target : String): Unit =
    makeFolder()
    val pw = new PrintWriter(new File(path + "/" + target + ".json"))
    pw.write(FildToJson(field).toString())
    pw.close()

  def FildToJson(field : FieldInterface) =
    Json.obj(
      "matrix" -> {
        for (row <- field.getMatrix.map.indices)
          yield lineToJson(field.getMatrix.map(row), row)
      },
      "current player" -> {
        Json.obj("player" -> field.getPlayer.playerString)
      },
      "playerNumber" -> {
        Json.obj(
          "player" -> field.getMatrix.getToken.map((move : Move) => move.token.getColor()).groupBy((s : String) => s).size)
      },
      "current dice" -> {
        Json.obj("dice" -> field.getDice)
      },
      "should dice" -> {
        Json.obj("should" -> field.getShouldDice)
      }
    )

  def lineToJson(list : List[Stone], row: Int) =
    Json.obj(
      "row" -> row,
      "cells" -> {
        for (i <- list.indices) yield stoneToJson(list(i), row, i)
      }
    )

  def stoneToJson(stone : Stone, x: Int, y: Int) =
    val stoneJson = Json.obj(
      "x" -> x,
      "y" -> y,
      "value" -> stone.toString()
    )
    stoneJson

  def load(source: String): FieldInterface =
    Field()

  def makeFolder() : Unit =

    val folder = new File(path)
    if (!(folder.exists))
      try Files.createDirectory(Paths.get(path))
      catch case e: IOException => e.printStackTrace()

}
