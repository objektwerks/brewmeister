package objektwerks.ui.dialog

import scalafx.Includes.*
import scalafx.scene.control.{ButtonType, Dialog, Label}
import scalafx.scene.control.ButtonBar.ButtonData

import objektwerks.Volume
import objektwerks.ui.{App, Context}

final class VolumeDialog(context: Context, volume: Volume) extends Dialog[Volume]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogGrain

  val labelValue = Label( context.labelValue )

  val saveButtonType = new ButtonType(context.buttonSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      volume // TODO
    else null