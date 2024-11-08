package objektwerks.ui.pane

import scalafx.scene.control.{Tab, TabPane}
import scalafx.scene.layout.{Priority, VBox}

import objektwerks.ui.Context

final class RecipesPane(context: Context) extends TabPane:
  val recipesTab = new Tab:
  	text = context.tabRecipes
  	closable = false
  	content = RecipesPane(context)

  tabs = List(recipesTab)

  VBox.setVgrow(this, Priority.Always)