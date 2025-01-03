package objektwerks.ui.dialog

import scalafx.Includes.*
import scalafx.beans.property.ObjectProperty
import scalafx.collections.ObservableBuffer
import scalafx.geometry.Insets
import scalafx.scene.Node
import scalafx.scene.control.{Button, ButtonType, ChoiceBox, Dialog, Label, ListView, SelectionMode}
import scalafx.scene.control.ButtonBar.ButtonData
import scalafx.scene.layout.{HBox, VBox}

import objektwerks.{MixinStep, UoM, Yeast}
import objektwerks.ui.{App, Context}
import objektwerks.ui.control.{ControlGrid, DoubleTextField, IntTextField, NonEmptyTextField}

final class YeastsDialog(context: Context, yeasts: Array[Yeast]) extends Dialog[Array[Yeast]]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogYeasts

  // Model
  var observableYeasts = ObservableBuffer.from( yeasts.map(identity).toBuffer.sorted )

  // Methods
  def select(yeast: Yeast): Unit =
    buttonRemove.disable = false
    saveButton.disable = false
    listViewYeasts.selectionModel().select(yeast)
    listViewYeasts.scrollTo(yeast)
    yeastToControls(yeast)

  def add(): Unit =
    val yeast = Yeast()
    observableYeasts += yeast // listview items refresh?
    select(yeast)

  def remove(yeast: Yeast): Unit =
    observableYeasts -= yeast // listview items refresh?
    resetControls()
    saveButton.disable = true
    if !listViewYeasts.selectionModel().isEmpty() then
      listViewYeasts.selectionModel().select(0)
      select( listViewYeasts.selectionModel().selectedItem() )

  def save(): Unit =
    val index = listViewYeasts.selectionModel().selectedIndex.value
    val yeast = listViewYeasts.selectionModel().selectedItem.value
    observableYeasts.update(index, yeast) // listview items refresh?

  // Bindings
  def yeastToControls(yeast: Yeast): Unit =
    textFieldName.text = yeast.name
    textFieldWeight.text = yeast.weight.toString
    choiceBoxUnit.value = yeast.unit.toString
    textFieldMixinMinute.text = yeast.mixinMinute.toString
    choiceBoxMixinStep.value = yeast.mixinStep.toString

  def resetControls(): Unit =
    saveButton.disable = true
    textFieldName.text = ""
    textFieldWeight.text = ""
    choiceBoxUnit.value = ""
    textFieldMixinMinute.text = ""
    choiceBoxMixinStep.value = ""

  // List
  val listViewYeasts = new ListView[Yeast]:
    prefHeight = 100
    items = observableYeasts
    items <== ObjectProperty(observableYeasts)
    cellFactory = (cell, yeast) => cell.text = yeast.name
    selectionModel().selectionModeProperty.value = SelectionMode.Single

  listViewYeasts.selectionModel().selectedItemProperty().addListener { (_, _, selectedYeast) =>
    if selectedYeast != null then select(selectedYeast)
  }

  val buttonAdd = new Button:
    graphic = context.imageViewPlus
    tooltip = context.tooltipAdd
    disable = false
    onAction = { _ => add() }

  val buttonRemove = new Button:
    graphic = context.imageViewMinus
    tooltip = context.tooltipRemove
    disable = true
    onAction = { _ => remove( listViewYeasts.selectionModel().selectedItem.value ) }

  val buttonBarYeasts = new HBox:
    spacing = 6
    children = List(buttonAdd, buttonRemove)

  val vboxYeasts = new VBox:
    spacing = 6
    padding = Insets(6)
    children = List(listViewYeasts, buttonBarYeasts)

  // Item
  val labelName = Label(context.labelName)
  val textFieldName = NonEmptyTextField()

  val labelWeight = Label(context.labelWeight)
  val textFieldWeight = DoubleTextField()

  val labelUnit = Label(context.labelUnit)
  val choiceBoxUnit = new ChoiceBox[String]:
  	items = ObservableBuffer.from( UoM.toList )

  val labelMixinMinute = Label(context.labelMixinMinute)
  val textFieldMixinMinute = IntTextField()

  val labelMixinStep = Label(context.labelMixinStep)
  val choiceBoxMixinStep = new ChoiceBox[String]:
  	items = ObservableBuffer.from( MixinStep.toList )

  val controls = List[(Label, Node)](
    labelName -> textFieldName,
    labelWeight -> textFieldWeight,
    labelUnit -> choiceBoxUnit,
    labelMixinMinute -> textFieldMixinMinute,
    labelMixinStep -> choiceBoxMixinStep
  )

  val saveButton = new Button:
    graphic = context.imageViewSave
    disable = true
    onAction = { _ => save() }

  val buttonBarControls = new HBox:
    spacing = 6
    children = List(saveButton)

  val vboxControls = new VBox:
    spacing = 6
    padding = Insets(6)
    children = List( ControlGrid(controls), buttonBarControls )

  // Content
  val content = new VBox:
    spacing = 6
    padding = Insets(6)
    children = List(vboxYeasts, vboxControls)

  dialogPane().content = content

  val saveButtonType = ButtonType(context.buttonSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      observableYeasts.toArray
    else null