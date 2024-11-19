package objektwerks.ui.control

import scalafx.beans.property.ObjectProperty
import scalafx.geometry.Pos
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.HBox

trait LabelButton[E] extends HBox:
  val value = new ObjectProperty[E]()
  val labelText = new ObjectProperty[String]()
  val buttonAction = new ObjectProperty[() => E]()

  spacing = 6

  val label = new Label:
    alignment = Pos.CenterLeft
    text = labelText.value

  val button = new Button:
    prefWidth = 75
    text = "..."
    onAction = { _ =>
      value.value = buttonAction.value()
    }

  children = List(label, button)