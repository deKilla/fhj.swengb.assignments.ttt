package fhj.swengb.assignments.ttt.mfuchs

import java.rmi.activation.ActivationGroup_Stub
import javafx.application.Application
import javafx.scene.{Scene, Parent}
import javafx.stage.Stage
import javafx.fxml.{FXMLLoader, FXML}
import javafx.scene.control.{Button}

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
  def apply(): TicTacToe = ???

  /**
    * For a given tic tac toe game, this function applies all moves to the game.
    * The first element of the sequence is also the first move.
    *
    * @param t
    * @param moves
    * @return
    */
  def play(t: TicTacToe, moves: Seq[TMove]): TicTacToe = ???

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
  def asString(): String = ???

  /**
    * is true if the game is over.
    *
    * The game is over if either of a player wins or there is a draw.
    */
  val gameOver : Boolean = ???

  /**
    * the moves which are still to be played on this tic tac toe.
    */
  val remainingMoves: Set[TMove] = ???

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


object TickTackToeApp {
  def main(args: Array[String]) {
    Application.launch(classOf[TickTackToeApp], args: _*)
  }
}

class TickTackToeApp extends Application {

  val Fxml = "/fhj/swengb/assignments/ttt/TicTacToeApp.fxml"
  val Css = "fhj/swengb/assignments/ttt/TicTacToeApp.css"
  val loader = new FXMLLoader(getClass.getResource(Fxml))

  override def start(stage: Stage): Unit = try {
    stage.setTitle("Tick Tack Toe")
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

class TickTackToeAppController {
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