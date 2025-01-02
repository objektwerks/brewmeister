package objektwerks.ui.pane

import scalafx.Includes.*
import scalafx.beans.property.ObjectProperty
import scalafx.geometry.Insets
import scalafx.scene.control.{Button, ButtonType, SelectionMode, Tab, TabPane, TableColumn, TableView}
import scalafx.scene.layout.{HBox, Priority, VBox}

import objektwerks.Batch
import objektwerks.ui.{Context, Model}
import objektwerks.ui.dialog.RemoveConfirmationDialog

final class BatchesPane(context: Context, model: Model) extends TabPane:
  val tableView = new TableView[Batch]():
    columns ++= List(
      new TableColumn[Batch, String]:
        text = context.columnBatch
        cellValueFactory = _.value.nameProperty
    )
    items = model.observableBatches
    items <== ObjectProperty(model.observableBatches)
    columnResizePolicy = TableView.ConstrainedResizePolicy
    selectionModel().selectionModeProperty.value = SelectionMode.Single

  tableView.selectionModel().selectedItemProperty().addListener { (_, _, selectedBatch) =>
    if selectedBatch != null then
      model.selectedBatchIndex.value = tableView.selectionModel().selectedIndex.value
      model.selectedBatch.value = selectedBatch
      buttonRemove.disable = false
  }

  val buttonRemove = new Button:
    graphic = context.imageViewMinus
    tooltip = context.tooltipRemove
    disable = true
    onAction = { _ => remove() }

  val buttonBar = new HBox:
    spacing = 6
    padding = Insets(3)
    children = List(buttonRemove)

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
        model.remove(model.selectedBatch.value)
        buttonRemove.disable = true
      case _ =>