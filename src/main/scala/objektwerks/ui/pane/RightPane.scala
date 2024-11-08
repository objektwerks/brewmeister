package objektwerks.ui.pane

import scalafx.scene.control.TabPane
import scalafx.scene.layout.{Priority, VBox}

import objektwerks.ui.{Context, Model}

final class RightPane(context: Context, model: Model) extends TabPane:
  tabs = List(RecipePane(context, model), BatchPane(context, model))

  VBox.setVgrow(this, Priority.Always)