package objektwerks.ui.pane

import scalafx.scene.control.{Tab, TabPane}
import scalafx.scene.layout.{Priority, VBox}

import objektwerks.ui.{Context, Model}

final class RecipePane(context: Context, model: Model) extends TabPane:
  val recipeTab = new Tab:
  	text = context.tabRecipe
  	closable = false

  tabs = List(recipeTab)

  VBox.setVgrow(this, Priority.Always)