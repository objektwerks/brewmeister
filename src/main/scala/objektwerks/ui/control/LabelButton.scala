package objektwerks.ui.control

import scalafx.beans.property.ObjectProperty
import scalafx.geometry.Pos
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.HBox

import scala.annotation.meta.setter

trait LabelButton[E] extends HBox:
  val value = new ObjectProperty[E]()
  @setter def value_=(e: E): Unit = value = e

  val labelText = new ObjectProperty[String]()
  @setter def labelText_=(text: String): Unit = labelText = text

  val buttonAction: ObjectProperty[() => E] = new ObjectProperty[() => E]()
  @setter def buttonAction_=(fn: () => E): Unit = buttonAction = fn

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