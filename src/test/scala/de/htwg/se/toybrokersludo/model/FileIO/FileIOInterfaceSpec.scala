package de.htwg.se.toybrokersludo.model.FileIO

import de.htwg.se.toybrokersludo.model.FileIO.FileIOInterface
import de.htwg.se.toybrokersludo.model.FieldInterface

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.should

class FileIOInterfaceSpec extends AnyWordSpec with Matchers {

  "json" should {
    import de.htwg.se.toybrokersludo.model.FileIO.JsonImpl.FileIo
    val jsonImpl: FileIOInterface = FileIo()

    "can save" in {
      val field: FieldInterface = Field(Matrix(),GreenPlayer,1)
      jsonImpl.save()
    }

    "can load" in {
      jsonImpl.load()
    }

    "can getTargets" in {
      jsonImpl.getTargets()
    }

  }

  "xml" should {
    import de.htwg.se.toybrokersludo.model.FileIO.XmlImpl.FileIo
    val xmlImpl: FileIOInterface = FileIo()
  }


}
