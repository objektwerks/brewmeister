package objektwerks.ui.pane

import scalafx.scene.control.{Tab, TabPane}
import scalafx.scene.layout.{Priority, VBox}

import objektwerks.ui.Context

final class RecipePane(context: Context) extends TabPane:
  val recipeTab = new Tab:
  	text = context.tabRecipe
  	closable = false
  	content = RecipePane(context)

  tabs = List(recipeTab)

  VBox.setVgrow(this, Priority.Always)