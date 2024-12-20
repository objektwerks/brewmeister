package objektwerks.ui.dialog

import scalafx.scene.control.Dialog

import objektwerks.ui.{App, Context}

final class LogDialog(context: Context) extends Dialog[Unit]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogLog