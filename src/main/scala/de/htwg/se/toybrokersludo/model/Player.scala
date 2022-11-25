package de.htwg.se.toybrokersludo.model

trait Player { //Strategy-Pattern
  def defaultField(): List[Int]

  def startField(): Int

  def endFields(): List[Int]
}

object GreenPlayer extends Player {
  override def defaultField(): List[Int] = List(0, 1, 2, 3)

  override def startField(): Int = 20

  override def endFields(): List[Int] = List(70, 71, 72, 73)

  override def playerString = "G"
}

object RedPlayer extends Player {
  override def defaultField(): List[Int] = List(4, 5, 6, 7)

  override def startField(): Int = 30

  override def endFields(): List[Int] = List(74, 75, 76, 77)
}

object BluePlayer extends Player {
  override def defaultField(): List[Int] = List(12, 13, 14, 15)

  override def startField(): Int = 40

  override def endFields(): List[Int] = List(78, 79, 80, 81)
}

object YellowPlayer extends Player {
  override def defaultField(): List[Int] = List(8, 9, 10, 11)

  override def startField(): Int = 50

  override def endFields(): List[Int] = List(82, 83, 84, 85)

  override def playerString = "Y"
}



