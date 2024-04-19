package Persistence.DB

import model.GameField

trait DAOInterface:
  def save(gameField: GameField): Unit
  def load(): GameField

