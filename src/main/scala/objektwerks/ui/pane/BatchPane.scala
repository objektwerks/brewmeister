package objektwerks.ui.pane

import scalafx.scene.control.Tab

import objektwerks.ui.{Context, Model}

final class BatchPane(context: Context, model: Model) extends Tab:
  text = context.tabBatch
  closable = false

  model.selectedBatch.onChange { (_, _, newBatch) =>

  }