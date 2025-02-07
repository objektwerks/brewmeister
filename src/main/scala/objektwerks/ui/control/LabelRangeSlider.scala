package objektwerks.ui.control

import org.controlsfx.control.RangeSlider

import scalafx.Includes.*
import scalafx.scene.control.Label
import scalafx.scene.layout.{HBox, Priority, Region}
import scalafx.scene.text.TextAlignment

import objektwerks.*

enum Format derives CanEqual:
  case asInt, asDouble, asGravity

object Format:
  def as(format: Format, value: Double) =
    if format == Format.asInt then value.intValue.toString
    else if format == Format.asDouble then value.format.toString
    else value.formatGravity.toString

final class LabelRangeSlider(min: Double,
                             max: Double,
                             low: Double,
                             increment: Double,
                             high: Double,
                             lowFunction: () => Unit = () => (),
                             highFunction: () => Unit = () => (),
                             format: Format = Format.asInt) extends HBox:
  val slider = new RangeSlider(min, max, low, high):
    setPrefWidth(200)
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(increment)
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
      labelLow.text = Format.as(format, value)

  def highValue(value: Double): Unit =
    if value <= max then
      slider.setHighValue(value)
      labelHigh.text = Format.as(format, value)

  val labelLow = new Label():
    prefWidth = 40
    textAlignment = TextAlignment.Right
    text = Format.as(format, low)

  val labelHigh = new Label():
    prefWidth = 40
    textAlignment = TextAlignment.Right
    text = Format.as(format, high)

  val spacerLow = Region()
  val spacerHigh = Region()
  HBox.setHgrow(spacerLow, Priority.Always)
  HBox.setHgrow(spacerHigh, Priority.Always)

  children.addAll(labelLow, spacerLow, slider, spacerHigh, labelHigh) // Required to add org.controlsfx.control.RangeSlider