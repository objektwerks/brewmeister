package objektwerks.ui.pane

import scalafx.scene.control.{Tab, TabPane}
import scalafx.scene.layout.{Priority, VBox}

import objektwerks.ui.{Context, Model}

final class BatchesPane(context: Context, model: Model) extends TabPane:
  private val batchesTab = new Tab:
  	text = context.tabBatches
  	closable = false

  tabs = List(batchesTab)

  VBox.setVgrow(this, Priority.Always)