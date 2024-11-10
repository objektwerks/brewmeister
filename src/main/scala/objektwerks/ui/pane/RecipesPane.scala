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
    items = model.observableRecipes
  tableView.columnResizePolicy = TableView.ConstrainedResizePolicy
  tableView.selectionModel().selectionModeProperty.value = SelectionMode.Single

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

  val simulateButton = new Button:
    graphic = context.logoImageView
    text = context.buttonAdd
    disable = true
    onAction = { _ => simulate() }

  val buttonBar = new HBox:
    spacing = 6
    children = List(addButton, editButton, simulateButton)

  val vbox = new VBox:
    spacing = 3
    padding = Insets(3)
    children = List(tableView, buttonBar)

  val tab = new Tab:
    text = context.tabRecipes
    closable = false
    content = vbox

  tabs = List(tab)

  VBox.setVgrow(vbox, Priority.Always)
  VBox.setVgrow(this, Priority.Always)

  tableView.selectionModel().selectedItemProperty().addListener { (_, _, selectedItem) =>
    if selectedItem != null then
      editButton.disable = false
      simulateButton.disable = false
  }

  def add(): Unit = ???

  def update(): Unit = ???

  def simulate(): Unit = ???
