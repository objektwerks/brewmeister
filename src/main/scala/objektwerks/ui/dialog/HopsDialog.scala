package objektwerks.ui.dialog

import scalafx.Includes.*
import scalafx.beans.property.ObjectProperty
import scalafx.collections.ObservableBuffer
import scalafx.geometry.Insets
import scalafx.scene.Node
import scalafx.scene.control.{Button, ButtonType, ChoiceBox, Dialog, Label, ListView, SelectionMode}
import scalafx.scene.control.ButtonBar.ButtonData
import scalafx.scene.layout.{HBox, VBox}

import objektwerks.{Hop, MixinStep, UoM}
import objektwerks.ui.{App, Context}
import objektwerks.ui.control.{ControlGrid, DoubleTextField, IntTextField, NonEmptyTextField}

final class HopsDialog(context: Context, hops: Array[Hop]) extends Dialog[Array[Hop]]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogHops

  // Model
  var observableHops = ObservableBuffer.from( hops.map(identity).toBuffer.sorted )

  // Methods
  def select(hop: Hop): Unit =
    buttonRemove.disable = false
    listViewHops.selectionModel().select(hop)
    listViewHops.scrollTo(hop)
    hopToControls(hop)

  def add(hop: Hop): Unit =
    observableHops.insert(0, hop)
    observableHops = observableHops.sorted
    select(hop)

  def remove(index: Int): Unit =
    buttonRemove.disable = true
    buttonSave.disable = true
    observableHops.remove(index)
    observableHops = observableHops.sorted
    resetControls()

  def save(index: Int, hop: Hop): Unit =
    if index > -1 then observableHops.update(index, hop)
    buttonSave.disable = true

  def enableSave(): Unit = if buttonSave != null then buttonSave.disable = false

  // Bindings
  def hopToControls(hop: Hop): Unit =
    textFieldName.text = hop.name
    textFieldWeight.text = hop.weight.toString
    choiceBoxWeightUnit.value = hop.weightUnit.toString
    textFieldVolume.text = hop.volume.toString
    choiceBoxVolumeUnit.value = hop.volumeUnit.toString
    textFieldAlphaAcid.text = hop.alphaAcid.toString
    textFieldMixinMinute.text = hop.mixinMinute.toString
    choiceBoxMixinStep.value = hop.mixinStep.toString

  def controlsToHop(): Hop = 
    Hop(
      name = textFieldName.text.value,
      weight = textFieldWeight.text.value.toDouble,
      weightUnit = UoM.valueOf(choiceBoxWeightUnit.value.value),
      volume = textFieldVolume.text.value.toDouble,
      volumeUnit = UoM.valueOf(choiceBoxVolumeUnit.value.value),
      alphaAcid = textFieldAlphaAcid.text.value.toDouble,
      mixinMinute = textFieldMixinMinute.int,
      mixinStep = MixinStep.valueOf(choiceBoxMixinStep.value.value)
    )

  def resetControls(): Unit =
    textFieldName.text = "name"
    textFieldWeight.text = "0.0"
    choiceBoxWeightUnit.value = UoM.oz.toString
    textFieldVolume.text = "0.0"
    choiceBoxVolumeUnit.value = UoM.oz.toString
    textFieldAlphaAcid.text = "0.0"
    textFieldMixinMinute.text = "0"
    choiceBoxMixinStep.value = MixinStep.Boiling.toString

  // List
  val listViewHops = new ListView[Hop]:
    prefHeight = 100
    items = observableHops
    items <== ObjectProperty(observableHops)
    cellFactory = (cell, hop) => cell.text = hop.name
    selectionModel().selectionModeProperty.value = SelectionMode.Single

  listViewHops.selectionModel().selectedItemProperty().addListener { (_, _, selectedHop) =>
    if selectedHop != null then select(selectedHop)
  }

  // Buttons
  val buttonAdd = new Button:
    graphic = context.imageViewPlus
    text = context.buttonAdd
    disable = false
    onAction = { _ => add( Hop() ) }

  val buttonRemove = new Button:
    graphic = context.imageViewMinus
    text = context.buttonRemove
    disable = true
    onAction = { _ => remove( listViewHops.selectionModel().selectedIndex.value ) }

  val buttonBarHops = new HBox:
    spacing = 6
    children = List(buttonAdd, buttonRemove)

  val vboxHops = new VBox:
    spacing = 6
    padding = Insets(6)
    children = List(listViewHops, buttonBarHops)

  // Controls
  val labelName = Label(context.labelName)
  val textFieldName = new NonEmptyTextField():
    text.onChange { (_, _, _) => enableSave() }

  val labelWeight = Label(context.labelWeight)
  val textFieldWeight = new DoubleTextField():
    text.onChange { (_, _, _) => enableSave() }

  val labelWeightUnit = Label(context.labelUnit)
  val choiceBoxWeightUnit = new ChoiceBox[String]:
  	items = ObservableBuffer.from( UoM.toList )
  choiceBoxWeightUnit.value.onChange { (_, _, _) => enableSave() }

  val labelVolume = Label(context.labelVolume)
  val textFieldVolume = new DoubleTextField():
    text.onChange { (_, _, _) => enableSave() }

  val labelVolumeUnit = Label(context.labelUnit)
  val choiceBoxVolumeUnit = new ChoiceBox[String]:
  	items = ObservableBuffer.from( UoM.toList )

  val labelAlphaAcid = Label(context.labelAlphaAcid)
  val textFieldAlphaAcid = new DoubleTextField():
    text.onChange { (_, _, _) => enableSave() }

  val labelMixinMinute = Label(context.labelMixinMinute)
  val textFieldMixinMinute = new IntTextField():
    text.onChange { (_, _, _) => enableSave() }

  val labelMixinStep = Label(context.labelMixinStep)
  val choiceBoxMixinStep = new ChoiceBox[String]:
  	items = ObservableBuffer.from( MixinStep.toList )

  val controls = List[(Label, Node)](
    labelName -> textFieldName,
    labelWeight -> textFieldWeight,
    labelWeightUnit -> choiceBoxWeightUnit,
    labelVolume -> textFieldVolume,
    labelVolumeUnit -> choiceBoxVolumeUnit,
    labelAlphaAcid -> textFieldAlphaAcid,
    labelMixinMinute -> textFieldMixinMinute,
    labelMixinStep -> choiceBoxMixinStep
  )

  // Buttons
  val buttonSave = new Button:
    graphic = context.imageViewSave
    text = context.buttonSave
    disable = true
    onAction = { _ => save( listViewHops.selectionModel().selectedIndex.value, controlsToHop() ) }

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
    children = List(vboxHops, vboxControls)

  dialogPane().content = content

  val buttonTypeSave = ButtonType(context.buttonSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(buttonTypeSave, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == buttonTypeSave then
      observableHops.toArray
    else null