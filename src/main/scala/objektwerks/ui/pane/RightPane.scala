package objektwerks.ui.pane

import scalafx.scene.layout.HBox

import objektwerks.ui.Context

final class RightPane(context: Context) extends HBox:
  children = List(RecipePane(), BatchPane())