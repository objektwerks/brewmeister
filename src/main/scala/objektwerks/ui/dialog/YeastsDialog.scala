package objektwerks.ui.dialog

import scalafx.Includes.*
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
  val updatedYeasts = yeasts.map(identity).toBuffer.sorted

  // Bindings
  def yeastToControls(adjunct: Yeast): Unit =
    textFieldName.text = adjunct.name
    textFieldWeight.text = adjunct.weight.toString
    choiceBoxUnit.value = adjunct.unit.toString
    textFieldMixinMinute.text = adjunct.mixinMinute.toString
    choiceBoxMixinStep.value = adjunct.mixinStep.toString

  def resetControls(): Unit =
    saveButton.disable = true
    textFieldName.text = ""
    textFieldWeight.text = ""
    choiceBoxUnit.value = ""
    textFieldMixinMinute.text = ""
    choiceBoxMixinStep.value = ""

  // Methods
  def select(adjunct: Yeast): Unit =
    saveButton.disable = false
    listViewYeasts.selectionModel().select(adjunct)
    yeastToControls(adjunct)
    listViewYeasts.scrollTo(adjunct)

  def add(): Unit =
    val yeast = Yeast()
    updatedYeasts += yeast // listview items refresh?
    select(yeast)

  def remove(yeast: Yeast): Unit =
    updatedYeasts -= yeast // listview items refresh?
    resetControls()
    saveButton.disable = true
    if !listViewYeasts.selectionModel().isEmpty() then
      listViewYeasts.selectionModel().select(0)
      select( listViewYeasts.selectionModel().selectedItem() )

  def save(): Unit =
    val index = listViewYeasts.selectionModel().selectedIndex.value
    val yeast = listViewYeasts.selectionModel().selectedItem.value
    updatedYeasts.update(index, yeast) // listview items refresh?

  // List
  val listViewYeasts = new ListView[Yeast]:
    prefHeight = 100
    items = ObservableBuffer.from(updatedYeasts)
    cellFactory = (cell, yeast) => cell.text = yeast.name

  listViewYeasts.selectionModel().selectionModeProperty.value = SelectionMode.Single
  listViewYeasts.selectionModel().selectedItemProperty().addListener { (_, _, selectedYeast) =>
    if selectedYeast != null then select(selectedYeast)
  }

  val buttonAdd = new Button:
    graphic = context.imageViewPlus
    tooltip = context.tooltipAdd
    disable = true
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

  val saveButtonType = new ButtonType(context.tooltipSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      updatedYeasts.toArray
    else null