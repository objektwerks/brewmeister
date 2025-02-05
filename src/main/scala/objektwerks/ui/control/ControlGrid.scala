package objektwerks.ui.control

import scalafx.geometry.Insets
import scalafx.scene.Node
import scalafx.scene.control.Label
import scalafx.scene.layout.{ColumnConstraints, GridPane}

final class ControlGrid(controls: List[(Label, Node)],
                        columnConstraints: List[ColumnConstraints] = List.empty[ColumnConstraints]) extends GridPane:
  hgap = 6
  vgap = 6
  padding = Insets(top = 6, right = 6, bottom = 6, left = 6)
  columnConstraints = columnConstraints

  var row = 0
  for ((label, node) <- controls)
    add(label, columnIndex = 0, rowIndex = row)
    add(node, columnIndex = 1, rowIndex = row)
    row += 1