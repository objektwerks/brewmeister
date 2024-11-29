package objektwerks.ui.pane

import scalafx.scene.layout.{Priority, VBox}

import objektwerks.ui.{Context, Model}

final class BatchPane(context: Context, model: Model) extends VBox:
  println(context)
  println(model)

  VBox.setVgrow(this, Priority.Always)