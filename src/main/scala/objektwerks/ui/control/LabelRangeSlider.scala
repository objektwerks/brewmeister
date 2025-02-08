package objektwerks.ui.control

import org.controlsfx.control.RangeSlider

import scalafx.Includes.*
import scalafx.scene.control.Label
import scalafx.scene.layout.{HBox, Priority, Region}
import scalafx.scene.text.TextAlignment

import objektwerks.*

enum Format derives CanEqual:
  case asInt, asDouble, asGravity

  def as(value: Double): String =
    this match
      case Format.asInt => value.intValue.toString
      case Format.asDouble => value.format.toString
      case Format.asGravity => value.formatGravity.toString

final class LabelRangeSlider(min: Double,
                             max: Double,
                             low: Double,
                             increment: Double,
                             high: Double,
                             lowFunction: () => Unit = () => (),
                             highFunction: () => Unit = () => (),
                             format: Format = Format.asInt) extends HBox:
  val slider = new RangeSlider(min, max, low, high):
    setPrefWidth(250)
    setShowTickMarks(true)
    setShowTickLabels(true)
    setMajorTickUnit(increment)
    lowValueProperty.onChange { (_, _, newValue) =>
      lowValue( if format == Format.asInt then newValue.intValue else newValue.doubleValue )
      lowFunction()
    }
    highValueProperty.onChange { (_, _, newValue) =>
      highValue( if format == Format.asInt then newValue.intValue else newValue.doubleValue )
      highFunction()
    }

  def lowValue: Double = slider.getLowValue()
  def highValue: Double = slider.getHighValue()

  def lowValue(value: Double): Unit =
    if value >= min then
      slider.setLowValue(value)
      labelLow.text = format.as(value)

  def highValue(value: Double): Unit =
    if value <= max then
      slider.setHighValue(value)
      labelHigh.text = format.as(value)

  val labelLow = new Label():
    prefWidth = 50
    textAlignment = TextAlignment.Right
    text = format.as(low)

  val labelHigh = new Label():
    prefWidth = 50
    textAlignment = TextAlignment.Right
    text = format.as(high)

  val spacerLow = Region()
  val spacerHigh = Region()

  HBox.setHgrow(spacerLow, Priority.Always)
  HBox.setHgrow(spacerHigh, Priority.Always)
  HBox.setHgrow(this, Priority.Always)

  children.addAll(labelLow, spacerLow, slider, spacerHigh, labelHigh) // Required to add org.controlsfx.control.RangeSlider