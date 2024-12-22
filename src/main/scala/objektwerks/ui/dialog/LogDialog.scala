package objektwerks.ui.dialog

import scalafx.Includes.*
import scalafx.scene.control.{ButtonType, Dialog, ListView}
import scalafx.scene.layout.VBox

import objektwerks.ui.{App, Context, Model}

final class LogDialog(context: Context, model: Model) extends Dialog[Unit]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogLog

  val listViewLog = ListView( model.selectedBatch.value.log )

  dialogPane().content = new VBox:
    prefWidth = 600
    prefHeight = 600
    spacing = 3
    children = List(listViewLog)

  dialogPane().buttonTypes = List(ButtonType.Close)