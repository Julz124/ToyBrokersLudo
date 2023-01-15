package de.htwg.se.toybrokersludo.model.FileIO

import de.htwg.se.toybrokersludo.model.FileIO.FileIOInterface
import de.htwg.se.toybrokersludo.model.FieldInterface
import de.htwg.se.toybrokersludo.model.FieldBaseImpl.{Field, Matrix}
import de.htwg.se.toybrokersludo.model.{Stone, Move, PlayToken}
import de.htwg.se.toybrokersludo.util.PlayerBaseImpl.{BluePlayer, GreenPlayer, RedPlayer, YellowPlayer}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.should

class FileIOInterfaceSpec extends AnyWordSpec with Matchers {

  val matrix: Matrix = Matrix()

  "json" should {
    import de.htwg.se.toybrokersludo.model.FileIO.JsonImpl.FileIo
    val jsonImpl: FileIOInterface = FileIo()

    "can load with GreenPlayer" in {
      val field: FieldInterface = Field(matrix,GreenPlayer,1)
      field.put(Move(PlayToken.apply(1, "G"), 20))
      jsonImpl.save(field, "SpecTestJsonGreen")
      jsonImpl.load("SpecTestJsonGreen").toString should equal (Field(matrix,GreenPlayer,1).toString)
    }

    "can load with RedPlayer" in {
      val field: FieldInterface = Field(matrix, RedPlayer, 2)
      field.put(Move(PlayToken.apply(2, "R"), 20))
      jsonImpl.save(field, "SpecTestJsonRed")
      jsonImpl.load("SpecTestJsonRed").toString should equal(Field(matrix, RedPlayer, 2).toString)
    }

    "can load with BluePlayer" in {
      val field: FieldInterface = Field(matrix, BluePlayer, 3)
      field.put(Move(PlayToken.apply(3, "B"), 20))
      jsonImpl.save(field, "SpecTestJsonBlue")
      jsonImpl.load("SpecTestJsonBlue").toString should equal(Field(matrix, BluePlayer, 3).toString)
    }

    "can load with YellowPlayer" in {
      val field: FieldInterface = Field(matrix, YellowPlayer, 4)
      field.put(Move(PlayToken.apply(4, "Y"), 20))
      jsonImpl.save(field, "SpecTestJsonYellow")
      jsonImpl.load("SpecTestJsonYellow").toString should equal(Field(matrix, YellowPlayer, 4).toString)
    }

    "can getTargets" in {
      jsonImpl.getTargets() should contain ("saveGameJson\\SpecTestJsonGreen")
      jsonImpl.getTargets() should contain (  "saveGameJson\\SpecTestJsonRed")
      jsonImpl.getTargets() should contain ("saveGameJson\\SpecTestJsonBlue")
      jsonImpl.getTargets() should contain ("saveGameJson\\SpecTestJsonYellow")
    }

  }

  "xml" should {
    import de.htwg.se.toybrokersludo.model.FileIO.XmlImpl.FileIo
    val xmlImpl: FileIOInterface = FileIo()

    "can load with GreenPlayer" in {
      val field: FieldInterface = Field(matrix, GreenPlayer, 1)
      field.put(Move(PlayToken.apply(1, "G"), 20))
      xmlImpl.save(field, "SpecTestXmlGreen")
      xmlImpl.load("SpecTestXmlGreen").toString should equal(Field(matrix, GreenPlayer, 1).toString)
    }

    "can load with RedPlayer" in {
      val field: FieldInterface = Field(matrix, RedPlayer, 1)
      field.put(Move(PlayToken.apply(2, "R"), 20))
      xmlImpl.save(field, "SpecTestXmlRed")
      xmlImpl.load("SpecTestXmlRed").toString should equal(Field(matrix, RedPlayer, 2).toString)
    }

    "can load with BluePlayer" in {
      val field: FieldInterface = Field(matrix, BluePlayer, 3)
      field.put(Move(PlayToken.apply(3, "B"), 20))
      xmlImpl.save(field, "SpecTestXmlBlue")
      xmlImpl.load("SpecTestXmlBlue").toString should equal(Field(matrix, BluePlayer, 3).toString)
    }

    "can load with YellowPlayer" in {
      val field: FieldInterface = Field(matrix, YellowPlayer, 4)
      field.put(Move(PlayToken.apply(4, "Y"), 20))
      xmlImpl.save(field, "SpecTestXmlYellow")
      xmlImpl.load("SpecTestXmlYellow").toString should equal(Field(matrix, YellowPlayer, 4).toString)
    }

    "can getTargets" in {
      xmlImpl.getTargets() should contain("saveGameXml\\SpecTestXmlGreen")
      xmlImpl.getTargets() should contain("saveGameXml\\SpecTestXmlRed")
      xmlImpl.getTargets() should contain("saveGameXml\\SpecTestXmlBlue")
      xmlImpl.getTargets() should contain("saveGameXml\\SpecTestXmlYellow")
    }
  }


}
