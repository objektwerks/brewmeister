package objektwerks.ui.dialog

import scalafx.collections.ObservableBuffer

import scalafx.Includes.*
import scalafx.geometry.Insets
import scalafx.scene.Node
import scalafx.scene.control.{Button, ButtonType, ChoiceBox, Dialog, Label, ListView, SelectionMode}
import scalafx.scene.control.ButtonBar.ButtonData
import scalafx.scene.layout.{HBox, VBox}

import objektwerks.{format, Grain, MixinStep, UoM}
import objektwerks.ui.{App, Context}
import objektwerks.ui.control.{ControlGrid, DoubleTextField, IntTextField, NonEmptyTextField}

final class GrainsDialog(context: Context, grains: Array[Grain]) extends Dialog[Array[Grain]]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogGrains

  // Model
  val updatedGrains = grains.map(identity).toBuffer.sorted

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
      weight = textFieldWeight.text.value.toDouble.format,
      unit = UoM.valueOf( choiceBoxUnit.value.value ),
      color = textFieldColor.text.value.toDouble.format,
      lovibond = textFieldLovibond.text.value.toDouble.format,
      mixinMinute = textFieldMixinMinute.text.value.toInt,
      mixinStep = MixinStep.valueOf( choiceBoxMixinStep.value.value )
    )

  def resetControls(): Unit =
    saveButton.disable = true
    textFieldName.text = ""
    textFieldWeight.text = ""
    choiceBoxUnit.value = ""
    textFieldColor.text = ""
    textFieldLovibond.text = ""
    textFieldMixinMinute.text = ""
    choiceBoxMixinStep.value = ""

  // Methods
  def add(): Unit =
    val grain = Grain()
    updatedGrains += grain // listview items refresh?
    listViewGrains.selectionModel().select(grain)
    grainToControls(grain)

  def remove(grain: Grain): Unit =
    updatedGrains -= grain // listview items refresh?
    resetControls()
    if !listViewGrains.selectionModel().isEmpty then listViewGrains.selectionModel().select(0)

  def save(): Unit =
    val index = listViewGrains.selectionModel().selectedIndex.value
    val grain = listViewGrains.selectionModel().selectedItem.value
    updatedGrains.update(index, grain) // listview items refresh?

  val addButton = new Button:
    graphic = context.addImageView
    disable = true
    onAction = { _ => add() }

  val removeButton = new Button:
    graphic = context.removeImageView
    disable = true
    onAction = { _ => remove( listViewGrains.selectionModel().selectedItem.value ) }

  val buttonBarGrains = new HBox:
    spacing = 6
    children = List(addButton, removeButton)

  val vboxGrains = new VBox:
    spacing = 6
    padding = Insets(6)
    children = List( listViewGrains, buttonBarGrains )

  // List
  val listViewGrains = new ListView[Grain]:
    items = ObservableBuffer.from(updatedGrains)
    cellFactory = (cell, grain) => cell.text = grain.name

  listViewGrains.selectionModel().selectionModeProperty.value = SelectionMode.Single
  listViewGrains.selectionModel().selectedItemProperty().addListener { (_, _, selectedGrain) =>
    if selectedGrain != null then
      saveButton.disable = false
      grainToControls(selectedGrain)
  }

  // Item
  val labelName = Label(context.labelName)
  val textFieldName = NonEmptyTextField()

  val labelWeight = Label(context.labelWeight)
  val textFieldWeight = DoubleTextField()

  val labelUnit = Label( context.labelUnit )
  val choiceBoxUnit = new ChoiceBox[String]:
  	items = ObservableBuffer.from( UoM.toList )

  val labelColor = Label(context.labelColor)
  val textFieldColor = DoubleTextField()

  val labelLovibond = Label(context.labelLovibond)
  val textFieldLovibond = DoubleTextField()

  val labelMixinMinute = Label(context.labelMixinMinute)
  val textFieldMixinMinute = IntTextField()

  val labelMixinStep = Label( context.labelMixinStep )
  val choiceBoxMixinStep = new ChoiceBox[String]:
  	items = ObservableBuffer.from( MixinStep.toList )

  val controls = List[(Label, Node)](
    labelName -> textFieldName,
    labelWeight -> textFieldWeight,
    labelUnit -> choiceBoxUnit,
    labelColor -> textFieldColor,
    labelLovibond -> textFieldLovibond,
    labelMixinMinute -> textFieldMixinMinute,
    labelMixinStep -> choiceBoxMixinStep
  )

  val saveButton = new Button:
    graphic = context.saveImageView
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
  val hboxContent = new HBox:
    spacing = 6
    padding = Insets(6)
    children = List(vboxGrains, vboxControls)

  dialogPane().content = hboxContent

  val saveButtonType = new ButtonType(context.buttonSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      updatedGrains.toArray
    else null