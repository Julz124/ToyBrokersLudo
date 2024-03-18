package de.htwg.se.toybrokersludo.model

enum Player(val color: String) {
  case Red extends Player("Red")
  case Blue extends Player("Blue")
  case Yellow extends Player("Yellow")
  case Green extends Player("Green")

  def next: Player =
    this match {
      case Player.Green => Player.Red
      case Player.Red => Player.Blue
      case Player.Blue => Player.Yellow
      case Player.Yellow => Player.Red
    }

  def firstCellIndex: Int =
    this match {
      case Player.Green => 20
      case Player.Red => 30
      case Player.Yellow => 50
      case Player.Blue => 40
    }

  def lastCellIndex(): Int =
    this match {
      case Player.Green => 59
      case Player.Red => 29
      case Player.Yellow => 49
      case Player.Blue => 39
    }

  def endCellIndexes(): List[Int] =
    this match {
      case Player.Green => List(70, 71, 72, 73)
      case Player.Red => List(74, 75, 76, 77)
      case Player.Yellow => List(82, 83, 84, 85)
      case Player.Blue => List(78, 79, 80, 81)
    }
}