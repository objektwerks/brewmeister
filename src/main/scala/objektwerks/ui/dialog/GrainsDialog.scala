package objektwerks.ui.dialog

import scalafx.collections.ObservableBuffer

import scalafx.Includes.*
import scalafx.beans.property.ObjectProperty
import scalafx.geometry.Insets
import scalafx.scene.Node
import scalafx.scene.control.{ButtonType, ChoiceBox, Dialog, Label, ListView}
import scalafx.scene.control.ButtonBar.ButtonData
import scalafx.scene.layout.{HBox, VBox}

import objektwerks.{Grain, UoM}
import objektwerks.ui.{App, Context}
import objektwerks.ui.control.{ControlGrid, DoubleTextField, NonEmptyTextField}

final class GrainsDialog(context: Context, grains: Array[Grain]) extends Dialog[Array[Grain]]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogGrains

  var selectedName = ObjectProperty[String]("")
  val selectedWeight = ObjectProperty[String]("")
  val selectedUnit = ObjectProperty[String]("")

  val listViewGrains = new ListView[String]:
    items = ObservableBuffer.from(grains.map(_.name))
  listViewGrains.selectionModel().selectedItem.onChange { (_, _, newName) =>
    selectedName.value = newName
    // TODO find grain and bind all fields
  }

  val labelName = Label(context.labelName)
  val textFieldName = new NonEmptyTextField:
    text <== selectedName

  val labelWeight = Label(context.labelWeight)
  val textFieldWeight = new DoubleTextField:
    text <== selectedWeight

  val labelUnit = Label( context.labelUnit )
  val choiceBoxUnit = new ChoiceBox[String]:
  	items = ObservableBuffer.from( UoM.toList )
  	value <== selectedUnit

  val controls = List[(Label, Node)](
    labelName -> textFieldName,
    labelWeight -> textFieldWeight
  )
  val vboxControls = new VBox:
    spacing = 6
    padding = Insets(6)
    children = List( ControlGrid(controls) )

  val hboxContent = new HBox:
    spacing = 6
    padding = Insets(6)
    children = List(listViewGrains, vboxControls)

  dialogPane().content = hboxContent

  val saveButtonType = new ButtonType(context.buttonSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      grains // TODO
    else null