package fhj.swengb.assignments.ttt.mfuchs

import scala.collection.JavaConverters._
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

    val movehistory = moves.foldLeft(Map.empty[TMove, Player])(
      (map,value) => map + (value -> changePlayer(t.nextPlayer))
    )
    val nextPlayer = changePlayer(movehistory.last._2)
    val game = TicTacToe(movehistory,nextPlayer)
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

    val separator:String = "+ - + - + - +\n"

    var fields:Map[Int,String] = Map.empty[Int,String]

    for (move <- moveHistory) {

      val position:Int = move._1 match {
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

      val symbol:String = move._2 match {
        case PlayerA => "x"
        case PlayerB => "o"
        case _ => " "
      }

      fields = fields + (position -> symbol)

    }

    val tl:String = if (fields.contains(1)) { fields(1) } else { " " }
    val tc:String = if (fields.contains(2)) { fields(2) } else { " " }
    val tr:String = if (fields.contains(3)) { fields(3) } else { " " }
    val ml:String = if (fields.contains(4)) { fields(4) } else { " " }
    val mc:String = if (fields.contains(5)) { fields(5) } else { " " }
    val mr:String = if (fields.contains(6)) { fields(6) } else { " " }
    val bl:String = if (fields.contains(7)) { fields(7) } else { " " }
    val bc:String = if (fields.contains(8)) { fields(8) } else { " " }
    val br:String = if (fields.contains(9)) { fields(9) } else { " " }


    val finalstring:String = (
      separator
      + "| " + tl + " | " + tc + " | " + tr + " |\n"
      + separator
      + "| " + ml + " | " + mc + " | " + mr + " |\n"
      + separator
      + "| " + bl + " | " + bc + " | " + br + " |\n"
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
    if (winner.isDefined || moveHistory.size == 9) {true} else {false}
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

    val stepsA = moveHistory.filter(_._2 == PlayerA).keySet
    val stepsB = moveHistory.filter(_._2 == PlayerB).keySet

    val winninggames: List[Tuple3[TMove,TMove,TMove]] = List(
      (TopLeft, TopCenter, TopRight),
      (MiddleLeft,MiddleCenter,MiddleRight),
      (BottomLeft,BottomCenter,BottomRight),
      (TopLeft,MiddleLeft,BottomLeft),
      (TopCenter,MiddleCenter,BottomCenter),
      (TopRight,MiddleRight,BottomRight),
      (TopLeft,MiddleCenter,BottomRight),
      (TopRight,MiddleCenter,BottomLeft))

    var winner = ""

    for (game <- winninggames) {
      if (stepsA.contains(game._1) && stepsA.contains(game._2) && stepsA.contains(game._3)) {winner = "A"}//; scala.util.control.Breaks.break()}
      else if (stepsB.contains(game._1) && stepsB.contains(game._2) && stepsB.contains(game._3)) {winner = "B"}//; scala.util.control.Breaks.break()}
    }

    if (winner == "A") Some(PlayerA, stepsA)
    else if (winner == "B") Some(PlayerB, stepsB)
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
    player match {
      case PlayerA => TicTacToe(moveHistory + (p -> player),PlayerB)
      case PlayerB => TicTacToe(moveHistory + (p -> player),PlayerA)
    }
  }
}