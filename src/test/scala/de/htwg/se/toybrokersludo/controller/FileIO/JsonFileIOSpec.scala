import de.htwg.se.toybrokersludo.controller.FileIO.impl.JsonFileIO
import de.htwg.se.toybrokersludo.model.GameField
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.BeforeAndAfterEach
import java.io.File

class JsonFileIOSpec extends AnyWordSpec with Matchers with BeforeAndAfterEach {
  val sut = JsonFileIO()

  override def beforeEach(): Unit = {
    val folder = new File("saveGameJson")
    if (folder.exists()) {
      folder.listFiles().foreach(_.delete())
      folder.delete()
    }
  }

  "JsonFileIO" should {
    "save and load game field correctly" in {
      val gameField = GameField.init()
      val target = "test"

      sut.save(gameField, target)
      val loadedGameField = sut.load(target)
      loadedGameField shouldBe gameField
    }

    "return empty list if no targets available" in {
      sut.getTargets shouldBe List()
    }

    "return list of targets available" in {
      val gameField1 = GameField.init()
      val gameField2 = GameField.init()

      sut.save(gameField1, "test1")
      sut.save(gameField2, "test2")

      sut.getTargets shouldBe List("test1", "test2")
    }

    "create folder if it does not exist" in {
      val folder = new File("saveGameJson")
      folder.exists() shouldBe false

      sut.save(GameField.init(), "test")
      
      folder.exists() shouldBe true
    }
  }
}

