package de.htwg.se.toybrokersludo.model.FileIO.JsonImpl

import de.htwg.se.toybrokersludo.model.FieldBaseImpl.{Field, Matrix}
import de.htwg.se.toybrokersludo.model.FileIO.FileIOInterface
import de.htwg.se.toybrokersludo.model.{FieldInterface, PlayToken, Stone, Token, Move}
import de.htwg.se.toybrokersludo.util.PlayerBaseImpl.{BluePlayer, GreenPlayer, RedPlayer, YellowPlayer}
import play.api.libs.json.{JsValue, Json}

import java.io.{File, IOException, PrintWriter}
import java.nio.file.{Files, Paths}

case class FileIo() extends FileIOInterface {

  val path = "saveGameJson"

  def makeFolder(): Unit = {
    val folder = new File(path)
    if (!(folder.exists))
      try Files.createDirectory(Paths.get(path))
      catch {
        case e: IOException => e.printStackTrace()
      }
  }

  def getTargets(): List[String] = {
    val file = scala.reflect.io.File(path)
    val files: List[scala.reflect.io.File] = file.toDirectory.files.toList
    files.map(file => file.toString.replaceAll(".json", "").replaceAll(path + "/", ""))
  }

  def save(field: FieldInterface, target: String): Unit = {
    makeFolder()
    val pw = new PrintWriter(new File(path + "/" + target + ".json"))
    pw.write(FildToJson(field).toString())
    pw.close()
  }

  def FildToJson(field: FieldInterface) =
    Json.obj(
      "matrix" -> {
        for (row <- field.getMatrix.map) yield lineToJson(row)
      },
      "current player" -> {
        Json.obj("player" -> field.getPlayer.playerString)
      },
      "playerNumber" -> {
        Json.obj(
          "player" -> field.getMatrix.getToken.map((move: Move) => move.token.getColor()).groupBy((s: String) => s).size)
      },
      "current dice" -> {
        Json.obj("dice" -> field.getDice)
      },
      "should dice" -> {
        Json.obj("should" -> field.getShouldDice)
      }
    )

  def lineToJson(row: List[Stone]) = {

  Json.obj(
    "row" -> {
      for (stone <- row) yield stoneToJson(stone)
    }
  )
}

  def stoneToJson(stone: Stone) = {
    Json.obj(stone.token match {
      case Some(token: Token)
      => "stone" -> Json.obj(
        "isAPlayField" -> stone.isAPlayField,
        "index" -> stone.index,
        "Option Token" -> tokenToJson(token)
      )
      case None =>
        "stone" -> Json.obj(
          "isAPlayField" -> stone.isAPlayField,
          "index" -> stone.index,
          "Option Token" -> None
        )
    }
    )
  }

  def tokenToJson(token: Token) =
    Json.obj(
      "number" -> token.getNumber(),
      "color" -> token.getColor(),
    )


  def load(source: String): FieldInterface = {
    import scala.io.Source
    val sourcePath: String = Source.fromFile (path + "/" + source + ".json").getLines.mkString
    val json: JsValue = Json.parse (sourcePath)
    Field (Matrix (json ("matrix").as[Seq[JsValue]].map (row => jsonToRow (row) ).toList.asInstanceOf[List[List[Stone]]] ),
    json ("current player") ("player").as[String] match {
    case "G" => GreenPlayer
    case "R" => RedPlayer
    case "B" => BluePlayer
    case "Y" => YellowPlayer
  }
    ,
    json ("playerNumber") ("player").as[Int],
    json ("current dice") ("dice").as[Int],
    json ("should dice") ("should").as[Boolean]
    )
  }


  def jsonToRow(row: JsValue) =
    row("row").as[Seq[JsValue]].map(stone => jsonToStone(stone))

  def jsonToStone(stone: JsValue) =
    Stone(stone("stone")("isAPlayField").as[Boolean],
      stone("stone")("index").as[Int],
      jsonToToken(stone("stone")("Option Token")))

  def jsonToToken(token: JsValue): Option[Token] = {
    token.toString () match {
      case "null" => None
      case _ => Option(PlayToken(token("number").as[Int], token("color").as[String]))
    }
  }
}
