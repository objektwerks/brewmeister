package objektwerks.ui.pane

import scalafx.scene.layout.HBox

final class RightPane extends HBox:
  children = List(RecipePane(), BatchPane())