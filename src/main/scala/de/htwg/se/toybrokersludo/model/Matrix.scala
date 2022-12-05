package de.htwg.se.toybrokersludo.model

import de.htwg.se.toybrokersludo.model


case class Matrix(var map: List[List[Stone]] = List(
  List(
    Stone(true, 0, None), Stone(false, -1, None), Stone(true, 1, None), Stone(false, -1, None),
    Stone(true, 28, None), Stone(true, 29, None), Stone(true, 30, None),
    Stone(false, -1, None), Stone(true, 4, None), Stone(false, -1, None), Stone(true, 5, None)),
  List(
    Stone(false, -1, None), Stone(false, -1, None), Stone(false, -1, None), Stone(false, -1, None),
    Stone(true, 27, None), Stone(true, 64, None), Stone(true, 31, None),
    Stone(false, -1, None), Stone(false, -1, None), Stone(false, -1, None), Stone(false, -1, None)),
  List(
    Stone(true, 2, None), Stone(false, -1, None), Stone(true, 3, None), Stone(false, -1, None),
    Stone(true, 26, None), Stone(true, 65, None), Stone(true, 32, None),
    Stone(false, -1, None), Stone(true, 6, None), Stone(false, -1, None), Stone(true, 7, None)),
  List(
    Stone(false, -1, None), Stone(false, -1, None), Stone(false, -1, None), Stone(false, -1, None),
    Stone(true, 25, None), Stone(true, 66, None), Stone(true, 33, None),
    Stone(false, -1, None), Stone(false, -1, None), Stone(false, -1, None), Stone(false, -1, None)),
  List(
    Stone(true, 20, None), Stone(true, 21, None), Stone(true, 22, None), Stone(true, 23, None),
    Stone(true, 24, None), Stone(true, 67, None), Stone(true, 33, None),
    Stone(true, 35, None), Stone(true, 36, None), Stone(true, 37, None), Stone(true, 38, None)),
  List(
    Stone(true, 59, None), Stone(true, 60, None), Stone(true, 61, None), Stone(true, 62, None),
    Stone(true, 63, None), Stone(false, -1, None), Stone(true, 71, None),
    Stone(true, 70, None), Stone(true, 69, None), Stone(true, 68, None), Stone(true, 39, None)),
  List(
    Stone(true, 58, None), Stone(true, 57, None), Stone(true, 56, None), Stone(true, 55, None),
    Stone(true, 54, None), Stone(true, 75, None), Stone(true, 44, None),
    Stone(true, 43, None), Stone(true, 42, None), Stone(true, 41, None), Stone(true, 40, None)),
  List(
    Stone(false, -1, None), Stone(false, -1, None), Stone(false, -1, None), Stone(false, -1, None),
    Stone(true, 53, None), Stone(true, 74, None), Stone(true, 45, None),
    Stone(false, -1, None), Stone(false, -1, None), Stone(false, -1, None), Stone(false, -1, None)),
  List(
    Stone(true, 8, None), Stone(false, -1, None), Stone(true, 9, None), Stone(false, -1, None),
    Stone(true, 52, None), Stone(true, 73, None), Stone(true, 46, None),
    Stone(false, -1, None), Stone(true, 12, None), Stone(false, -1, None), Stone(true, 13, None)),
  List(
    Stone(false, -1, None), Stone(false, -1, None), Stone(false, -1, None), Stone(false, -1, None),
    Stone(true, 51, None), Stone(true, 72, None), Stone(true, 47, None),
    Stone(false, -1, None), Stone(false, -1, None), Stone(false, -1, None), Stone(false, -1, None)),
  List(
    Stone(true, 10, None), Stone(false, -1, None), Stone(true, 11, None), Stone(false, -1, None),
    Stone(true, 50, None), Stone(true, 49, None), Stone(true, 48, None),
    Stone(false, -1, None), Stone(true, 14, None), Stone(false, -1, None), Stone(true, 15, None)),
)) {



  def put(move : Move) : Matrix =
    val a = map.indexWhere((list: List[Stone]) => list.exists((stone: Stone) => stone.index == move.number))
    val stone = map(a)(map(a).indexWhere((stone: Stone) => stone.index == move.number))
    val list = map(a).updated(map(a).indexWhere((stone: Stone) => stone.index == move.number),
      Stone(stone.isAPlayField, stone.index, Option(move.token)))
    this.copy(map.updated(a, list))


  def pull(move : Move) : Matrix =
    val a = map.indexWhere((list: List[Stone]) => list.exists((stone: Stone) => stone.player match
      case Some(player: Token) => player.equals(move.token)
      case None => false))
    val stone = map(a)(map(a).indexWhere((stone: Stone) => stone.player match
      case Some(player: Token) => player.equals(move.token)
      case None => false))
    val list = map(a).updated(map(a).indexWhere((stone: Stone) => stone.player match
      case Some(player: Token) => player.equals(move.token)
      case None => false),
      Stone(stone.isAPlayField, stone.index, None))
    this.copy(map.updated(a, list))


  def move(move : Move) : Matrix = {
    this.copy(pull(move).put(move).map)
  }

  def getToken: List[Move] =
    map.flatten.filter((stone: Stone) => stone.player != None).map((stone: Stone) => Move(stone.player match
      case Some(playToken: Token) => playToken, stone.index))
  
  
/*

+----+      +----+      +----++----++----+      +----+      +----+
| G0 |      | G1 |      | 28 || 29 || 30 |      | R4 |      | R5 |
+----+      +----+      +----++----++----+      +----+      +----+
                        +----++----++----+
                        | 27 || 74 || 31 |
                        +----++----++----+
+----+      +----+      +----++----++----+      +----+      +----+
| G2 |      | G3 |      | 26 || 75 || 32 |      | R6 |      | R7 |
+----+      +----+      +----++----++----+      +----+      +----+
                        +----++----++----+
                        | 25 || 76 || 33 |
                        +----++----++----+
+----++----++----++----++----++----++----++----++----++----++----+
| 20 || 21 || 22 || 23 || 24 || 77 || 34 || 35 || 36 || 37 || 38 |
+----++----++----++----++----++----++----++----++----++----++----+
+----++----++----++----++----+      +----++----++----++----++----+
| 59 || 70 || 71 || 72 || 73 |      | 81 || 80 || 79 || 78 || 39 |
+----++----++----++----++----+      +----++----++----++----++----+
+----++----++----++----++----++----++----++----++----++----++----+
| 58 || 57 || 56 || 55 || 54 || 85 || 44 || 43 || 42 || 41 || 40 |
+----++----++----++----++----++----++----++----++----++----++----+
                        +----++----++----+
                        | 53 || 84 || 45 |
                        +----++----++----+
+----+      +----+      +----++----++----+      +----+      +----+
| Y8 |      | Y9 |      | 52 || 83 || 46 |      | B12|      | B13|
+----+      +----+      +----++----++----+      +----+      +----+
                        +----++----++----+
                        | 51 || 82 || 47 |
                        +----++----++----+
+----+      +----+      +----++----++----+      +----+      +----+
| Y10|      | Y11|      | 50 || 49 || 48 |      | B14|      | B15|
+----+      +----+      +----++----++----+      +----+      +----+

*/

  
}
