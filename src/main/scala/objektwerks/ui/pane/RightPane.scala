package objektwerks.ui.pane

import scalafx.geometry.{Insets, Orientation}
import scalafx.scene.control.{SplitPane, Tab, TabPane}
import scalafx.scene.layout.{Priority, VBox}

import objektwerks.ui.{Context, Model}

final class RightPane(context: Context, model: Model) extends VBox:
  padding = Insets(3)

  val recipeTab = new Tab:
    text = context.tabRecipe
    graphic = context.imageViewRecipe
    closable = false
    content = RecipePane(context, model)

  val recipeTabPane = new TabPane:
    tabs = List(recipeTab)

  val batchTab = new Tab:
    text = context.tabBatch
    graphic = context.imageViewBatch
    closable = false
    content = BatchPane(context, model)

  val logTab = new Tab:
    text = context.tabLog
    graphic = context.imageViewLog
    closable = false
    content = LogPane(context, model)

  val timelineTab = new Tab:
    text = context.tabTimeline
    graphic = context.imageViewTimeline
    closable = false
    content = TimelinePane(context, model)

  val batchTabPane = new TabPane:
    tabs = List(batchTab, logTab, timelineTab)

  val splitPane = new SplitPane:
    orientation = Orientation.Vertical
    items.addAll(recipeTabPane, batchTabPane)

  splitPane.setDividerPositions(0.50, 0.50)
  VBox.setVgrow(splitPane, Priority.Always)

  children = List(splitPane)

  VBox.setVgrow(this, Priority.Always)