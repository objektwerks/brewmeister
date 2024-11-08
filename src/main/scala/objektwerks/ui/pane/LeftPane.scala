package objektwerks.ui.pane

import scalafx.scene.layout.VBox
import objektwerks.ui.Context

final class LeftPane(context: Context) extends VBox:
  children = List(RecipesPane(context), BatchesPane(context))