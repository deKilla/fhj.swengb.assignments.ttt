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

  def updategame(field:Button,currentgame:TicTacToe): TicTacToe = {
    field.setText(if (currentgame.moveHistory.size % 2 == 0) "x" else "o")
    val buttonid = field.getId.toString
    val position:TMove = buttonid match {
      case "btn_A1" => TopLeft
      case "btn_A2" => TopCenter
      case "btn_A3" => TopRight
      case "btn_B1" => MiddleLeft
      case "btn_B2" => MiddleCenter
      case "btn_B3" => MiddleRight
      case "btn_C1" => BottomLeft
      case "btn_C2" => BottomCenter
      case "btn_C3" => BottomRight
    }
    val updatedgame = currentgame.turn(position, currentgame.nextPlayer)
    field.setDisable(true)
    if (updatedgame.gameOver) {
      if (updatedgame.winner.isDefined) lbl_winner.setText(updatedgame.winner.head._1 match {case PlayerA => "x hat gewonnen" case PlayerB => "o hat gewonnen"}) else lbl_winner.setText("unentschieden")
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
    updatedgame
  }

  def A1(): Unit = {val updatedgame = updategame(btn_A1,game); game = updatedgame}
  def A2(): Unit = {val updatedgame = updategame(btn_A2,game); game = updatedgame}
  def A3(): Unit = {val updatedgame = updategame(btn_A3,game); game = updatedgame}
  def B1(): Unit = {val updatedgame = updategame(btn_B1,game); game = updatedgame}
  def B2(): Unit = {val updatedgame = updategame(btn_B2,game); game = updatedgame}
  def B3(): Unit = {val updatedgame = updategame(btn_B3,game); game = updatedgame}
  def C1(): Unit = {val updatedgame = updategame(btn_C1,game); game = updatedgame}
  def C2(): Unit = {val updatedgame = updategame(btn_C2,game); game = updatedgame}
  def C3(): Unit = {val updatedgame = updategame(btn_C3,game); game = updatedgame}

  def a1e(): Unit = {btn_A1.setText(game.nextPlayer match {case PlayerA => "x" case PlayerB => "o"})}
  def a1l(): Unit = {if (!btn_A1.isDisabled) btn_A1.setText("")}
  def a2e(): Unit = {btn_A2.setText(game.nextPlayer match {case PlayerA => "x" case PlayerB => "o"})}
  def a2l(): Unit = {if (!btn_A2.isDisabled) btn_A2.setText("")}
  def a3e(): Unit = {btn_A3.setText(game.nextPlayer match {case PlayerA => "x" case PlayerB => "o"})}
  def a3l(): Unit = {if (!btn_A3.isDisabled) btn_A3.setText("")}
  def b1e(): Unit = {btn_B1.setText(game.nextPlayer match {case PlayerA => "x" case PlayerB => "o"})}
  def b1l(): Unit = {if (!btn_B1.isDisabled) btn_B1.setText("")}
  def b2e(): Unit = {btn_B2.setText(game.nextPlayer match {case PlayerA => "x" case PlayerB => "o"})}
  def b2l(): Unit = {if (!btn_B2.isDisabled) btn_B2.setText("")}
  def b3e(): Unit = {btn_B3.setText(game.nextPlayer match {case PlayerA => "x" case PlayerB => "o"})}
  def b3l(): Unit = {if (!btn_B3.isDisabled) btn_B3.setText("")}
  def c1e(): Unit = {btn_C1.setText(game.nextPlayer match {case PlayerA => "x" case PlayerB => "o"})}
  def c1l(): Unit = {if (!btn_C1.isDisabled) btn_C1.setText("")}
  def c2e(): Unit = {btn_C2.setText(game.nextPlayer match {case PlayerA => "x" case PlayerB => "o"})}
  def c2l(): Unit = {if (!btn_C2.isDisabled) btn_C2.setText("")}
  def c3e(): Unit = {btn_C3.setText(game.nextPlayer match {case PlayerA => "x" case PlayerB => "o"})}
  def c3l(): Unit = {if (!btn_C3.isDisabled) btn_C3.setText("")}



}