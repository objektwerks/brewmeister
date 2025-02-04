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
      labelLow.text = if displayAsInt then newValue.intValue.toString else newValue.toString
      lowFunction()
    }
    highValueProperty.onChange { (_, _, newValue) =>
      labelHigh.text = if displayAsInt then newValue.intValue.toString else newValue.toString
      highFunction()
    }

  def lowValue: Double = slider.getLowValue()
  def highValue: Double = slider.getHighValue()

  def lowValue(value: Double): Unit = slider.setLowValue(value)
  def highValue(value: Double): Unit = slider.setHighValue(value)

  val labelLow = new Label():
    prefWidth = 35
    text = if displayAsInt then low.intValue.toString else low.toString

  val labelHigh = new Label():
    prefWidth = 35
    text = if displayAsInt then high.intValue.toString else high.toString

  spacing = 3
  children.addAll(labelLow, slider, labelHigh) // Required to add org.controlsfx.control.RangeSlider