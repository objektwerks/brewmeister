package objektwerks.ui.pane

import scalafx.scene.control.{Tab, TabPane}
import scalafx.scene.layout.{Priority, VBox}

import objektwerks.Batch
import objektwerks.ui.Context

final class BatchPane(context: Context, batch: Batch) extends TabPane:
  val batchTab = new Tab:
  	text = context.tabBatch
  	closable = false

  tabs = List(batchTab)

  VBox.setVgrow(this, Priority.Always)