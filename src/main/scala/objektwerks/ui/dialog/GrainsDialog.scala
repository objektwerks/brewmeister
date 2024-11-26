package objektwerks.ui.dialog

import scalafx.collections.ObservableBuffer

import scalafx.Includes.*
import scalafx.scene.Node
import scalafx.scene.control.{ButtonType, Dialog, Label, ListView}
import scalafx.scene.control.ButtonBar.ButtonData
import javafx.scene.layout.HBox

import objektwerks.Grain
import objektwerks.ui.{App, Context}
import objektwerks.ui.control.NonEmptyTextField

final class GrainsDialog(context: Context, grains: Array[Grain]) extends Dialog[Array[Grain]]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogGrains

  var selectedGrain = ""

  val listViewGrains = new ListView[String]:
    items = ObservableBuffer.from(grains.map(_.name))
    prefHeight = 100.0
  listViewGrains.selectionModel().selectedItem.onChange { (_, _, newValue) => selectedGrain = newValue }

  val labelName = Label(context.labelName)
  val textFieldName = new NonEmptyTextField:
    text = selectedGrain

  val controls = List[(Label, Node)](
    labelName -> textFieldName
  )

  val hbox = new HBox:
    children = List(listViewGrains, controls)

  val saveButtonType = new ButtonType(context.buttonSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      grains // TODO
    else null