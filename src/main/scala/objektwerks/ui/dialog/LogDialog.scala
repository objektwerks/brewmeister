package objektwerks.ui.dialog

import scalafx.Includes.*
import scalafx.scene.control.{ButtonType, Dialog, TextArea}
import scalafx.scene.layout.VBox

import objektwerks.ui.{App, Context, Model}

final class LogDialog(context: Context, model: Model) extends Dialog[Unit]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogLog

  val textAreaLog = new TextArea( model.selectedBatch.value.log.mkString ):
    wrapText = true

  dialogPane().content = new VBox:
    prefWidth = 500
    prefHeight = 250
    spacing = 3
    children = List(textAreaLog)

  dialogPane().buttonTypes = List(ButtonType.Close)