package objektwerks.ui.pane

import scalafx.geometry.Insets
import scalafx.scene.layout.{Priority, VBox}

import objektwerks.ui.{Context, Model}

final class LeftPane(context: Context, model: Model) extends VBox:
  padding = Insets(3)

  children = List( RecipesPane(context, model), BatchesPane(context, model) )

  VBox.setVgrow(this, Priority.Always)