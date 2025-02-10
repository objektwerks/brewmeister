package objektwerks.ui.pane

import scalafx.geometry.Insets
import scalafx.scene.layout.VBox

import objektwerks.ui.{Context, Model}

final class LogPane(context: Context, model: Model) extends VBox:
  padding = Insets(3)

  // Model
  model.selectedBatch.onChange { (_, _, selectedBatch) =>

  }