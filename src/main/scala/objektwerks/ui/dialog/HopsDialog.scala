package objektwerks.ui.dialog

import scalafx.Includes.*
import scalafx.collections.ObservableBuffer
import scalafx.geometry.Insets
import scalafx.scene.control.{Button, ButtonType, ChoiceBox, Dialog, Label, ListView, SelectionMode}
import scalafx.scene.control.ButtonBar.ButtonData
import scalafx.scene.layout.{HBox, VBox}

import objektwerks.{Hop, UoM}
import objektwerks.ui.{App, Context}
import objektwerks.ui.control.{DoubleTextField, NonEmptyTextField}

final class HopsDialog(context: Context, hops: Array[Hop]) extends Dialog[Array[Hop]]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogHops

  // Model
  val updatedHops = hops.map(identity).toBuffer.sorted

  // Methods
  def select(hop: Hop): Unit =
    //saveButton.disable = false
    listViewHops.selectionModel().select(hop)
    //hopToControls(hop)
    listViewHops.scrollTo(hop)

  // List
  val listViewHops = new ListView[Hop]:
    items = ObservableBuffer.from(updatedHops)
    cellFactory = (cell, hop) => cell.text = hop.name

  listViewHops.selectionModel().selectionModeProperty.value = SelectionMode.Single
  listViewHops.selectionModel().selectedItemProperty().addListener { (_, _, selectedHop) =>
    if selectedHop != null then select(selectedHop)
  }

  val addButton = new Button:
    graphic = context.addImageView
    disable = true
    onAction = { _ => add() }

  val removeButton = new Button:
    graphic = context.removeImageView
    disable = true
    onAction = { _ => remove( listViewHops.selectionModel().selectedItem.value ) }

  val buttonBarHops = new HBox:
    spacing = 6
    children = List(addButton, removeButton)

  val vboxGrains = new VBox:
    spacing = 6
    padding = Insets(6)
    children = List( listViewHops, buttonBarHops )

  // Item
  val labelName = Label(context.labelName)
  val textFieldName = NonEmptyTextField()

  val labelWeight = Label(context.labelWeight)
  val textFieldWeight = DoubleTextField()

  val labelVolume = Label(context.labelVolume)
  val textFieldVolume = DoubleTextField()

  val labelUnit = Label( context.labelUnit )
  val choiceBoxUnit = new ChoiceBox[String]:
  	items = ObservableBuffer.from( UoM.toList )

  val saveButtonType = new ButtonType(context.buttonSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      hops // TODO
    else null