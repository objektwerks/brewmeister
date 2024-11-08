package objektwerks.ui.pane

import scalafx.scene.control.{Tab, TabPane}
import scalafx.scene.layout.{Priority, VBox}

import objektwerks.ui.Context

final class BatchesPane(context: Context) extends TabPane:
  val recipesTab = new Tab:
  	text = "Recipes"
  	closable = false
  	content = RecipesPane(context)

  tabs = List(recipesTab)

  VBox.setVgrow(this, Priority.Always)