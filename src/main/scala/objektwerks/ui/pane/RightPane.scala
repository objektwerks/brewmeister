package objektwerks.ui.pane

import scalafx.scene.layout.HBox

import objektwerks.ui.{Context, Model}

final class RightPane(context: Context, model: Model) extends HBox:
  children = List(RecipePane(context, model), BatchPane(context, model))