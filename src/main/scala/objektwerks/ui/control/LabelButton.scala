package objektwerks.ui.control

import scalafx.beans.property.ObjectProperty
import scalafx.geometry.Pos
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.HBox

import scala.annotation.meta.setter
import scala.language.strictEquality

trait LabelButton[E] extends HBox:
  given CanEqual[E, E] = CanEqual.derived

  val value = new ObjectProperty[E]()
  @setter def value_=(e: E): Unit = value.value = e

  val labelText = new ObjectProperty[String]()
  @setter def labelText_=(text: String): Unit = labelText.value = text

  val buttonAction: ObjectProperty[() => E] = new ObjectProperty[() => E]()
  @setter def buttonAction_=(fn: () => E): Unit = buttonAction.value = fn

  val label = new Label:
    alignment = Pos.CenterLeft
    text = labelText.value

  val button = new Button:
    prefWidth = 25
    text = "..."
    onAction = { _ =>
      val newValue = buttonAction.value()
      if newValue != value.value then value.value = newValue
    }

  spacing = 3
  children = List(label, button)