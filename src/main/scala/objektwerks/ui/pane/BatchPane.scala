package objektwerks.ui.pane

import scalafx.scene.control.{Tab, TabPane}
import scalafx.scene.layout.{Priority, VBox}

import objektwerks.ui.Context

final class BatchPane(context: Context) extends TabPane:
  val batchTab = new Tab:
  	text = "Batch"
  	closable = false
  	content = BatchesPane(context)

  tabs = List(batchTab)

  VBox.setVgrow(this, Priority.Always)