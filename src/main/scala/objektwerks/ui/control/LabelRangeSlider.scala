package objektwerks.ui.control

import org.controlsfx.control.RangeSlider

import scalafx.Includes.*
import scalafx.scene.control.Label
import scalafx.scene.layout.HBox

final class LabelRangeSlider(min: Double,
                             max: Double,
                             low: Double,
                             increment: Double,
                             high: Double,
                             lowFunction: () => Unit = () => (),
                             highFunction: () => Unit = () => (),
                             displayAsInt: Boolean = true) extends HBox:
  val slider = new RangeSlider(min, max, low, high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(increment)
    lowValueProperty.onChange { (_, _, newValue) =>
      lowValue( if displayAsInt then newValue.intValue else newValue.doubleValue )
      lowFunction()
    }
    highValueProperty.onChange { (_, _, newValue) =>
      highValue( if displayAsInt then newValue.intValue else newValue.doubleValue )
      highFunction()
    }

  def lowValue: Double = slider.getLowValue()
  def highValue: Double = slider.getHighValue()

  def lowValue(value: Double): Unit =
    if value >= min then
      slider.setLowValue(value)
      labelLow.text = if displayAsInt then value.intValue.toString else value.toString

  def highValue(value: Double): Unit =
    if value <= max then
      slider.setHighValue(value)
      labelHigh.text = if displayAsInt then value.intValue.toString else value.toString

  val labelLow = new Label():
    prefWidth = 35
    text = if displayAsInt then low.intValue.toString else low.toString

  val labelHigh = new Label():
    prefWidth = 35
    text = if displayAsInt then high.intValue.toString else high.toString

  spacing = 6
  children.addAll(labelLow, slider, labelHigh) // Required to add org.controlsfx.control.RangeSlider