package objektwerks.ui.pane

import scalafx.Includes.*
import scalafx.geometry.Insets
import scalafx.scene.control.{Button, SelectionMode, Tab, TabPane, TableColumn, TableView}
import scalafx.scene.layout.{HBox, Priority, VBox}

import objektwerks.Recipe
import objektwerks.ui.{Context, Model}

final class RecipesPane(context: Context, model: Model) extends TabPane:
  val tableView = new TableView[Recipe]():
    columns ++= List(
      new TableColumn[Recipe, String]:
        text = context.columnRecipe
        cellValueFactory = _.value.nameProperty
    )
    items = model.observableRecipes.sorted
  tableView.columnResizePolicy = TableView.ConstrainedResizePolicy
  tableView.selectionModel().selectionModeProperty.value = SelectionMode.Single
  tableView.selectionModel().selectedItemProperty().addListener { (_, _, selectedRecipe) =>
    if selectedRecipe != null then
      model.selectedRecipeIndex.value = tableView.selectionModel().selectedIndex.value
      model.selectedRecipe.value = selectedRecipe
      buttonBrew.disable = false
  }

  val buttonAdd = new Button:
    graphic = context.imageViewPlus
    tooltip = context.tooltipAdd
    disable = false
    onAction = { _ => add() }

  val buttonBrew = new Button:
    graphic = context.imageViewBang
    tooltip = context.tooltipBrew
    disable = true
    onAction = { _ => brew() }

  val buttonBar = new HBox:
    spacing = 6
    padding = Insets(3)
    children = List(buttonAdd, buttonBrew)

  val vbox = new VBox:
    children = List(tableView, buttonBar)

  val tab = new Tab:
    text = context.tabRecipes
    closable = false
    content = vbox

  tabs = List(tab)

  VBox.setVgrow(this, Priority.Always)

  def add(): Unit =
    model.observableRecipes.add(0, Recipe())
    tableView.selectionModel().select(0)

  def brew(): Unit =
    buttonBrew.disable = true // TODO