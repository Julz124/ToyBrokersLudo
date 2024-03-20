package de.htwg.se.toybrokersludo.controller.FileIO.impl

import de.htwg.se.toybrokersludo.controller.FileIO.FileIO
import de.htwg.se.toybrokersludo.model.GameField
import play.api.libs.json.{JsValue, Json, Writes}

import java.io.{File, FileNotFoundException, IOException, PrintWriter}
import java.nio.file.{Files, Paths}
import de.htwg.se.toybrokersludo.util.json.JsonWriters.gameFieldWrites
import de.htwg.se.toybrokersludo.util.json.JsonReaders.gameFieldReads
import scala.io.Source

case class JsonFileIO() extends FileIO:
  private val path = "saveGameJson"
  def save(gameField: GameField, target: String): Unit = 
    makeFolder()
    val pw = new PrintWriter(new File(path + "/" + target + ".json"))
    val json = Json.toJson(gameField)
    pw.write(Json.stringify(json))
    pw.close()
  def load(source: String): GameField = 
    val filePath = path + "/" + source + ".json"
    if (!Files.exists(Paths.get(filePath))) {
      throw new FileNotFoundException("File not found: " + filePath)
    }
    var sourceOption: Option[Source] = None
    try {
      val source = Source.fromFile(filePath)
      sourceOption = Some(source)
      val fileContent: String = source.getLines.mkString
      Json.fromJson(Json.parse(fileContent)).get
    } finally {
      sourceOption.foreach(_.close())
    }

  def getTargets: List[String] =
    makeFolder()
    val files: List[File] = File(path).listFiles().toList
    files.map(file => file.toString.replaceAll(".json", "").replaceAll(path + "/", ""))

  private def makeFolder(): Unit =
    val folder = new File(path)
    if (!folder.exists) {
      try Files.createDirectory(Paths.get(path))
      catch {
        case e: IOException => e.printStackTrace()
      }
    }

