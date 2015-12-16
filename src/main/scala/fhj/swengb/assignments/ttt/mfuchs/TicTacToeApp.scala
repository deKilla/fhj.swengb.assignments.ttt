package fhj.swengb.assignments.ttt.mfuchs

import javafx.application.Application
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.Button
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

  }

  var game = TicTacToe()

  def A1(): Unit = {
    //btn_A1.setText("X")
    game = game.turn(TopLeft, game.nextPlayer)
    println(game.asString())
  }

  def A2(): Unit = {
    //btn_A2.setText("X")
    game = game.turn(TopCenter, game.nextPlayer)
    println(game.asString())
  }

  def A3(): Unit = {
    //btn_A3.setText("X")
    game = game.turn(TopRight, game.nextPlayer)
    println(game.asString())
  }

  def B1(): Unit = {
    //btn_B1.setText("X")
    game = game.turn(MiddleLeft, game.nextPlayer)
    println(game.asString())
  }

  def B2(): Unit = {
    //btn_B2.setText("X")
    game = game.turn(MiddleCenter, game.nextPlayer)
    println(game.asString())
  }

  def B3(): Unit = {
    //btn_B3.setText("X")
    game = game.turn(MiddleRight, game.nextPlayer)
    println(game.asString())
  }

  def C1(): Unit = {
    //btn_C1.setText("X")
    game = game.turn(BottomLeft, game.nextPlayer)
    println(game.asString())
  }

  def C2(): Unit = {
    //btn_C2.setText
    game = game.turn(BottomCenter, game.nextPlayer)
    println(game.asString())
  }

  def C3(): Unit = {
    //btn_C3.setText("X")
    game = game.turn(BottomRight, game.nextPlayer)
    println(game.asString())
  }

}