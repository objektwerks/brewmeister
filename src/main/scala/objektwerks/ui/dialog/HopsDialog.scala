package objektwerks.ui.dialog

import scalafx.Includes.*
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
  val updatedHops = hops.map(identity).toBuffer.sorted

  // Bindings
  def hopToControls(hop: Hop): Unit =
    textFieldName.text = hop.name
    textFieldWeight.text = hop.weight.toString
    textFieldVolume.text = hop.volume.toString
    choiceBoxVolumeUnit.value = hop.volumeUnit.toString
    textFieldAlphaAcid.text = hop.alphaAcid.toString
    textFieldMixinMinute.text = hop.mixinMinute.toString
    choiceBoxMixinStep.value = hop.mixinStep.toString

  def resetControls(): Unit =
    saveButton.disable = true
    textFieldName.text = ""
    textFieldWeight.text = ""
    textFieldVolume.text = ""
    choiceBoxVolumeUnit.value = ""
    textFieldAlphaAcid.text = ""
    textFieldMixinMinute.text = ""
    choiceBoxMixinStep.value = ""

  // Methods
  def select(hop: Hop): Unit =
    saveButton.disable = false
    listViewHops.selectionModel().select(hop)
    hopToControls(hop)
    listViewHops.scrollTo(hop)

  def add(): Unit =
    val hop = Hop()
    updatedHops += hop // listview items refresh?
    select(hop)

  def remove(hop: Hop): Unit =
    updatedHops -= hop // listview items refresh?
    resetControls()
    saveButton.disable = true
    if !listViewHops.selectionModel().isEmpty() then
      listViewHops.selectionModel().select(0)
      select( listViewHops.selectionModel().selectedItem() )

  def save(): Unit =
    val index = listViewHops.selectionModel().selectedIndex.value
    val hop = listViewHops.selectionModel().selectedItem.value
    updatedHops.update(index, hop) // listview items refresh?

  // List
  val listViewHops = new ListView[Hop]:
    prefHeight = 100
    items = ObservableBuffer.from(updatedHops)
    cellFactory = (cell, hop) => cell.text = hop.name

  listViewHops.selectionModel().selectionModeProperty.value = SelectionMode.Single
  listViewHops.selectionModel().selectedItemProperty().addListener { (_, _, selectedHop) =>
    if selectedHop != null then select(selectedHop)
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
    onAction = { _ => remove( listViewHops.selectionModel().selectedItem.value ) }

  val buttonBarHops = new HBox:
    spacing = 6
    children = List(buttonAdd, buttonRemove)

  val vboxHops = new VBox:
    spacing = 6
    padding = Insets(6)
    children = List(listViewHops, buttonBarHops)

  // Item
  val labelName = Label(context.labelName)
  val textFieldName = NonEmptyTextField()

  val labelWeight = Label(context.labelWeight)
  val textFieldWeight = DoubleTextField()

  val labelWeightUnit = Label(context.labelUnit)
  val choiceBoxWeightUnit = new ChoiceBox[String]:
  	items = ObservableBuffer.from( UoM.toList )

  val labelVolume = Label(context.labelVolume)
  val textFieldVolume = DoubleTextField()

  val labelVolumeUnit = Label(context.labelUnit)
  val choiceBoxVolumeUnit = new ChoiceBox[String]:
  	items = ObservableBuffer.from( UoM.toList )

  val labelAlphaAcid = Label(context.labelAlphaAcid)
  val textFieldAlphaAcid = DoubleTextField()

  val labelMixinMinute = Label(context.labelMixinMinute)
  val textFieldMixinMinute = IntTextField()

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
    children = List(vboxHops, vboxControls)

  dialogPane().content = content

  val saveButtonType = new ButtonType(context.tooltipSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      updatedHops.toArray
    else null