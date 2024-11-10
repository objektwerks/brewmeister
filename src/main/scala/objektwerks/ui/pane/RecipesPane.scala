package objektwerks.ui.pane

import scalafx.scene.control.{Tab, TabPane, TableColumn, TableView}
import scalafx.scene.layout.{Priority, VBox}

import objektwerks.Recipe
import objektwerks.ui.{Context, Model}

final class RecipesPane(context: Context, model: Model) extends TabPane:
  private val tableView = new TableView[Recipe]():
    columns ++= List(
      new TableColumn[Recipe, String]:
        text = "Recipe"
        cellValueFactory = _.value.name
    )
    items = model.observableRecipes

  private val recipesTab = new Tab:
  	text = context.tabRecipes
  	closable = false

  tabs = List(recipesTab)

  VBox.setVgrow(this, Priority.Always)