package objektwerks.ui.dialog

import org.controlsfx.control.RangeSlider

import scalafx.Includes.*
import scalafx.scene.Node
import scalafx.scene.control.{ButtonType, Dialog, Label}
import scalafx.scene.control.ButtonBar.ButtonData

import objektwerks.TempRangeDuration
import objektwerks.ui.{App, Context}
import objektwerks.ui.control.{ControlGrid, IntTextField}

final class TempRangeDurationDialog(context: Context, tempRangeDuration: TempRangeDuration) extends Dialog[TempRangeDuration]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogGrain

  val labelTempRange = Label( context.labelTempRange )
  val rangeSliderTempRange = new RangeSlider(
    tempRangeDuration.tempRange.low,
    tempRangeDuration.tempRange.high,
    tempRangeDuration.tempRange.low,
    tempRangeDuration.tempRange.high ):
      setShowTickMarks(true)
      setShowTickLabels(true)
      setBlockIncrement(1)

  val labelDuration = Label( context.labelDuration )
  val textFieldDuration = new IntTextField:
    text = tempRangeDuration.duration.toString

  val controls = List[(Label, Node)](
    labelTempRange -> rangeSliderTempRange,
    labelDuration -> rangeSliderTempRange
  )

  dialogPane().content = ControlGrid(controls)

  val saveButtonType = new ButtonType(context.buttonSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      tempRangeDuration // TODO
    else null