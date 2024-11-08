package objektwerks.ui.pane

import scalafx.scene.layout.VBox

final class LeftPane extends VBox:
  children = List(RecipesPane(), BatchesPane())