package de.htwg.se.toybrokersludo.model

import de.htwg.se.toybrokersludo.model.Stone

class Matrix() {

  val map: Vector[Vector[Stone]] = Vector(
    Vector(
      Stone(true, 0, None), Stone(false, -1, None),
      Stone(true, 1, None), Stone(false, -1, None),
      Stone(true, 29, None), Stone(true, 30, None),
      Stone(true, 31, None),
      Stone(false, -1, None), Stone(true, 4, None),
      Stone(false, -1, None), Stone(true, 5, None),

      Stone(false, -1, None), Stone(false, -1, None),
      Stone(false, -1, None), Stone(false , -1, None),
      Stone(true, 28, None), Stone(true, 74, None),
      Stone(false, 32, None), 
      Stone(false, -1, None), Stone(false, -1, None),
      Stone(false, -1, None), Stone(false, -1, None),
      
      Stone(true, 2, None), Stone(false, -1, None),
      Stone(true, 3, None), Stone(false, -1, None),
      Stone(true, 28, None), Stone(true, 74, None),
      Stone(true, 32, None),
      Stone(false, -1, None), Stone(true, 6, None),
      Stone(false, -1, None), Stone(true, 7, None),

      Stone(false, -1, None), Stone(false, -1, None),
      Stone(false, -1, None), Stone(false, -1, None),
      Stone(true, 25, None), Stone(true, 76, None),
      Stone(false, 34, None),
      Stone(false, -1, None), Stone(false, -1, None),
      Stone(false, -1, None), Stone(false, -1, None),

      Stone(false, -1, None), Stone(false, -1, None),
      Stone(false, -1, None), Stone(false, -1, None),
      Stone(true, 25, None), Stone(true, 76, None),
      Stone(false, 34, None),
      Stone(false, -1, None), Stone(false, -1, None),
      Stone(false, -1, None), Stone(false, -1, None),
    ))

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
