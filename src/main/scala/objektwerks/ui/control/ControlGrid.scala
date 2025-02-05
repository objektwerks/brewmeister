package objektwerks.ui.control

import scalafx.geometry.Insets
import scalafx.scene.Node
import scalafx.scene.control.Label
import scalafx.scene.layout.{GridPane, Priority}

final class ControlGrid(controls: List[(Label, Node)]) extends GridPane:
  hgap = 6
  vgap = 6
  padding = Insets(top = 6, right = 6, bottom = 6, left = 6)

  var row = 0
  for ((label, node) <- controls)
    add(label, columnIndex = 0, rowIndex = row)
    add(node, columnIndex = 1, rowIndex = row)
    row += 1

  GridPane.setHgrow(this, Priority.Always)
  GridPane.setVgrow(this, Priority.Always)