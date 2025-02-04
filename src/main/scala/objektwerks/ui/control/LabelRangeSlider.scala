package objektwerks.ui.control

import org.controlsfx.control.RangeSlider

import scalafx.Includes.*
import scalafx.scene.control.Label
import scalafx.scene.layout.HBox

final class LabelRangeSlider(min: Double,
                             max: Double,
                             changingLow: Double,
                             changingHigh: Double,
                             changingLowFunction: () => Unit,
                             changingHighFunction: () => Unit) extends HBox:
  val slider = new RangeSlider(min, max, changingLow, changingHigh):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(1.0)
    lowValueProperty.onChange { (_, _, newValue) =>
      labelChangingLow.text = newValue.toString
      changingLowFunction()
    }
    highValueProperty.onChange { (_, _, newValue) =>
      labelChangingHigh.text = newValue.toString
      changingHighFunction()
    }

  def changingLowValue: Double = slider.getLowValue()
  def changingHighValue: Double = slider.getHighValue()

  def changingMinValue(value: Double): Unit = slider.setLowValue(value)
  def changingMaxValue(value: Double): Unit = slider.setHighValue(value)

  val labelChangingLow = new Label():
    style = "-fx-background-color: lightGray;"
    prefWidth = 50
    text = changingLow.toString

  val labelChangingHigh = new Label():
    style = "-fx-background-color: lightGray;"
    prefWidth = 50
    text = changingHigh.toString

  spacing = 3
  children.addAll(labelChangingLow, slider, labelChangingHigh) // Required to add org.controlsfx.control.RangeSlider