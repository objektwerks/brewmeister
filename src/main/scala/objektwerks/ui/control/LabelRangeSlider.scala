package objektwerks.ui.control

import org.controlsfx.control.RangeSlider

import scalafx.Includes.*
import scalafx.scene.control.Label
import scalafx.scene.layout.HBox

final class LabelRangeSlider(min: Double,
                             max: Double,
                             changingMin: Double,
                             changingMax: Double) extends HBox:
  val slider = new RangeSlider(min, max, changingMin, changingMax):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(1.0)
    lowValueProperty.onChange { (_, _, newValue) => labelChangingMin.text = newValue.toString  }
    highValueProperty.onChange { (_, _, newValue) => labelChangingMax.text = newValue.toString  }

  val labelChangingMin = new Label():
    style = "-fx-background-color: lightGray;"
    prefWidth = 50
    text = changingMin.toString

  val labelChangingMax = new Label():
    style = "-fx-background-color: lightGray;"
    prefWidth = 50
    text = changingMax.toString

  spacing = 3
  children.addAll(labelChangingMin, slider, labelChangingMax) // Required to add org.controlsfx.control.RangeSlider