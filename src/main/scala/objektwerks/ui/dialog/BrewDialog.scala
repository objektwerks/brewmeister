package objektwerks.ui.dialog

import scalafx.scene.control.Dialog

import objektwerks.Batch
import objektwerks.ui.Context

final class BrewDialog(context: Context) extends Dialog[Batch]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogBrew
