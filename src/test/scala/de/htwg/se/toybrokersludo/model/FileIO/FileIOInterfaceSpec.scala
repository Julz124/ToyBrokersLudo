package de.htwg.se.toybrokersludo.model.FileIO

import de.htwg.se.toybrokersludo.model.FileIO.FileIOInterface
import de.htwg.se.toybrokersludo.model.FieldInterface
import de.htwg.se.toybrokersludo.model.FieldBaseImpl.{Field, Matrix}
import de.htwg.se.toybrokersludo.util.PlayerBaseImpl.{BluePlayer, GreenPlayer, RedPlayer, YellowPlayer}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.should

class FileIOInterfaceSpec extends AnyWordSpec with Matchers {

  "json" should {
    import de.htwg.se.toybrokersludo.model.FileIO.JsonImpl.FileIo
    val jsonImpl: FileIOInterface = FileIo()

    "can load" in {
      val matrix: Matrix = Matrix()
      val field: FieldInterface = Field(matrix,GreenPlayer,1)
      jsonImpl.save(field, "SpecTestJson")
      jsonImpl.load("SpecTestJson").toString should equal (Field(matrix,GreenPlayer,1).toString)
    }

    "can getTargets" in {
      jsonImpl.getTargets() should contain ("saveGameJson\\SpecTestJson")
    }

  }

  "xml" should {
    import de.htwg.se.toybrokersludo.model.FileIO.XmlImpl.FileIo
    val xmlImpl: FileIOInterface = FileIo()

    "can load" in {
      val matrix: Matrix = Matrix()
      val field: FieldInterface = Field(matrix, GreenPlayer, 1)
      xmlImpl.save(field, "SpecTestXml")
      xmlImpl.load("SpecTestXml").toString should equal(Field(matrix, GreenPlayer, 1).toString)
    }

    "can getTargets" in {
      xmlImpl.getTargets() should contain("saveGameXml\\SpecTestXml")
    }
  }


}
