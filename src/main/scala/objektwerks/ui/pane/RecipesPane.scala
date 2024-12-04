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
      model.selectedRecipe.value = selectedRecipe
      simulateButton.disable = false
  }

  val addButton = new Button:
    graphic = context.addImageView
    tooltip = context.tooltipAdd
    disable = false
    onAction = { _ => add() }

  val simulateButton = new Button:
    graphic = context.bangImageView
    tooltip = context.tooltipRun
    disable = true
    onAction = { _ => run() }

  val buttonBar = new HBox:
    spacing = 6
    padding = Insets(3)
    children = List(addButton, simulateButton)

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

  def run(): Unit =
    simulateButton.disable = true // TODO