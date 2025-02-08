package objektwerks.ui.pane

import scalafx.Includes.*
import scalafx.geometry.Insets
import scalafx.scene.control.{Alert, Button, ButtonType, SelectionMode, Tab, TabPane, TableColumn, TableView}
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.layout.{HBox, Priority, VBox}

import objektwerks.{Batch, Recipe}
import objektwerks.ui.{App, Context, Model}
import objektwerks.ui.dialog.{BrewDialog, RecipeNameDialog, RemoveConfirmationDialog}

final class RecipesPane(context: Context, model: Model) extends TabPane:
  val tableView = new TableView[Recipe]():
    columns ++= List(
      new TableColumn[Recipe, String]:
        text = context.columnRecipe
        cellValueFactory = _.value.nameProperty
    )
    items = model.observableRecipes
    columnResizePolicy = TableView.ConstrainedResizePolicy
    selectionModel().selectionModeProperty.value = SelectionMode.Single

  tableView.selectionModel().selectedItemProperty().addListener { (_, _, selectedRecipe) =>
    if selectedRecipe != null then
      model.selectedRecipe.value = selectedRecipe
      buttonRemove.disable = false
      buttonBrew.disable = false
  }

  val buttonAdd = new Button:
    graphic = context.imageViewPlus
    text = context.buttonAdd
    disable = false
    onAction = { _ => add() }

  val buttonRemove = new Button:
    graphic = context.imageViewMinus
    text = context.buttonRemove
    disable = true
    onAction = { _ => remove() }

  val buttonBrew = new Button:
    graphic = context.imageViewBang
    text = context.buttonBrew
    disable = true
    onAction = { _ => brew() }

  val buttonBar = new HBox:
    spacing = 6
    padding = Insets(3)
    children = List(buttonAdd, buttonRemove, buttonBrew)

  val vbox = new VBox:
    children = List(tableView, buttonBar)

  val tab = new Tab:
    text = context.tabRecipes
    graphic = context.imageViewRecipe
    closable = false
    content = vbox

  tabs = List(tab)

  VBox.setVgrow(this, Priority.Always)

  def add(): Unit =
    RecipeNameDialog(context, "name").showAndWait() match
      case Some(name) =>
        if name.nonEmpty && model.observableRecipes.filter(recipe => recipe.name.equalsIgnoreCase(name)).isEmpty then
          model.add( Recipe(name = name) )
          tableView.items.value.sort()
          buttonRemove.disable = false
          buttonBrew.disable = false
        else
          new Alert(AlertType.Error) {
            initOwner(App.stage)
            title = context.windowTitle
            headerText = context.recipenameAlertHeaderText
            contentText = context.recipenameAlertContentText
          }.showAndWait()
      case _ =>

  def remove(): Unit =
    RemoveConfirmationDialog(context).showAndWait() match
      case Some(ButtonType.OK) =>
        val recipe = tableView.selectionModel().selectedItemProperty().value
        if model.remove(recipe) then
          tableView.items.value.removeAll(recipe)
          tableView.getSelectionModel().clearSelection()
          tableView.items.value.sort()
          buttonRemove.disable = tableView.items.value.isEmpty()
          buttonBrew.disable = tableView.items.value.isEmpty()
      case _ =>

  def brew(): Unit =
    BrewDialog(context, model.selectedRecipe.value).showAndWait() match
      case Some(batch: Batch) => model.add(batch)
      case _ =>