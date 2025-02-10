package objektwerks.ui.pane

import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.layout.VBox

import objektwerks.ui.{Context, Model}

final class TimelinePane(context: Context, model: Model) extends VBox:
  padding = Insets(3)

  // Model
  model.selectedBatch.onChange { (_, _, selectedBatch) =>

  }

  // Controls
  val labelSanitizingStarted = Label( context.labelSanitizingStarted )
  val textSanitizingStarted = Label("")

  val labelSanitizingCompleted = Label( context.labelSanitizingCompleted )
  val textSanitizingCompleted = Label("")

  val labelPreparingStarted = Label( context.labelPreparingStarted )
  val textPreparingStarted = Label("")

  val labelPreparingCompleted = Label( context.labelPreparingCompleted )
  val textPreparingCompleted = Label("")

  val labelMaltingStarted = Label( context.labelMaltingStarted )
  val textMaltingStarted = Label("")

  val labelMaltingCompleted = Label( context.labelMaltingCompleted )
  val textMaltingCompleted = Label("")

  val labelMillingStarted = Label( context.labelMillingStarted )
  val textMillingStarted = Label("")

  val labelMillingCompleted = Label( context.labelMillingCompleted )
  val textMillingCompleted = Label("")

  val labelMashingingStarted = Label( context.labelMashingStarted )
  val textMashingStarted = Label("")

  val labelMashingCompleted = Label( context.labelMashingCompleted )
  val textMashingCompleted = Label("")

  val labelLauteringingStarted = Label( context.labelLauteringStarted )
  val textLauteringStarted = Label("")

  val labelLauteringCompleted = Label( context.labelLauteringCompleted )
  val textLauteringCompleted = Label("")

  val labelSpargingingStarted = Label( context.labelSpargingStarted )
  val textSpargingStarted = Label("")

  val labelSpargingCompleted = Label( context.labelSpargingCompleted )
  val textSpargingCompleted = Label("")

  val labelBoilingingStarted = Label( context.labelBoilingStarted )
  val textBoilingStarted = Label("")

  val labelBoilingCompleted = Label( context.labelBoilingCompleted )
  val textBoilingCompleted = Label("")

  val labelCoolingingStarted = Label( context.labelCoolingStarted )
  val textCoolingStarted = Label("")

  val labelCoolingCompleted = Label( context.labelCoolingCompleted )
  val textCoolingCompleted = Label("")

  val labelWhirlpoolingingStarted = Label( context.labelWhirlpoolingStarted )
  val textWhirlpoolingStarted = Label("")

  val labelWhirlpoolingCompleted = Label( context.labelWhirlpoolingCompleted )
  val textWhirlpoolingCompleted = Label("")

  val labelFermentingingStarted = Label( context.labelFermentingStarted )
  val textFermentingStarted = Label("")

  val labelFermentingCompleted = Label( context.labelFermentingCompleted )
  val textFermentingCompleted = Label("")

  val labelConditioningingStarted = Label( context.labelConditioningStarted )
  val textConditioningStarted = Label("")

  val labelConditioningCompleted = Label( context.labelConditioningCompleted )
  val textConditioningCompleted = Label("")

  val labelKeggingStarted = Label( context.labelKeggingStarted )
  val textKeggingStarted = Label("")

  val labelKeggingCompleted = Label( context.labelKeggingCompleted )
  val textKeggingCompleted = Label("")