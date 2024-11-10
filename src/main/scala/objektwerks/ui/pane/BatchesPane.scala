package objektwerks.ui.pane

import scalafx.Includes.*
import scalafx.geometry.Insets
import scalafx.scene.control.{SelectionMode, Tab, TabPane, TableColumn, TableView}
import scalafx.scene.layout.{Priority, VBox}

import objektwerks.Batch
import objektwerks.ui.{Context, Model}

final class BatchesPane(context: Context, model: Model) extends TabPane:
  val tableView = new TableView[Batch]():
    columns ++= List(
      new TableColumn[Batch, String]:
        text = context.columnBatch
        cellValueFactory = _.value.recipeProperty
    )
    items = model.observableBatches
  tableView.columnResizePolicy = TableView.ConstrainedResizePolicy
  tableView.selectionModel().selectionModeProperty.value = SelectionMode.Single

  val vbox = new VBox:
    spacing = 3
    padding = Insets(3)
    children = List(tableView)

  val tab = new Tab:
    text = context.tabBatches
    closable = false
    content = vbox

  tabs = List(tab)

  VBox.setVgrow(this, Priority.Always)