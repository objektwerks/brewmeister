package objektwerks.ui.pane

import scalafx.geometry.{Insets, Orientation}
import scalafx.scene.control.{SplitPane, Tab, TabPane}
import scalafx.scene.layout.{Priority, VBox}

import objektwerks.ui.{Context, Model}

final class RightPane(context: Context, model: Model) extends VBox:
  padding = Insets(3)

  val recipeTab = new Tab:
    text = context.tabRecipe
    closable = false
    content = RecipePane(context, model)

  val recipeTabPane = new TabPane:
    tabs = List(recipeTab)

  val batchTab = new Tab:
    text = context.tabBatch
    closable = false
    content = BatchPane(context, model)

  val batchTabPane = new TabPane:
    tabs = List(batchTab)

  val splitPane = new SplitPane:
    orientation = Orientation.Vertical
    items.addAll(recipeTabPane, batchTabPane)

  splitPane.setDividerPositions(0.50, 0.50)
  VBox.setVgrow(splitPane, Priority.Always)

  children = List(splitPane)

  VBox.setVgrow(this, Priority.Always)