package objektwerks.ui.pane

import scalafx.Includes.*
// import scalafx.beans.property.ObjectProperty
import scalafx.geometry.Insets
import scalafx.scene.control.{Button, ButtonType, Label, SelectionMode, Tab, TabPane, TableColumn, TableView}
import scalafx.scene.layout.{HBox, Priority, VBox}
import scalafx.scene.text.TextAlignment

import objektwerks.Batch
import objektwerks.ui.{Context, Model}
import objektwerks.ui.dialog.RemoveConfirmationDialog

final class BatchesPane(context: Context, model: Model) extends TabPane:
  model.observableBatches.onChange { (_, _) =>
    labelBrewhouseEfficiency.text = calculateBrewhouseEfficiency()
  }
  
  val tableView = new TableView[Batch]():
    columns ++= List(
      new TableColumn[Batch, String]:
        text = context.columnBatch
        cellValueFactory = _.value.nameProperty,
      new TableColumn[Batch, Int]:
        text = context.columnBrewhouseEfficiency
        cellValueFactory = _.value.brewhouseEfficiencyProoperty
    )
    items = model.observableBatches
    // items <== ObjectProperty(model.observableBatches)
    columnResizePolicy = TableView.ConstrainedResizePolicy
    selectionModel().selectionModeProperty.value = SelectionMode.Single

  tableView.selectionModel().selectedItemProperty().addListener { (_, _, selectedBatch) =>
    if selectedBatch != null then
      model.selectedBatch.value = selectedBatch
      buttonRemove.disable = false
  }

  val buttonRemove = new Button:
    graphic = context.imageViewMinus
    tooltip = context.tooltipRemove
    disable = true
    onAction = { _ => remove() }

  val labelBrewhouseEfficiency = new Label:
    style = "-fx-font: normal bold 13pt sans-serif"
    textAlignment = TextAlignment.Justify
    text = calculateBrewhouseEfficiency()

  val buttonBar = new HBox:
    spacing = 15
    padding = Insets(3)
    children = List(buttonRemove, labelBrewhouseEfficiency)

  val vbox = new VBox:
    children = List(tableView, buttonBar)

  val tab = new Tab:
    text = context.tabBatches
    closable = false
    content = vbox

  tabs = List(tab)

  VBox.setVgrow(this, Priority.Always)

  def remove(): Unit =
    RemoveConfirmationDialog(context).showAndWait() match
      case Some(ButtonType.OK) =>
        val index = tableView.selectionModel().selectedIndex.value - 1
        val batch = tableView.selectionModel().selectedItemProperty().value
        if model.remove(batch) && index > -1 then
          tableView.items.value.remove(index)
          tableView.items.value.sort()
          buttonRemove.disable = true
      case _ =>

  def calculateBrewhouseEfficiency(): String =
    s"${context.labelBrewhouseEfficiency} ${Batch.brewhouseEfficiency(model.observableBatches.toList)}%"