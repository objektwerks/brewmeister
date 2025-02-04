package objektwerks.ui.control

import org.controlsfx.control.RangeSlider

import scalafx.Includes.*
import scalafx.scene.control.Label
import scalafx.scene.layout.HBox

final class LabelRangeSlider(min: Double,
                             max: Double,
                             low: Double,
                             high: Double,
                             lowFunction: () => Unit,
                             highFunction: () => Unit,
                             displayAsInt: Boolean = true) extends HBox:
  val slider = new RangeSlider(min, max, low, high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(1.0)
    lowValueProperty.onChange { (_, _, newValue) =>
      labelChangingLow.text = if displayAsInt then newValue.intValue.toString else newValue.toString
      lowFunction()
    }
    highValueProperty.onChange { (_, _, newValue) =>
      labelChangingHigh.text = if displayAsInt then newValue.intValue.toString else newValue.toString
      highFunction()
    }

  def lowValue: Double = slider.getLowValue()
  def highValue: Double = slider.getHighValue()

  def changingMinValue(value: Double): Unit = slider.setLowValue(value)
  def changingMaxValue(value: Double): Unit = slider.setHighValue(value)

  val labelChangingLow = new Label():
    prefWidth = 35
    text = if displayAsInt then low.intValue.toString else low.toString

  val labelChangingHigh = new Label():
    prefWidth = 35
    text = if displayAsInt then high.intValue.toString else high.toString

  spacing = 3
  children.addAll(labelChangingLow, slider, labelChangingHigh) // Required to add org.controlsfx.control.RangeSlider