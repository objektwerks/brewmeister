package objektwerks.ui.dialog

import scalafx.Includes.*
import scalafx.scene.control.{ButtonType, Dialog}
import scalafx.scene.control.ButtonBar.ButtonData

import objektwerks.Yeast
import objektwerks.ui.{App, Context}

final class YeastsDialog(context: Context, yeasts: Array[Yeast]) extends Dialog[Array[Yeast]]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogYeasts

  // Model
  val updatedYeasts = yeasts.map(identity).toBuffer.sorted

  // Bindings
  def adjunctToControls(adjunct: Yeast): Unit =
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
    adjunctToControls(adjunct)
    listViewYeasts.scrollTo(adjunct)

  def add(): Unit =
    val adjunct = Yeast()
    updatedYeasts += adjunct // listview items refresh?
    select(adjunct)

  def remove(adjunct: Yeast): Unit =
    updatedYeasts -= adjunct // listview items refresh?
    resetControls()
    saveButton.disable = true
    if !listViewYeasts.selectionModel().isEmpty() then
      listViewYeasts.selectionModel().select(0)
      select( listViewYeasts.selectionModel().selectedItem() )

  def save(): Unit =
    val index = listViewYeasts.selectionModel().selectedIndex.value
    val adjunct = listViewYeasts.selectionModel().selectedItem.value
    updatedYeasts.update(index, adjunct) // listview items refresh?

  val saveButtonType = new ButtonType(context.tooltipSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      yeasts // TODO
    else null