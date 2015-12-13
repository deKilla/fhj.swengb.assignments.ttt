package fhj.swengb.assignments.ttt.mfuchs

import scala.collection.Set

/**
  * models the different moves the game allows
  *
  * each move is made by either player a or player b.
  */
sealed trait TMove {
  def idx: Int
}

case object TopLeft extends TMove {
  override def idx: Int = 0
}

case object TopCenter extends TMove {
  override def idx: Int = 1
}

case object TopRight extends TMove {
  override def idx: Int = 2
}

case object MiddleLeft extends TMove {
  override def idx: Int = 3
}

case object MiddleCenter extends TMove {
  override def idx: Int = 4
}

case object MiddleRight extends TMove {
  override def idx: Int = 5
}

case object BottomLeft extends TMove {
  override def idx: Int = 6
}

case object BottomCenter extends TMove {
  override def idx: Int = 7
}

case object BottomRight extends TMove {
  override def idx: Int = 8
}


/**
  * for a tic tac toe game, there are two players, player A and player B
  */
sealed trait Player

case object PlayerA extends Player

case object PlayerB extends Player

object TicTacToe {

  /**
    * creates an empty tic tac toe game
    * @return
    */
  def apply(): TicTacToe = {
    val moveHistory:Map[TMove,Player] = Map.empty[TMove,Player]
    val ttt = TicTacToe(moveHistory)
    ttt
  }

  /**
    * For a given tic tac toe game, this function applies all moves to the game.
    * The first element of the sequence is also the first move.
    *
    * @param t
    * @param moves
    * @return
    */
  def play(t: TicTacToe, moves: Seq[TMove]): TicTacToe = {
    def changePlayer(player:Player):Player = {
      player match {
        case PlayerA => PlayerB
        case PlayerB => PlayerA
      }
    }
    val nextPlayer = changePlayer(t.nextPlayer)
    val movehistory = moves.foldLeft(Map.empty[TMove, Player])(
      (map,value) => map + (value -> changePlayer(nextPlayer))
    )
    val game = TicTacToe(movehistory,changePlayer(movehistory.last._2))
    game
  }

  /**
    * creates all possible games.
    * @return
    */
  def mkGames(): Map[Seq[TMove], TicTacToe] = ???

}

/**
  * Models the well known tic tac toe game.
  *
  * The map holds the information which player controls which field.
  *
  * The nextplayer parameter defines which player makes the next move.
  */
case class TicTacToe(moveHistory: Map[TMove, Player],
                     nextPlayer: Player = PlayerA) {

  /**
    * outputs a representation of the tic tac toe like this:
    *
    * |---|---|---|
    * | x | o | x |
    * |---|---|---|
    * | o | x | x |
    * |---|---|---|
    * | x | o | o |
    * |---|---|---|
    *
    *
    * @return
    */
  def asString(): String = {
    val separator:String = "|---|---|---|\n"
    val fields:Map[Int,String] = Map.empty[Int,String]

    for (move <- moveHistory) {

      val key:Int = move._1 match {
        case TopLeft => 1
        case TopCenter => 2
        case TopRight => 3
        case MiddleLeft => 4
        case MiddleCenter => 5
        case MiddleRight => 6
        case BottomLeft => 7
        case BottomCenter => 8
        case BottomRight => 9
      }

      val player:String = move._2 match {
        case PlayerA => "X"
        case PlayerB => "0"
        case _ => " "
      }

      fields + (key -> player)

    }

    val sortedfields = fields.toList.sortWith((x,y) => x._1 < y._1)

    val finalstring:String = (
      separator
      + "| " + sortedfields(1)._2 + " | " + sortedfields(2)._2 + " | " + sortedfields(3)._2 + " |\n"
      + separator
      + "| " + sortedfields(4)._2 + " | " + sortedfields(5)._2 + " | " + sortedfields(6)._2 + " |\n"
      + separator
      + "| " + sortedfields(7)._2 + " | " + sortedfields(8)._2 + " | " + sortedfields(9)._2 + " |\n"
      + separator
      )

    finalstring
  }

  /**
    * is true if the game is over.
    *
    * The game is over if either of a player wins or there is a draw.
    */
  val gameOver : Boolean = {
    if (winner != None) {true} else {false}
  }

  /**
    * the moves which are still to be played on this tic tac toe.
    */
  val remainingMoves: Set[TMove] = {
    val movesdone:Set[TMove] = moveHistory.keySet
    val potentialmoves:Set[TMove] = Set(TopLeft,TopCenter,TopRight,MiddleLeft,MiddleCenter,MiddleRight,BottomLeft,BottomCenter,BottomRight)
    val remainingmoves:Set[TMove] = potentialmoves.diff(movesdone)
    remainingmoves
  }

  /**
    * given a tic tac toe game, this function returns all
    * games which can be derived by making the next turn. that means one of the
    * possible turns is taken and added to the set.
    */
  lazy val nextGames: Set[TicTacToe] = ???

  /**
    * Either there is no winner, or PlayerA or PlayerB won the game.
    *
    * The set of moves contains all moves which contributed to the result.
    */
  def winner: Option[(Player, Set[TMove])] = {

    def checkEquality(p1:Player,p2:Player,p3:Player):Option[Player] = {
      if (p1 == p2 && p2 == p3) {Some(p1)} else {None}
    }

    /*
    if (moveHistory.contains(TopLeft)) {val a1 = moveHistory(TopLeft)}
    if (moveHistory.contains(TopCenter)) {val a2 = moveHistory(TopCenter)}
    if (moveHistory.contains(TopRight)) {val a3 = moveHistory(TopRight)}
    if (moveHistory.contains(MiddleLeft)) {val b1 = moveHistory(MiddleLeft)}
    if (moveHistory.contains(MiddleCenter)) {val b2 = moveHistory(MiddleCenter)}
    if (moveHistory.contains(MiddleRight)) {val b3 = moveHistory(MiddleRight)}
    if (moveHistory.contains(BottomLeft)) {val c1 = moveHistory(BottomLeft)}
    if (moveHistory.contains(BottomCenter)) {val c2 = moveHistory(BottomCenter)}
    if (moveHistory.contains(BottomRight)) {val c3 = moveHistory(BottomRight)}
    */

    val a1 = moveHistory(TopLeft)
    val a2 = moveHistory(TopCenter)
    val a3 = moveHistory(TopRight)
    val b1 = moveHistory(MiddleLeft)
    val b2 = moveHistory(MiddleCenter)
    val b3 = moveHistory(MiddleRight)
    val c1 = moveHistory(BottomLeft)
    val c2 = moveHistory(BottomCenter)
    val c3 = moveHistory(BottomRight)

    val g1 = (a1,a2,a3)
    val g2 = (b1,b2,b3)
    val g3 = (c1,c2,c3)
    val g4 = (a1,b1,c1)
    val g5 = (a2,b2,c2)
    val g6 = (a3,b3,c3)
    val g7 = (a1,b2,c3)
    val g8 = (a3,b2,c1)

    val winninggames = List(g1,g2,g3,g4,g5,g6,g7,g8)

    def detWinner():Unit = (winninggames).foreach { case game => {
          checkEquality(game._1, game._2, game._3)
        }
    }

    val stepsA = moveHistory.filter(_._2 == PlayerA).keySet
    val stepsB = moveHistory.filter(_._2 == PlayerB).keySet

    if (detWinner() == PlayerA) Some(PlayerA, stepsA)
    else if (detWinner() == PlayerB) Some(PlayerB, stepsB)
    else None

  }

  /**
    * returns a copy of the current game, but with the move applied to the tic tac toe game.
    *
    * @param move to be played
    * @param player the player
    * @return
    */
  def turn(p: TMove, player: Player): TicTacToe = {
    if(player == PlayerA) {
      TicTacToe(moveHistory.updated(p,player),PlayerB)
    } else {
      TicTacToe(moveHistory.updated(p,player),PlayerA)
    }
  }
}