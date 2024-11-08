package objektwerks.ui.pane

import scalafx.scene.control.{Tab, TabPane}
import scalafx.scene.layout.{Priority, VBox}

import objektwerks.ui.{Context, Model}

final class RecipesPane(context: Context, model: Model) extends TabPane:
  val recipesTab = new Tab:
  	text = context.tabRecipes
  	closable = false

  tabs = List(recipesTab)

  VBox.setVgrow(this, Priority.Always)