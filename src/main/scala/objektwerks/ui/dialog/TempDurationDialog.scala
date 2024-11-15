package objektwerks.ui.dialog

import scalafx.Includes.*
import scalafx.scene.control.{ButtonType, Dialog}
import scalafx.scene.control.ButtonBar.ButtonData

import objektwerks.TempDuration
import objektwerks.ui.{App, Context}

final class TempDurationDialog(context: Context, tempDuration: TempDuration) extends Dialog[TempDuration]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogGrain

  val saveButtonType = new ButtonType(context.buttonSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      tempDuration // TODO
    else null