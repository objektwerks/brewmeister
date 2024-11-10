package objektwerks.ui.pane

import scalafx.scene.control.{Tab, TabPane, TableColumn, TableView}
import scalafx.scene.layout.{Priority, VBox}

import objektwerks.Batch
import objektwerks.ui.{Context, Model}

final class BatchesPane(context: Context, model: Model) extends TabPane:
  val tableView = new TableView[Batch]():
    columns ++= List(
      new TableColumn[Batch, String]:
        text = context.tabBatch
        cellValueFactory = _.value.recipeProperty
    )
    items = model.observableBatches

  val tab = new Tab:
  	text = context.tabBatches
  	closable = false

  tabs = List(tab)

  VBox.setVgrow(this, Priority.Always)