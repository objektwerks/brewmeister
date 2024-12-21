package objektwerks.ui.dialog

import scalafx.Includes.*
import scalafx.scene.control.{ButtonType, Dialog, Label, TextArea}
import scalafx.scene.layout.VBox

import objektwerks.ui.{App, Context, Model}

final class LogDialog(context: Context, model: Model) extends Dialog[Unit]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogLog

  val labelLog = Label( context.labelLog )
  val textAreaLog = new TextArea( model.selectedBatch.value.log.mkString ):
    wrapText = true

  dialogPane().content = new VBox:
    prefWidth = 400
    prefHeight = 200
    spacing = 3
    children = List(labelLog, textAreaLog)

  dialogPane().buttonTypes = List(ButtonType.Close)