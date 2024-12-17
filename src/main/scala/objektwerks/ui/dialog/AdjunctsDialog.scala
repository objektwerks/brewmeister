package objektwerks.ui.dialog

import scalafx.Includes.*
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
  val updatedAdjuncts = adjuncts.map(identity).toBuffer.sorted

  // Bindings
  def hopToControls(adjunct: Adjunct): Unit =
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
  def select(adjunct: Adjunct): Unit =
    saveButton.disable = false
    listViewAdjuncts.selectionModel().select(adjunct)
    adjunctToControls(adjunct)
    listViewAdjuncts.scrollTo(adjunct)

  // List
  val listViewAdjuncts = new ListView[Adjunct]:
    prefHeight = 100
    items = ObservableBuffer.from(updatedAdjuncts)
    cellFactory = (cell, hop) => cell.text = hop.name

  listViewAdjuncts.selectionModel().selectionModeProperty.value = SelectionMode.Single
  listViewAdjuncts.selectionModel().selectedItemProperty().addListener { (_, _, selectedAdjunct) =>
    if selectedAdjunct != null then select(selectedAdjunct)
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
    onAction = { _ => remove( listViewAdjuncts.selectionModel().selectedItem.value ) }

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
    children = List(vboxAdjuncts, vboxControls)

  dialogPane().content = content

  val saveButtonType = new ButtonType(context.tooltipSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      updatedAdjuncts.toArray
    else null