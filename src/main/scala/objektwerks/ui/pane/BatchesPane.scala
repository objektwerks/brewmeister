package objektwerks.ui.pane

import scalafx.Includes.*
import scalafx.scene.control.{SelectionMode, Tab, TabPane, TableColumn, TableView}
import scalafx.scene.layout.{Priority, VBox}

import objektwerks.Batch
import objektwerks.ui.{Context, Model}

final class BatchesPane(context: Context, model: Model) extends TabPane:
  val tableView = new TableView[Batch]():
    columns ++= List(
      new TableColumn[Batch, String]:
        text = context.columnBatch
        cellValueFactory = _.value.nameProperty
    )
    items = model.observableBatches.sorted
  tableView.columnResizePolicy = TableView.ConstrainedResizePolicy
  tableView.selectionModel().selectionModeProperty.value = SelectionMode.Single
  tableView.selectionModel().selectedItemProperty().addListener { (_, _, selectedItem) =>
    if selectedItem != null then
      model.selectedBatch.value = selectedItem
  }

  val vbox = new VBox:
    children = List(tableView)

  val tab = new Tab:
    text = context.tabBatches
    closable = false
    content = vbox

  tabs = List(tab)

  VBox.setVgrow(this, Priority.Always)