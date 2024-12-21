package objektwerks.ui.dialog

import scalafx.scene.control.{Dialog, Label}

import objektwerks.ui.{App, Context, Model}

final class ProcessDialog(context: Context, model: Model) extends Dialog[Unit]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogProcess

  val process = model.selectedBatch.value.process

  val labelSanitizeStarted = Label( context.labelSanitizeStarted )
  val textSanitizedStarted = Label( process.sanitizingStarted )
