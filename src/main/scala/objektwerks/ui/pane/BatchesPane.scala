package objektwerks.ui.pane

import scalafx.scene.control.{Tab, TabPane}
import scalafx.scene.layout.{Priority, VBox}

import objektwerks.ui.{Context, Model}

final class BatchesPane(context: Context, model: Model) extends TabPane:
  val tab = new Tab:
  	text = context.tabBatches
  	closable = false

  tabs = List(tab)

  VBox.setVgrow(this, Priority.Always)