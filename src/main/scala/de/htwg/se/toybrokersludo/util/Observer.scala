package de.htwg.se.toybrokersludo.util

trait Observer {
  def update: Unit
}

trait Observable {
  var subscribers: Vector[Observer] = Vector()

  def add(s: Observer) = subscribers = subscribers :+ s

  def notifyObservers = subscribers.foreach(o => o.update)
}