package de.htwg.se.toybrokersludo.model

import de.htwg.se.toybrokersludo.model.Stone

class Matrix() {

  val map: List[List[Stone]] = List(
    List(
      Stone(true, 0, Option(Player(1))), Stone(false, -1, None), Stone(true, 1, None), Stone(false, -1, None),
      Stone(true, 28, None), Stone(true, 29, None), Stone(true, 30, None),
      Stone(false, -1, None), Stone(true, 4, None), Stone(false, -1, None), Stone(true, 5, None)),
    List(
      Stone(false, -1, None), Stone(false, -1, None), Stone(false, -1, None), Stone(false , -1, None),
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
      Stone(false, -1, None), Stone(true, 14, None), Stone(false, -1, None), Stone(true, 15, None))
    )

  def put(move : Move): Unit =
    map.flatMap(_.filter(_.index == move.number)).find(_ => true) match
      case None =>
      case Some(stone) => stone = Stone(stone.isAPlayField, stone.index, Option(move.player))








  // index 0 - 16 is for start, 20 - 60 for we play field and 70 - 86 for stop

  /*"can convert a map to String" in
    field.toString() == (
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
        "|  0  |   |  1  |       | 29 ||30  || 31 |      |  4  |      |  5  |" + eol +
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
        "                        +----++----++----+                        " + eol +
        "                        | 28 || 74  || 32 |                        " + eol +
        "                        +----++----++----+                        " + eol +
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
        "|  2  |      |  3  |    | 27  ||75   || 33 |      |6  |      |  7  |" + eol +
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
        "                        +----++----++----+                        " + eol +
        "                        |25  ||76  ||34  |                        " + eol +
        "                        +----++----++----+                        " + eol +
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
        "|20  | 21   | 22 |  23  | 24 || 78 || 35 |  36  | 37 | 38   |39 |" + eol +
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
        "+----+      +----+      +----+      +----+      +----+      +----+" + eol +
        "| 60 | 70   |71  |  72  | 73 |      |82  |  81    | 80   |79    |40 |" + eol +
        "+----+      +----+      +----+      +----+      +----+      +----+" + eol +
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
        "| 59 |  58  |57  |   56 | 55 ||86  ||45  |  44  |43  |42    |41  |" + eol +
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
        "                        +----++----++----+                        " + eol +
        "                        |  54||85  ||46  |                        " + eol +
        "                        +----++----++----+                        " + eol +
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
        "| 8  |      | 9   |      | 53 ||84  || 47 |    | 12   |    | 13   |" + eol +
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
        "                        +----++----++----+                        " + eol +
        "                        | 52 ||83  || 48 |                        " + eol +
        "                        +----++----++----+                        " + eol +
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol +
        "|10  |      |11  |      |51  ||50  || 49 |      |  14  |    | 15   |" + eol +
        "+----+      +----+      +----++----++----+      +----+      +----+" + eol
      )
  */
}
