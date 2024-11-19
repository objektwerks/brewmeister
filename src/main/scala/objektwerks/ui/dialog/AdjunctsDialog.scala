package objektwerks.ui.dialog

import scalafx.Includes.*
import scalafx.scene.control.{ButtonType, Dialog}
import scalafx.scene.control.ButtonBar.ButtonData

import objektwerks.Adjunct
import objektwerks.ui.{App, Context}

final class AdjunctsDialog(context: Context, adjuncts: Array[Adjunct]) extends Dialog[Array[Adjunct]]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogGrain

  val saveButtonType = new ButtonType(context.buttonSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      adjuncts // TODO
    else null