package objektwerks.ui.control

import scalafx.geometry.Pos
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.HBox

final class LabelButton(labelText: String,
                        buttonText: String,
                        buttonAction: () => Unit) extends HBox:
  val label = new Label:
    alignment = Pos.CenterLeft
    text = labelText

  val button = new Button:
    prefWidth = 75
    text = buttonText
    onAction = { _ =>
      buttonAction()
    }