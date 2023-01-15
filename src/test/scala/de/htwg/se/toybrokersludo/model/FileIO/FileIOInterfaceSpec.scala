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
      var field: FieldInterface = Field(matrix,GreenPlayer,1)
      field = field.put(Move(PlayToken.apply(1, "G"), 20))
      jsonImpl.save(field, "SpecTestJsonGreen")
      jsonImpl.load("SpecTestJsonGreen").toString should equal (field.toString)
    }

    "can load with RedPlayer" in {
      var field: FieldInterface = Field(matrix, RedPlayer, 2)
      field = field.put(Move(PlayToken.apply(2, "R"), 20))
      jsonImpl.save(field, "SpecTestJsonRed")
      jsonImpl.load("SpecTestJsonRed").toString should equal(field.toString)
    }

    "can load with BluePlayer" in {
      var field: FieldInterface = Field(matrix, BluePlayer, 3)
      field = field.put(Move(PlayToken.apply(3, "B"), 20))
      jsonImpl.save(field, "SpecTestJsonBlue")
      jsonImpl.load("SpecTestJsonBlue").toString should equal(field.toString)
    }

    "can load with YellowPlayer" in {
      var field: FieldInterface = Field(matrix, YellowPlayer, 4)
      field = field.put(Move(PlayToken.apply(4, "Y"), 20))
      jsonImpl.save(field, "SpecTestJsonYellow")
      jsonImpl.load("SpecTestJsonYellow").toString should equal(field.toString)
    }

    "can getTargets" in {
      jsonImpl.getTargets() should contain ("SpecTestJsonGreen")
      jsonImpl.getTargets() should contain ("SpecTestJsonRed")
      jsonImpl.getTargets() should contain ("SpecTestJsonBlue")
      jsonImpl.getTargets() should contain ("SpecTestJsonYellow")
    }

  }

  "xml" should {
    import de.htwg.se.toybrokersludo.model.FileIO.XmlImpl.FileIo
    val xmlImpl: FileIOInterface = FileIo()

    "can load with GreenPlayer" in {
      var field: FieldInterface = Field(matrix, GreenPlayer, 1)
      field = field.put(Move(PlayToken.apply(1, "G"), 20))
      xmlImpl.save(field, "SpecTestXmlGreen")
      xmlImpl.load("SpecTestXmlGreen").toString should equal(field.toString)
    }

    "can load with RedPlayer" in {
      var field: FieldInterface = Field(matrix, RedPlayer, 1)
      field = field.put(Move(PlayToken.apply(2, "R"), 20))
      xmlImpl.save(field, "SpecTestXmlRed")
      xmlImpl.load("SpecTestXmlRed").toString should equal(field.toString)
    }

    "can load with BluePlayer" in {
      var field: FieldInterface = Field(matrix, BluePlayer, 3)
      field = field.put(Move(PlayToken.apply(3, "B"), 20))
      xmlImpl.save(field, "SpecTestXmlBlue")
      xmlImpl.load("SpecTestXmlBlue").toString should equal(field.toString)
    }

    "can load with YellowPlayer" in {
      var field: FieldInterface = Field(matrix, YellowPlayer, 4)
      field = field.put(Move(PlayToken.apply(4, "Y"), 20))
      xmlImpl.save(field, "SpecTestXmlYellow")
      xmlImpl.load("SpecTestXmlYellow").toString should equal(field.toString)
    }

    "can getTargets" in {
      xmlImpl.getTargets() should contain("SpecTestXmlGreen")
      xmlImpl.getTargets() should contain("SpecTestXmlRed")
      xmlImpl.getTargets() should contain("SpecTestXmlBlue")
      xmlImpl.getTargets() should contain("SpecTestXmlYellow")
    }
  }


}
