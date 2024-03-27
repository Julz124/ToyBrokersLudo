package de.htwg.se.toybrokersludo.FileIO

import de.htwg.se.toybrokersludo.model.GameField
import play.api.libs.json.{JsValue, Json, Writes}

import scala.concurrent.Future

trait FileIO:
  def save(gameField: GameField, target: String): Unit
  def load(source: String): Future[GameField]
  def getTargets: List[String]

