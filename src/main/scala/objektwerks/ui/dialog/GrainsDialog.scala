package objektwerks.ui.dialog

import scalafx.Includes.*
import scalafx.scene.control.{ButtonType, Dialog}
import scalafx.scene.control.ButtonBar.ButtonData

import objektwerks.Grain
import objektwerks.ui.{App, Context}

final class GrainsDialog(context: Context, grains: Array[Grain]) extends Dialog[Array[Grain]]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogGrain

  val saveButtonType = new ButtonType(context.buttonSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      grains // TODO
    else null