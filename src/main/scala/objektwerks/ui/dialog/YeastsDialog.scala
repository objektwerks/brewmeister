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
    buttonSave.disable = false
    listViewYeasts.selectionModel().select(yeast)
    listViewYeasts.scrollTo(yeast)
    yeastToControls(yeast)

  def add(yeast: Yeast): Unit =
    observableYeasts.insert(0, yeast)
    observableYeasts = observableYeasts.sorted
    select(yeast)

  def remove(index: Int): Unit =
    buttonRemove.disable = true
    buttonSave.disable = true
    observableYeasts.remove(index)
    observableYeasts = observableYeasts.sorted
    resetControls()

  def save(index: Int, yeast: Yeast): Unit =
    observableYeasts.update(index, yeast)

  // Bindings
  def yeastToControls(yeast: Yeast): Unit =
    textFieldName.text = yeast.name
    textFieldWeight.text = yeast.weight.toString
    choiceBoxUnit.value = yeast.unit.toString
    textFieldMixinMinute.text = yeast.mixinMinute.toString
    choiceBoxMixinStep.value = yeast.mixinStep.toString

  def controlsToYeast(): Yeast =
    Yeast(
      name = textFieldName.text.value,
      weight = textFieldWeight.text.value.toDouble,
      unit = UoM.valueOf(choiceBoxUnit.value.value),
      mixinMinute = textFieldMixinMinute.int,
      mixinStep = MixinStep.valueOf(choiceBoxMixinStep.value.value)
    )

  def resetControls(): Unit =
    textFieldName.text = "name"
    textFieldWeight.text = "0.0"
    textFieldMixinMinute.text = "0"

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

  // Buttons
  val buttonAdd = new Button:
    graphic = context.imageViewPlus
    tooltip = context.tooltipAdd
    disable = false
    onAction = { _ => add( Yeast() ) }

  val buttonRemove = new Button:
    graphic = context.imageViewMinus
    tooltip = context.tooltipRemove
    disable = true
    onAction = { _ => remove( listViewYeasts.selectionModel().selectedIndex.value ) }

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

  // Buttons
  val buttonSave = new Button:
    graphic = context.imageViewSave
    disable = true
    onAction = { _ => save() }

  val buttonBarControls = new HBox:
    spacing = 6
    children = List(buttonSave)

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

  val buttonTypeSave = ButtonType(context.buttonSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(buttonTypeSave, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == buttonTypeSave then
      observableYeasts.toArray
    else null