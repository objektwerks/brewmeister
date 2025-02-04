package objektwerks.ui.control

import org.controlsfx.control.RangeSlider

import scalafx.scene.layout.HBox

class LabelRangeSlider(min: Double,
                       max: Double,
                       changingMin: Double,
                       changingMax: Double) extends HBox:
  val slider = new RangeSlider(min, max, changingMin, changingMax):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(1.0)
