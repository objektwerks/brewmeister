package objektwerks.ui.dialog

import scalafx.collections.ObservableBuffer
import scalafx.Includes.*
import scalafx.scene.Node
import scalafx.scene.control.{ButtonType, ChoiceBox, Dialog, Label}
import scalafx.scene.control.ButtonBar.ButtonData

import objektwerks.{IntRange, TempRangeDuration, UoT}
import objektwerks.ui.{App, Context}
import objektwerks.ui.control.{ControlGrid, IntTextField, LabelRangeSlider}

final class TempRangeDurationDialog(context: Context, tempRangeDuration: TempRangeDuration) extends Dialog[TempRangeDuration]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogTempRangeDuration

  val labelTempRange = Label( context.labelTempRange )
  val labelRangeSliderTempRange = LabelRangeSlider(
    min = tempRangeDuration.fixedTempRange.low,
    max = tempRangeDuration.fixedTempRange.high,
    increment = 1,
    low = tempRangeDuration.modelTempRange.low,
    high = tempRangeDuration.modelTempRange.high)

  val labelDuration = Label( context.labelDuration )
  val textFieldDuration = new IntTextField:
    text = tempRangeDuration.duration.toString

  val labelUnit = Label( context.labelUnit )
  val choiceBoxUnit = new ChoiceBox[String]:
  	items = ObservableBuffer.from( UoT.toList )
  	value = tempRangeDuration.unit.toString

  val controls = List[(Label, Node)](
    labelTempRange -> labelRangeSliderTempRange,
    labelDuration -> textFieldDuration,
    labelUnit -> choiceBoxUnit
  )

  dialogPane().content = ControlGrid(controls)

  val saveButtonType = ButtonType(context.buttonSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      tempRangeDuration.copy(
        fixedTempRange = tempRangeDuration.fixedTempRange,
        modelTempRange = IntRange( labelRangeSliderTempRange.lowValue.toInt, labelRangeSliderTempRange.highValue.toInt ),
        duration = textFieldDuration.int,
        unit = UoT.valueOf( choiceBoxUnit.value.value )
      )
    else null