package de.htwg.se.toybrokersludo.model.FileIO.XmlImpl

import de.htwg.se.toybrokersludo.model.FieldBaseImpl.{Field, Matrix}
import de.htwg.se.toybrokersludo.model.FileIO.FileIOInterface
import de.htwg.se.toybrokersludo.model.*
import de.htwg.se.toybrokersludo.util.PlayerBaseImpl.{BluePlayer, GreenPlayer, RedPlayer, YellowPlayer}
import play.api.libs.json.{JsValue, Json}

import java.io.{File, IOException, PrintWriter}
import java.nio.file.{Files, Paths}
import scala.xml._

case class FileIO() extends FileIOInterface {

  val path = "saveGameXml"

  def makeFolder(): Unit =
    val folder = new File(path)
    if (!(folder.exists))
      try Files.createDirectory(Paths.get(path))
      catch case e: IOException => e.printStackTrace()

  def getTargets(): List[String] =
    val files: List[File] = File(path).listFiles().toList
    files.map(file => file.toString.replaceAll(".xml", "").replaceAll(path + "/", ""))


  def save(field: FieldInterface, target: String): Unit =
    makeFolder()
    val pw = new PrintWriter(new File(path + "/" + target + ".xml"))
    pw.write(FieldToXml(field).toString())
    pw.close()

  def FieldToXml(field: FieldInterface) =
    <field>
      <matrix>
        {
        for row <- field.getMatrix.map
          yield lineToXml(row)
        }
      </matrix>
      <currentplayer>
        {field.getPlayer.playerString}
      </currentplayer>
      <playerNumber>
        {field.getMatrix.getToken.map((move: Move) => move.token.getColor()).groupBy((s: String) => s).size}
      </playerNumber>
      <currentDice>
        {field.getDice}
      </currentDice>
      <shouldDice>
        {field.getShouldDice}
      </shouldDice>
    </field>

  def lineToXml(row: List[Stone]) =
    <row>
      {
      for (stone <- row) 
        yield stoneToXml(stone)
      }
    </row>

  def stoneToXml(stone: Stone) =
    stone.token match
      case Some(token: Token) =>
        <stone>
          <isAPlayField>
            {stone.isAPlayField}
          </isAPlayField>
          <index>
           {stone.index}
          </index>  
          <OptionToken>
            {tokenToXml(token)}
          </OptionToken>
        </stone>
      case None =>
        <stone>
          <isAPlayField>
            {stone.isAPlayField}
          </isAPlayField>
          <index>
            {stone.index}
          </index>
          <OptionToken>
            {None}
          </OptionToken>
        </stone>

  def tokenToXml(token: Token) =
    <token>
      <number>
        {token.getNumber()}
      </number>
      <color>
        {token.getColor()}
      </color>
    </token>

  def load(source: String): FieldInterface =
    import scala.io.Source
    import scala.xml._
    val sourcePath: String = Source.fromFile(path + "/" + source + ".xml").getLines.mkString
    val xml = XML.loadString(source.mkString)

    val field = (xml \ "field").asInstanceOf[Field[Matrix[]]]
    val player_nr = (xml \ "player").text.trim.toInt
    val curr_dice = (xml \"dice").text.trim.toInt
    val shd_dice = (xml \ "should dice").text.trim.toBoolean
}