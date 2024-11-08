package objektwerks.ui.pane

import scalafx.scene.control.{Tab, TabPane}
import scalafx.scene.layout.{Priority, VBox}

import objektwerks.Recipe
import objektwerks.ui.Context

final class RecipePane(context: Context, recipe: Recipe) extends TabPane:
  val recipeTab = new Tab:
  	text = context.tabRecipe
  	closable = false

  tabs = List(recipeTab)

  VBox.setVgrow(this, Priority.Always)