package objektwerks.ui.pane

import scalafx.scene.layout.VBox

import objektwerks.ui.{Context, Model}

final class RightPane(context: Context, model: Model) extends VBox:
  children = List(RecipePane(context, model), BatchPane(context, model))