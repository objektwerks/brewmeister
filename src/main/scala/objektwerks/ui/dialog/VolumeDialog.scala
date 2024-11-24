package objektwerks.ui.dialog

import scalafx.Includes.*
import scalafx.collections.ObservableBuffer
import scalafx.scene.Node
import scalafx.scene.control.{ButtonType, ChoiceBox, Dialog, Label}
import scalafx.scene.control.ButtonBar.ButtonData

import objektwerks.{UoM, Volume}
import objektwerks.ui.{App, Context}
import objektwerks.ui.control.DoubleTextField

final class VolumeDialog(context: Context, volume: Volume) extends Dialog[Volume]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogGrain

  val labelValue = Label( context.labelValue )
  val textFieldValue = new DoubleTextField:
    text = volume.value.toString

  val labelUnit = Label( context.labelUnit )
  val choiceBoxUnit = new ChoiceBox[String]:
  	items = ObservableBuffer.from( UoM.toList )
  	value = volume.unit.toString

  val controls = List[(Label, Node)](
    labelValue -> textFieldValue,
    labelUnit -> choiceBoxUnit
  )

  val saveButtonType = new ButtonType(context.buttonSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      volume // TODO
    else null