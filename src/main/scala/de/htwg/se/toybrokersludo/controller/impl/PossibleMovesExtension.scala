package de.htwg.se.toybrokersludo.controller.impl

import de.htwg.se.toybrokersludo.model.{Cell, Dice, GameField, Move, Player}

trait PossibleMovesExtension {
  this: DefaultController =>

  def possibleMoves(gameField: GameField): List[Move] =
    gameField.map.values
      .filter(cell => hasTokenFromCurrentPlayer(cell, gameField.currentPlayer))
      .filter(cell => moveOffsetIsLegal(cell, gameField))
      .map(cell => mapToMove(cell, gameField) )
      .toList

  private def hasTokenFromCurrentPlayer(cell: Cell, currentPlayer: Player): Boolean =
    cell.token match {
      case Some(token) => token.player == currentPlayer
      case None => false
    }

  private def moveOffsetIsLegal(cell: Cell, gameField: GameField): Boolean =
    if cell.index < 20 then
      gameField.dice.diceNumber == 6
    else
      gameField.dice match {
        case Dice(true, _) =>
          false
        case Dice(false, 6) =>
          gameField.map.find(cell =>
            cell._2.index == gameField.currentPlayer.firstCellIndex).get._2.token match {
            case Some(token) => token.player != gameField.currentPlayer
            case None => true
          }
        case Dice(false, number) =>
          add(gameField.currentPlayer, cell.index, gameField.dice.diceNumber) match
            case None => false
            case Some(newIndex) => gameField.map.find(cell =>
              cell._2.index == newIndex).get._2.token match {
              case Some(token) => token.player == gameField.currentPlayer
              case None => true
            }
      }

  private def mapToMove(cell: Cell, gameField: GameField): Move =
    if cell.index < 20 then
      Move(
        fromIndex = cell.index,
        toIndex = gameField.currentPlayer.firstCellIndex
      )
    else
      Move(
        fromIndex = cell.index,
        toIndex = add(gameField.currentPlayer, cell.index, gameField.dice.diceNumber).get
      )

  def add(player: Player, from: Int, dice: Int): Option[Int] =
    if (player.endCellIndexes().contains(from))
      Some(from + dice).filter(player.endCellIndexes().contains)
    else if (goOverEnd(player, from, dice))
      if from + dice - player.lastCellIndex() <= player.endCellIndexes().size then
        Some(player.endCellIndexes()(from + dice - player.lastCellIndex() - 1))
      else
        None
    else if (from + dice > Player.Green.lastCellIndex()) Some(from + dice - 40)
    else Some(from + dice)


  private def goOverEnd(player: Player, from: Int, dice: Int): Boolean =
    player match
      case Player.Green => from + dice > player.lastCellIndex()
      case _ => player.lastCellIndex() >= from && player.lastCellIndex() < from + dice
}
