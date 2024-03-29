package de.htwg.se.toybrokersludo

import de.htwg.se.toybrokersludo.FileIO.FileIO
import de.htwg.se.toybrokersludo.model.GameField

class FileIOStub extends FileIO:
  var saveCalls: List[(GameField, String)] = List()
  var loadCalls: List[String] = List()
  var getTargetsResult: List[String] = List()

  def save(gameField: GameField, target: String): Unit =
    saveCalls = (gameField, target) :: saveCalls

  def load(source: String): GameField =
    loadCalls = source :: loadCalls
    GameField.init()

  def getTargets: List[String] =
    getTargetsResult
