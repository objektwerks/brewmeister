package objektwerks.ui.dialog

import scalafx.Includes.*
import scalafx.beans.property.ObjectProperty
import scalafx.collections.ObservableBuffer
import scalafx.geometry.Insets
import scalafx.scene.Node
import scalafx.scene.control.{Button, ButtonType, ChoiceBox, Dialog, Label, ListView, SelectionMode}
import scalafx.scene.control.ButtonBar.ButtonData
import scalafx.scene.layout.{HBox, VBox}

import objektwerks.{Adjunct, MixinStep, UoM}
import objektwerks.ui.{App, Context}
import objektwerks.ui.control.{ControlGrid, DoubleTextField, IntTextField, NonEmptyTextField}

final class AdjunctsDialog(context: Context, adjuncts: Array[Adjunct]) extends Dialog[Array[Adjunct]]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogAdjuncts

  // Model
  var observableAdjuncts = ObservableBuffer.from( adjuncts.map(identity).toBuffer.sorted )

  // Methods
  def select(adjunct: Adjunct): Unit =
    buttonRemove.disable = false
    listViewAdjuncts.selectionModel().select(adjunct)
    listViewAdjuncts.scrollTo(adjunct)
    adjunctToControls(adjunct)

  def add(adjunct: Adjunct): Unit =
    observableAdjuncts.insert(0, adjunct)
    observableAdjuncts = observableAdjuncts.sorted
    select(adjunct)

  def remove(index: Int): Unit =
    buttonRemove.disable = true
    buttonSave.disable = true
    observableAdjuncts.remove(index)
    observableAdjuncts = observableAdjuncts.sorted
    resetControls()

  def save(index: Int, adjunct: Adjunct): Unit =
    if index > -1 then
      observableAdjuncts.update(index, adjunct)
      buttonSave.disable = true

  def enableSave(): Unit = if buttonSave != null then buttonSave.disable = false

  // Bindings
  def adjunctToControls(adjunct: Adjunct): Unit =
    textFieldName.text = adjunct.name
    textFieldWeight.text = adjunct.weight.toString
    choiceBoxUnit.value = adjunct.unit.toString
    textFieldMixinMinute.text = adjunct.mixinMinute.toString
    choiceBoxMixinStep.value = adjunct.mixinStep.toString

  def controlsToAdjunct(): Adjunct = 
    Adjunct(
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
  val listViewAdjuncts = new ListView[Adjunct]:
    prefHeight = 100
    items = observableAdjuncts
    items <== ObjectProperty(observableAdjuncts)
    cellFactory = (cell, adjunct) => cell.text = adjunct.name
    selectionModel().selectionModeProperty.value = SelectionMode.Single

  listViewAdjuncts.selectionModel().selectedItemProperty().addListener { (_, _, selectedAdjunct) =>
    if selectedAdjunct != null then select(selectedAdjunct)
  }

  // Buttons
  val buttonAdd = new Button:
    graphic = context.imageViewPlus
    text = context.buttonAdd
    disable = false
    onAction = { _ => add( Adjunct() ) }

  val buttonRemove = new Button:
    graphic = context.imageViewMinus
    text = context.buttonRemove
    disable = true
    onAction = { _ => remove( listViewAdjuncts.selectionModel().selectedIndex.value ) }

  val buttonBarAdjuncts = new HBox:
    spacing = 6
    children = List(buttonAdd, buttonRemove)

  val vboxAdjuncts = new VBox:
    spacing = 6
    padding = Insets(6)
    children = List(listViewAdjuncts, buttonBarAdjuncts)

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
    text = context.buttonSave
    disable = true
    onAction = { _ => save( listViewAdjuncts.selectionModel().selectedIndex.value, controlsToAdjunct() ) }

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
    children = List(vboxAdjuncts, vboxControls)

  dialogPane().content = content

  val buttonTypeSave = ButtonType(context.buttonSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(buttonTypeSave, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == buttonTypeSave then
      observableAdjuncts.toArray
    else null