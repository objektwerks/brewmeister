package objektwerks.ui.dialog

import scalafx.Includes.*
import scalafx.collections.ObservableBuffer
import scalafx.geometry.Insets
import scalafx.scene.control.{Button, ButtonType, Dialog, ListView, SelectionMode}
import scalafx.scene.control.ButtonBar.ButtonData
import scalafx.scene.layout.{HBox, VBox}

import objektwerks.Adjunct
import objektwerks.ui.{App, Context}

final class AdjunctsDialog(context: Context, adjuncts: Array[Adjunct]) extends Dialog[Array[Adjunct]]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogAdjuncts

  // Model
  val updatedAdjuncts = adjuncts.map(identity).toBuffer.sorted

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

  val buttonBarHops = new HBox:
    spacing = 6
    children = List(buttonAdd, buttonRemove)

  val vboxHops = new VBox:
    spacing = 6
    padding = Insets(6)
    children = List(listViewAdjuncts, buttonBarHops)

  val saveButton = new Button:
    graphic = context.imageViewSave
    disable = true
    onAction = { _ => save() }

  val buttonBarControls = new HBox:
    spacing = 6
    children = List(saveButton)

  val saveButtonType = new ButtonType(context.tooltipSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      adjuncts // TODO
    else null