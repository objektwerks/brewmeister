package objektwerks.ui.dialog

import scalafx.collections.ObservableBuffer

import scalafx.Includes.*
import scalafx.beans.property.ObjectProperty
import scalafx.geometry.Insets
import scalafx.scene.Node
import scalafx.scene.control.{Button, ButtonType, ChoiceBox, Dialog, Label, ListView, SelectionMode}
import scalafx.scene.layout.{HBox, VBox}

import objektwerks.{Grain, MixinStep, UoM}
import objektwerks.ui.{App, Context}
import objektwerks.ui.control.{ControlGrid, DoubleTextField, IntTextField, NonEmptyTextField}

final class GrainsDialog(context: Context, grains: Array[Grain]) extends Dialog[Array[Grain]]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogGrains

  // Model
  var observableGrains = ObservableBuffer.from( grains.map(identity).toBuffer.sorted )

  // Methods
  def select(grain: Grain): Unit =
    buttonRemove.disable = false
    listViewGrains.selectionModel().select(grain)
    listViewGrains.scrollTo(grain)
    grainToControls(grain)

  def add(grain: Grain): Unit =
    observableGrains.insert(0, grain)
    observableGrains = observableGrains.sorted
    select(grain)

  def remove(index: Int): Unit =
    buttonRemove.disable = true
    buttonSave.disable = true
    observableGrains.remove(index)
    observableGrains = observableGrains.sorted
    resetControls()

  def save(index: Int, grain: Grain): Unit =
    observableGrains.update(index, grain)
    buttonSave.disable = true

  def enableSave(): Unit = if buttonSave != null then buttonSave.disable = false

  // Bindings
  def grainToControls(grain: Grain): Unit =
    textFieldName.text = grain.name
    textFieldWeight.text = grain.weight.toString
    choiceBoxUnit.value = grain.unit.toString
    textFieldColor.text = grain.color.toString
    textFieldLovibond.text = grain.lovibond.toString
    textFieldMixinMinute.text = grain.mixinMinute.toString
    choiceBoxMixinStep.value = grain.mixinStep.toString

  def controlsToGrain(): Grain =
    Grain(
      name = textFieldName.text.value,
      weight = textFieldWeight.text.value.toDouble,
      unit = UoM.valueOf(choiceBoxUnit.value.value),
      color = textFieldColor.text.value.toDouble,
      lovibond = textFieldLovibond.text.value.toDouble,
      mixinMinute = textFieldMixinMinute.int,
      mixinStep = MixinStep.valueOf(choiceBoxMixinStep.value.value)
    )

  def resetControls(): Unit =
    textFieldName.text = "name"
    textFieldWeight.text = "0.0"
    choiceBoxUnit.value = UoM.oz.toString
    textFieldColor.text = "0.0"
    textFieldLovibond.text = "0.0"
    textFieldMixinMinute.text = "0"
    choiceBoxMixinStep.value = MixinStep.Mashing.toString

  // List
  val listViewGrains = new ListView[Grain]:
    prefHeight = 100
    items = observableGrains
    items <== ObjectProperty(observableGrains)
    cellFactory = (cell, grain) => cell.text = grain.name
    selectionModel().selectionModeProperty.value = SelectionMode.Single

  listViewGrains.selectionModel().selectedItemProperty().addListener { (_, _, selectedGrain) =>
    if selectedGrain != null then select(selectedGrain)
  }

  // Buttons
  val buttonAdd = new Button:
    graphic = context.imageViewPlus
    text = context.buttonAdd
    disable = false
    onAction = { _ => add( Grain() ) }

  val buttonRemove = new Button:
    graphic = context.imageViewMinus
    text = context.buttonRemove
    disable = true
    onAction = { _ => remove( listViewGrains.selectionModel().selectedIndex.value ) }

  val buttonBarGrains = new HBox:
    spacing = 6
    children = List(buttonAdd, buttonRemove)

  val vboxGrains = new VBox:
    spacing = 6
    padding = Insets(6)
    children = List(listViewGrains, buttonBarGrains)

  // Controls
  val labelName = Label(context.labelName)
  val textFieldName = new NonEmptyTextField():
    text.onChange { (_, _, _) => enableSave() }

  val labelWeight = Label(context.labelWeight)
  val textFieldWeight = new DoubleTextField():
    text.onChange { (_, _, _) => enableSave() }

  val labelUnit = Label(context.labelUnit)
  val choiceBoxUnit = new ChoiceBox[String]:
  	items = ObservableBuffer.from( UoM.toList )
  choiceBoxUnit.value.onChange { (_, _, _) => enableSave() }

  val labelColor = Label(context.labelColor)
  val textFieldColor = new DoubleTextField():
    text.onChange { (_, _, _) => enableSave() }

  val labelLovibond = Label(context.labelLovibond)
  val textFieldLovibond = new DoubleTextField():
    text.onChange { (_, _, _) => enableSave() }

  val labelMixinMinute = Label(context.labelMixinMinute)
  val textFieldMixinMinute = new IntTextField():
    text.onChange { (_, _, _) => enableSave() }

  val labelMixinStep = Label(context.labelMixinStep)
  val choiceBoxMixinStep = new ChoiceBox[String]:
  	items = ObservableBuffer.from( MixinStep.toList )
  choiceBoxMixinStep.value.onChange { (_, _, _) => enableSave() }

  val controls = List[(Label, Node)](
    labelName -> textFieldName,
    labelWeight -> textFieldWeight,
    labelUnit -> choiceBoxUnit,
    labelColor -> textFieldColor,
    labelLovibond -> textFieldLovibond,
    labelMixinMinute -> textFieldMixinMinute,
    labelMixinStep -> choiceBoxMixinStep
  )

  resetControls()

  // Buttons
  val buttonSave = new Button:
    graphic = context.imageViewSave
    text = context.buttonSave
    disable = true
    onAction = { _ => save( listViewGrains.selectionModel().selectedIndex.value, controlsToGrain() ) }

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
    children = List(vboxGrains, vboxControls)

  dialogPane().content = content

  dialogPane().buttonTypes = List(ButtonType.OK)

  resultConverter = dialogButton =>
    if dialogButton == ButtonType.OK then
      observableGrains.toArray
    else null