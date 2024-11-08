package objektwerks.ui.pane

import scalafx.scene.control.{Tab, TabPane}
import scalafx.scene.layout.{Priority, VBox}

import objektwerks.ui.{Context, Model}

final class BatchPane(context: Context, model: Model) extends TabPane:
  val batchTab = new Tab:
  	text = context.tabBatch
  	closable = false

  tabs = List(batchTab)

  VBox.setVgrow(this, Priority.Always)