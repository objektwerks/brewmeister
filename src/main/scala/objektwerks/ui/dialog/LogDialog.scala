package objektwerks.ui.dialog

import scalafx.scene.control.{Dialog, Label, TextArea}

import objektwerks.ui.{App, Context, Model}

final class LogDialog(context: Context, model: Model) extends Dialog[Unit]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogLog

  val labelLog = Label( context.labelLog )
  val textAreaLog = new TextArea( model.selectedBatch.value.log.mkString ):
    wrapText = true