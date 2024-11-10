package objektwerks.ui.pane

import scalafx.geometry.Insets
import scalafx.scene.control.{Button, Tab, TabPane, TableColumn, TableView}
import scalafx.scene.layout.{Priority, VBox}

import objektwerks.Recipe
import objektwerks.ui.{Context, Model}

final class RecipesPane(context: Context, model: Model) extends TabPane:
  val tableView = new TableView[Recipe]():
    columns ++= List(
      new TableColumn[Recipe, String]:
        text = context.columnRecipe
        cellValueFactory = _.value.nameProperty
    )
    items = model.observableRecipes
  tableView.columnResizePolicy = TableView.ConstrainedResizePolicy

  val addButton = new Button:
    graphic = context.addImageView
    text = context.buttonAdd
    disable = false
    onAction = { _ => add() }

  val editButton = new Button:
    graphic = context.editImageView
    text = context.buttonEdit
    disable = true
    onAction = { _ => update() }

  val vbox = new VBox:
    spacing = 3
    padding = Insets(3)
    children = List(tableView)

  val tab = new Tab:
    text = context.tabRecipes
    closable = false
    content = vbox

  tabs = List(tab)

  VBox.setVgrow(this, Priority.Always)