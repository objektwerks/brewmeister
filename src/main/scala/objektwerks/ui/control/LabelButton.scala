package objektwerks.ui.control

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.HBox

final class LabelButton(labelText: String,
                        buttonText: String,
                        buttonAction: () => Unit) extends HBox:
  spacing = 6
  padding = Insets(6)

  val label = new Label:
    alignment = Pos.CenterLeft
    text = labelText

  val button = new Button:
    prefWidth = 75
    text = buttonText
    onAction = { _ =>
      buttonAction()
    }

  children = List(label, button)