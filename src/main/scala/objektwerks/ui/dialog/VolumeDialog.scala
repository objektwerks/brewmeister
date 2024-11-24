package objektwerks.ui.dialog

import scalafx.Includes.*
import scalafx.scene.Node
import scalafx.scene.control.{ButtonType, Dialog, Label}
import scalafx.scene.control.ButtonBar.ButtonData

import objektwerks.Volume
import objektwerks.ui.{App, Context}
import objektwerks.ui.control.DoubleTextField

final class VolumeDialog(context: Context, volume: Volume) extends Dialog[Volume]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogGrain

  val labelValue = Label( context.labelValue )
  val textFieldValue = new DoubleTextField:
    text = volume.value.toString

  val controls = List[(Label, Node)](
    labelValue -> textFieldValue
  )

  val saveButtonType = new ButtonType(context.buttonSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      volume // TODO
    else null