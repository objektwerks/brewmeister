package objektwerks.ui.dialog

import scalafx.scene.control.Dialog

import objektwerks.ui.{App, Context}

final class ProcessDialog(context: Context) extends Dialog[Unit]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogProcess