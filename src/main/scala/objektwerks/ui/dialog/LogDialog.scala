package objektwerks.ui.dialog

import scalafx.Includes.*
import scalafx.scene.Node
import scalafx.scene.control.{ButtonType, Dialog, Label, TextArea}

import objektwerks.ui.{App, Context, Model}
import objektwerks.ui.control.ControlGrid

final class LogDialog(context: Context, model: Model) extends Dialog[Unit]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogLog

  val labelLog = Label( context.labelLog )
  val textAreaLog = new TextArea( model.selectedBatch.value.log.mkString ):
    wrapText = true

  val controls = List[(Label, Node)](
    labelLog -> textAreaLog
  )

  dialogPane().content = ControlGrid(controls)

  dialogPane().buttonTypes = List(ButtonType.Close)