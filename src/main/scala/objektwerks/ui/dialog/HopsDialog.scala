package objektwerks.ui.dialog

import scalafx.Includes.*
import scalafx.collections.ObservableBuffer
import scalafx.scene.control.{ButtonType, Dialog, ListView}
import scalafx.scene.control.ButtonBar.ButtonData

import objektwerks.Hop
import objektwerks.ui.{App, Context}

final class HopsDialog(context: Context, hops: Array[Hop]) extends Dialog[Array[Hop]]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogHops

  // Model
  val updatedHops = hops.map(identity).toBuffer.sorted

  // List
  val listViewHops = new ListView[Hop]:
    items = ObservableBuffer.from(updatedHops)
    cellFactory = (cell, hop) => cell.text = hop.name

  val saveButtonType = new ButtonType(context.buttonSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      hops // TODO
    else null