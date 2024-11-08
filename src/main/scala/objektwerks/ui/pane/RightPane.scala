package objektwerks.ui.pane

import scalafx.scene.control.TabPane

import objektwerks.ui.{Context, Model}

final class RightPane(context: Context, model: Model) extends TabPane:
  tabs = List(RecipePane(context, model), BatchPane(context, model))