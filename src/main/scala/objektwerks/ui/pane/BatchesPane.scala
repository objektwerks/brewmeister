package objektwerks.ui.pane

import scalafx.scene.control.{Tab, TabPane}
import scalafx.scene.layout.{Priority, VBox}

import objektwerks.ui.Context

final class BatchesPane(context: Context) extends TabPane:
  val batchesTab = new Tab:
  	text = context.tabBatches
  	closable = false
  	content = BatchesPane(context)

  tabs = List(batchesTab)

  VBox.setVgrow(this, Priority.Always)