package objektwerks.ui.dialog

import scalafx.Includes.*
import scalafx.collections.ObservableBuffer
import scalafx.scene.Node
import scalafx.scene.control.{ButtonType, ChoiceBox, Dialog, Label}
import scalafx.scene.control.ButtonBar.ButtonData

import objektwerks.*
import objektwerks.ui.{App, Context}
import objektwerks.ui.control.{ControlGrid, DoubleTextField}

final class VolumeDialog(context: Context, volume: Volume) extends Dialog[Volume]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogVolume

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

  dialogPane().content = ControlGrid(controls)

  val saveButtonType = ButtonType(context.buttonSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      volume.copy(
        value = textFieldValue.double.format,
        unit = UoM.valueOf( choiceBoxUnit.value.value )
      )
    else null