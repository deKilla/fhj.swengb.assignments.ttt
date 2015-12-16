package fhj.swengb.assignments.ttt.mfuchs

import javafx.application.Application
import javafx.event.EventHandler
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.{Label, Button}
import javafx.scene.input.MouseEvent
import javafx.scene.{Scene, Parent}
import javafx.stage.Stage

import scala.util.control.NonFatal

/**
  * Implement here your TicTacToe JavaFX App.
  */

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
    scene.getStylesheets().add(Css)
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
  @FXML private var btn_restart: Button = _
  @FXML private var lbl_winner: Label = _

  val buttons:List[Button] = List(btn_A1,btn_A2,btn_A3,btn_B1,btn_B2,btn_B3,btn_C1,btn_C2,btn_C3)

  def initialize(): Unit = {

  }

  def restart(): Unit = {
    game = TicTacToe()
    btn_A1.setText("")
    btn_A2.setText("")
    btn_A3.setText("")
    btn_B1.setText("")
    btn_B2.setText("")
    btn_B3.setText("")
    btn_C1.setText("")
    btn_C2.setText("")
    btn_C3.setText("")
    btn_A1.setDisable(false)
    btn_A2.setDisable(false)
    btn_A3.setDisable(false)
    btn_B1.setDisable(false)
    btn_B2.setDisable(false)
    btn_B3.setDisable(false)
    btn_C1.setDisable(false)
    btn_C2.setDisable(false)
    btn_C3.setDisable(false)
    lbl_winner.setText("")
  }


  var game = TicTacToe()

  def A1(): Unit = {
    btn_A1.setText(if (game.moveHistory.size % 2 == 0) "x" else "o")
    game = game.turn(TopLeft, game.nextPlayer)
    //println(game.asString())
    btn_A1.setDisable(true)
    if (game.gameOver && game.winner.isDefined) {
      lbl_winner.setText(game.winner.head._1.toString + " hat gewonnen")
      btn_A1.setDisable(true)
      btn_A2.setDisable(true)
      btn_A3.setDisable(true)
      btn_B1.setDisable(true)
      btn_B2.setDisable(true)
      btn_B3.setDisable(true)
      btn_C1.setDisable(true)
      btn_C2.setDisable(true)
      btn_C3.setDisable(true)
    }
  }

  def A2(): Unit = {
    btn_A2.setText(if (game.moveHistory.size % 2 == 0) "x" else "o")
    game = game.turn(TopCenter, game.nextPlayer)
    //println(game.asString())
    btn_A2.setDisable(true)
    if (game.gameOver && game.winner.isDefined) {
      lbl_winner.setText(game.winner.head._1.toString + " hat gewonnen")
      btn_A1.setDisable(true)
      btn_A2.setDisable(true)
      btn_A3.setDisable(true)
      btn_B1.setDisable(true)
      btn_B2.setDisable(true)
      btn_B3.setDisable(true)
      btn_C1.setDisable(true)
      btn_C2.setDisable(true)
      btn_C3.setDisable(true)
    }
  }

  def A3(): Unit = {
    btn_A3.setText(if (game.moveHistory.size % 2 == 0) "x" else "o")
    game = game.turn(TopRight, game.nextPlayer)
    //println(game.asString())
    btn_A3.setDisable(true)
    if (game.gameOver && game.winner.isDefined) {
      lbl_winner.setText(game.winner.head._1.toString + " hat gewonnen")
      btn_A1.setDisable(true)
      btn_A2.setDisable(true)
      btn_A3.setDisable(true)
      btn_B1.setDisable(true)
      btn_B2.setDisable(true)
      btn_B3.setDisable(true)
      btn_C1.setDisable(true)
      btn_C2.setDisable(true)
      btn_C3.setDisable(true)
    }
  }

  def B1(): Unit = {
    btn_B1.setText(if (game.moveHistory.size % 2 == 0) "x" else "o")
    game = game.turn(MiddleLeft, game.nextPlayer)
    //println(game.asString())
    btn_B1.setDisable(true)
    if (game.gameOver) buttons foreach(_.setDisable(true))
    if (game.gameOver && game.winner.isDefined) {
      lbl_winner.setText(game.winner.head._1.toString + " hat gewonnen")
      btn_A1.setDisable(true)
      btn_A2.setDisable(true)
      btn_A3.setDisable(true)
      btn_B1.setDisable(true)
      btn_B2.setDisable(true)
      btn_B3.setDisable(true)
      btn_C1.setDisable(true)
      btn_C2.setDisable(true)
      btn_C3.setDisable(true)
    }
  }

  def B2(): Unit = {
    btn_B2.setText(if (game.moveHistory.size % 2 == 0) "x" else "o")
    game = game.turn(MiddleCenter, game.nextPlayer)
    //println(game.asString())
    btn_B2.setDisable(true)
    if (game.gameOver && game.winner.isDefined) {
      lbl_winner.setText(game.winner.head._1.toString + " hat gewonnen")
      btn_A1.setDisable(true)
      btn_A2.setDisable(true)
      btn_A3.setDisable(true)
      btn_B1.setDisable(true)
      btn_B2.setDisable(true)
      btn_B3.setDisable(true)
      btn_C1.setDisable(true)
      btn_C2.setDisable(true)
      btn_C3.setDisable(true)
    }
  }

  def B3(): Unit = {
    btn_B3.setText(if (game.moveHistory.size % 2 == 0) "x" else "o")
    game = game.turn(MiddleRight, game.nextPlayer)
    //println(game.asString())
    btn_B3.setDisable(true)
    if (game.gameOver && game.winner.isDefined) {
      lbl_winner.setText(game.winner.head._1.toString + " hat gewonnen")
      btn_A1.setDisable(true)
      btn_A2.setDisable(true)
      btn_A3.setDisable(true)
      btn_B1.setDisable(true)
      btn_B2.setDisable(true)
      btn_B3.setDisable(true)
      btn_C1.setDisable(true)
      btn_C2.setDisable(true)
      btn_C3.setDisable(true)
    }
  }

  def C1(): Unit = {
    btn_C1.setText(if (game.moveHistory.size % 2 == 0) "x" else "o")
    game = game.turn(BottomLeft, game.nextPlayer)
    //println(game.asString())
    btn_C1.setDisable(true)
    if (game.gameOver && game.winner.isDefined) {
      lbl_winner.setText(game.winner.head._1.toString + " hat gewonnen")
      btn_A1.setDisable(true)
      btn_A2.setDisable(true)
      btn_A3.setDisable(true)
      btn_B1.setDisable(true)
      btn_B2.setDisable(true)
      btn_B3.setDisable(true)
      btn_C1.setDisable(true)
      btn_C2.setDisable(true)
      btn_C3.setDisable(true)
    }
  }

  def C2(): Unit = {
    btn_C2.setText(if (game.moveHistory.size % 2 == 0) "x" else "o")
    game = game.turn(BottomCenter, game.nextPlayer)
    //println(game.asString())
    btn_C2.setDisable(true)
    if (game.gameOver && game.winner.isDefined) {
      lbl_winner.setText(game.winner.head._1.toString + " hat gewonnen")
      btn_A1.setDisable(true)
      btn_A2.setDisable(true)
      btn_A3.setDisable(true)
      btn_B1.setDisable(true)
      btn_B2.setDisable(true)
      btn_B3.setDisable(true)
      btn_C1.setDisable(true)
      btn_C2.setDisable(true)
      btn_C3.setDisable(true)
    }
  }

  def C3(): Unit = {
    btn_C3.setText(if (game.moveHistory.size % 2 == 0) "x" else "o")
    game = game.turn(BottomRight, game.nextPlayer)
    //println(game.asString())
    btn_C3.setDisable(true)
    if (game.gameOver && game.winner.isDefined) {
      lbl_winner.setText(game.winner.head._1.toString + " hat gewonnen")
      btn_A1.setDisable(true)
      btn_A2.setDisable(true)
      btn_A3.setDisable(true)
      btn_B1.setDisable(true)
      btn_B2.setDisable(true)
      btn_B3.setDisable(true)
      btn_C1.setDisable(true)
      btn_C2.setDisable(true)
      btn_C3.setDisable(true)
    }
  }

}