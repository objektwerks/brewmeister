package objektwerks.ui.control

import scalafx.beans.property.ObjectProperty
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.{HBox, Priority}

import scala.annotation.meta.setter
import scala.language.strictEquality

trait LabelButton[E] extends HBox:
  given CanEqual[E, E] = CanEqual.derived

  val value = new ObjectProperty[E]()
  @setter def value_=(e: E): Unit = value.value = e

  val text = new ObjectProperty[String]()
  @setter def text_=(value: String): Unit = text.value = value

  val buttonAction: ObjectProperty[() => E] = new ObjectProperty[() => E]()
  @setter def buttonAction_=(fn: () => E): Unit = buttonAction.value = fn

  val label = new Label("n/a"):
    prefWidth = 250
  label.text <== text

  val button = new Button:
    prefWidth = 25
    onAction = { _ =>
      val newValue = buttonAction.value()
      if newValue != value.value then value.value = newValue
    }
  button.text = "..."

  HBox.setHgrow(this, Priority.Always)

  spacing = 9
  children = List(label, button)