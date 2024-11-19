package objektwerks.ui.control

import scalafx.geometry.Pos
import scalafx.scene.control.Label
import scalafx.scene.layout.HBox

final class LabelButton(label: String) extends HBox:
  val l = new Label:
    alignment = Pos.CenterLeft
    text = label