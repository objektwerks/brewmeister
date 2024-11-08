package objektwerks.ui.pane

import scalafx.scene.layout.{Priority, VBox}

import objektwerks.ui.{Context, Model}

final class LeftPane(context: Context, model: Model) extends VBox:
  children = List(RecipesPane(context, model), BatchesPane(context, model))

  VBox.setVgrow(this, Priority.Always)