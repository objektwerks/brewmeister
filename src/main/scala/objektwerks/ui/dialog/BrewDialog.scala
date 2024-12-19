package objektwerks.ui.dialog

import scalafx.scene.Node
import scalafx.scene.control.{ButtonType, Dialog, Label}
import scalafx.scene.control.ButtonBar.ButtonData

import objektwerks.Batch
import objektwerks.ui.{App, Context}
import objektwerks.ui.control.ControlGrid

final class BrewDialog(context: Context) extends Dialog[Batch]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogBrew

  val controls = List[(Label, Node)](
  )

  dialogPane().content = ControlGrid(controls)

  val saveButtonType = new ButtonType(context.tooltipSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      null // TODO
    else null