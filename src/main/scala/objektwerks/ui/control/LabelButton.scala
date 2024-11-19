package objektwerks.ui.control

import scalafx.geometry.Pos
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.HBox

final class LabelButton(labelText: String,
                        buttonAction: () => Unit) extends HBox:
  spacing = 6

  val label = new Label:
    alignment = Pos.CenterLeft
    text = labelText

  val button = new Button:
    prefWidth = 75
    text = "..."
    onAction = { _ =>
      buttonAction()
    }

  children = List(label, button)