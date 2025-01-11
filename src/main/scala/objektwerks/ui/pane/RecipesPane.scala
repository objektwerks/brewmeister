package objektwerks.ui.pane

import scalafx.Includes.*
// import scalafx.beans.property.ObjectProperty
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
    // items <== ObjectProperty(model.observableRecipes)
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
    tooltip = context.tooltipAdd
    disable = false
    onAction = { _ => add() }

  val buttonRemove = new Button:
    graphic = context.imageViewMinus
    tooltip = context.tooltipRemove
    disable = true
    onAction = { _ => remove() }

  val buttonBrew = new Button:
    graphic = context.imageViewBang
    tooltip = context.tooltipBrew
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
    closable = false
    content = vbox

  tabs = List(tab)

  VBox.setVgrow(this, Priority.Always)

  def add(): Unit =
    RecipeNameDialog(context, "name").showAndWait() match
      case Some(name) =>
        if name.nonEmpty && model.observableRecipes.filter(recipe => recipe.name.equalsIgnoreCase(name)).isEmpty then
          model.add( Recipe(name = name) )
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
        val index = tableView.selectionModel().selectedIndex.value
        val recipe = tableView.selectionModel().selectedItemProperty().value
        if model.remove(recipe) && index > -1 then
          tableView.items.value.remove(index)
          tableView.items.value.sort()
          buttonRemove.disable = true
          buttonBrew.disable = true
      case _ =>

  def brew(): Unit =
    BrewDialog(context, model.selectedRecipe.value).showAndWait() match
      case Some(batch: Batch) => model.add(batch)
      case _ =>