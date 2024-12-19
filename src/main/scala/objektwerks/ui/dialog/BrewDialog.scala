package objektwerks.ui.dialog

import scalafx.scene.control.{ButtonType, Dialog}

import objektwerks.Batch
import objektwerks.ui.{App, Context}

final class BrewDialog(context: Context) extends Dialog[Batch]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogBrew

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      context // TODO
    else null