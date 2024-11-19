package objektwerks.ui.dialog

import scalafx.Includes.*
import scalafx.scene.control.{ButtonType, Dialog}
import scalafx.scene.control.ButtonBar.ButtonData

import objektwerks.Yeast
import objektwerks.ui.{App, Context}

final class YeastsDialog(context: Context, yeasts: Array[Yeast]) extends Dialog[Array[Yeast]]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogGrain

  val saveButtonType = new ButtonType(context.buttonSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      yeasts // TODO
    else null