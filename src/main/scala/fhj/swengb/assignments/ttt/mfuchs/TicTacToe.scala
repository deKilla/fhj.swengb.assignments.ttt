package fhj.swengb.assignments.ttt.mfuchs

import java.rmi.activation.ActivationGroup_Stub
import javafx.application.Application
import javafx.scene.{Scene, Parent}
import javafx.stage.Stage
import javafx.fxml.{FXMLLoader, FXML}
import javafx.scene.control.{Button}

import fhj.swengb.assignments.ttt.mfuchs

import scala.collection.Set
import scala.util.control.NonFatal

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

    def checkEquality(p1:Player,p2:Player,p3:Player):Boolean = {
      if (p1 == p2 && p2 == p3) {true} else {false}
    }

    val a1 = moveHistory(TopLeft)
    val a2 = moveHistory(TopCenter)
    val a3 = moveHistory(TopRight)
    val b1 = moveHistory(MiddleLeft)
    val b2 = moveHistory(MiddleCenter)
    val b3 = moveHistory(MiddleRight)
    val c1 = moveHistory(BottomLeft)
    val c2 = moveHistory(BottomCenter)
    val c3 = moveHistory(BottomRight)

    val g1 = checkEquality(a1,a2,a3)
    val g2 = checkEquality(b1,b2,b3)
    val g3 = checkEquality(c1,c2,c3)
    val g4 = checkEquality(a1,b1,c1)
    val g5 = checkEquality(a2,b2,c2)
    val g6 = checkEquality(a3,b3,c3)
    val g7 = checkEquality(a1,b2,c3)
    val g8 = checkEquality(a3,b2,c1)

    if(g1 == true || g2 == true || g3 == true || g4 == true || g5 == true || g6 == true || g7 == true || g8 == true || moveHistory.size == 9) {true} else {false}

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
  def winner: Option[(Player, Set[TMove])] = ???

  /**
    * returns a copy of the current game, but with the move applied to the tic tac toe game.
    *
    * @param move to be played
    * @param player the player
    * @return
    */
  def turn(p: TMove, player: Player): TicTacToe = ???

}


object TicTacToeApp {
  def main(args: Array[String]) {
    Application.launch(classOf[TicTacToeApp], args: _*)
  }
}

class TicTacToeApp extends Application {

  val Fxml = "/fhj/swengb/assignments/ttt/TicTacToeApp.fxml"
  val Css = "fhj/swengb/assignments/ttt/TicTacToeApp.css" //TODO: css not working
  val loader = new FXMLLoader(getClass.getResource(Fxml))

  override def start(stage: Stage): Unit = try {
    stage.setTitle("Tic Tac Toe")
    loader.load[Parent]() // side effect
    val scene = new Scene(loader.getRoot[Parent])
    stage.setScene(scene)
    stage.getScene.getStylesheets.add(Css)
    stage.show()

  } catch {
    case NonFatal(e) => {
      e.printStackTrace()
    }
  }

}

class TicTacToeAppController {
  @FXML private var btn_A1: Button = _
  @FXML private var btn_A2: Button = _
  @FXML private var btn_A3: Button = _
  @FXML private var btn_B1: Button = _
  @FXML private var btn_B2: Button = _
  @FXML private var btn_B3: Button = _
  @FXML private var btn_C1: Button = _
  @FXML private var btn_C2: Button = _
  @FXML private var btn_C3: Button = _


  def initialize(): Unit = {
  //  test.setText("geht")
  }

  def A1(): Unit = {
    btn_A1.setText("X")
  }

  def A2(): Unit = {
    btn_A2.setText("X")
  }

  def A3(): Unit = {
    btn_A3.setText("X")
  }

  def B1(): Unit = {
    btn_B1.setText("X")
  }

  def B2(): Unit = {
    btn_B2.setText("X")
  }

  def B3(): Unit = {
    btn_B3.setText("X")
  }

  def C1(): Unit = {
    btn_C1.setText("X")
  }

  def C2(): Unit = {
    btn_C2.setText("X")
  }

  def C3(): Unit = {
    btn_C3.setText("X")
  }

}