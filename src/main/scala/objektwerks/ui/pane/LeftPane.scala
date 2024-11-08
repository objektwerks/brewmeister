package objektwerks.ui.pane

import scalafx.scene.layout.VBox

import objektwerks.ui.{Context, Model}

final class LeftPane(context: Context, model: Model) extends VBox:
  children = List(RecipesPane(context, model), BatchesPane(context, model))