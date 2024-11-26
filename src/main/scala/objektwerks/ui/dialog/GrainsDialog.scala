package objektwerks.ui.dialog

import scalafx.collections.ObservableBuffer

import scalafx.Includes.*
import scalafx.beans.property.ObjectProperty
import scalafx.geometry.Insets
import scalafx.scene.Node
import scalafx.scene.control.{Button, ButtonType, ChoiceBox, Dialog, Label, ListView}
import scalafx.scene.control.ButtonBar.ButtonData
import scalafx.scene.layout.{HBox, VBox}

import objektwerks.{Grain, UoM}
import objektwerks.ui.{App, Context}
import objektwerks.ui.control.{ControlGrid, DoubleTextField, IntTextField, NonEmptyTextField}
import objektwerks.MixinStep

final class GrainsDialog(context: Context, grains: Array[Grain]) extends Dialog[Array[Grain]]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogGrains

  // TODO! var updatedGrains = Array.empty[Grain]
  val selectedName = ObjectProperty[String]("")
  val selectedWeight = ObjectProperty[String]("")
  val selectedUnit = ObjectProperty[String]("")
  val selectedColor = ObjectProperty[String]("")
  val selectedLovibond = ObjectProperty[String]("")
  val selectedMixinMinute = ObjectProperty[String]("")
  val selectedMixinStep = ObjectProperty[String]("")

  val listViewGrains = new ListView[String]:
    items = ObservableBuffer.from(grains.map(_.name))
  listViewGrains.selectionModel().selectedItem.onChange { (_, _, newName) =>
    saveButton.disable = false
    selectedName.value = newName
    // TODO! Find selected grain and bind all fields.
  }

  val labelName = Label(context.labelName)
  val textFieldName = new NonEmptyTextField:
    text <== selectedName

  val labelWeight = Label(context.labelWeight)
  val textFieldWeight = new DoubleTextField:
    text <== selectedWeight

  val labelUnit = Label( context.labelUnit )
  val choiceBoxUnit = new ChoiceBox[String]:
  	items = ObservableBuffer.from( UoM.toList )
  	value <== selectedUnit

  val labelColor = Label(context.labelColor)
  val textFieldColor = new DoubleTextField:
    text <== selectedColor

  val labelLovibond = Label(context.labelLovibond)
  val textFieldLovibond = new DoubleTextField:
    text <== selectedLovibond

  val labelMixinMinute = Label(context.labelMixinMinute)
  val textFieldMixinMinute = new IntTextField:
    text <== selectedMixinMinute

  val labelMixinStep = Label( context.labelMixinStep )
  val choiceBoxMixinStep = new ChoiceBox[String]:
  	items = ObservableBuffer.from( MixinStep.toList )
  	value <== selectedMixinStep

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
    text = context.buttonSave
    disable = true
    onAction = { _ => save() }

  val save = () => () // TODO!

  val controlsButtonBar = new HBox:
    spacing = 6
    children = List(saveButton)  

  val vboxControls = new VBox:
    spacing = 6
    padding = Insets(6)
    children = List( ControlGrid(controls), controlsButtonBar )

  val hboxContent = new HBox:
    spacing = 6
    padding = Insets(6)
    children = List(listViewGrains, vboxControls)

  dialogPane().content = hboxContent

  val saveButtonType = new ButtonType(context.buttonSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      grains // TODO
    else null