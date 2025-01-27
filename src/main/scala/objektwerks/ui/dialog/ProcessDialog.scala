package objektwerks.ui.dialog

import scalafx.Includes.*
import scalafx.scene.Node
import scalafx.scene.control.{ButtonType, Dialog, Label}

import objektwerks.ui.{App, Context, Model}
import objektwerks.ui.control.ControlGrid

final class ProcessDialog(context: Context, model: Model) extends Dialog[Unit]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogProcess

  val process = model.selectedBatch.value.process

  val labelSanitizingStarted = Label( context.labelSanitizingStarted )
  val textSanitizingStarted = Label( process.sanitizingStarted )

  val labelSanitizingCompleted = Label( context.labelSanitizingCompleted )
  val textSanitizingCompleted = Label( process.sanitizingCompleted )

  val labelPreparingStarted = Label( context.labelPreparingStarted )
  val textPreparingStarted = Label( process.preparingStarted )

  val labelPreparingCompleted = Label( context.labelPreparingCompleted )
  val textPreparingCompleted = Label( process.preparingCompleted )

  val labelMaltingStarted = Label( context.labelMaltingStarted )
  val textMaltingStarted = Label( process.maltingStarted )

  val labelMaltingCompleted = Label( context.labelMaltingCompleted )
  val textMaltingCompleted = Label( process.maltingCompleted )

  val labelMillingStarted = Label( context.labelMillingStarted )
  val textMillingStarted = Label( process.millingStarted )

  val labelMillingCompleted = Label( context.labelMillingCompleted )
  val textMillingCompleted = Label( process.millingCompleted )

  val labelMashingingStarted = Label( context.labelMashingStarted )
  val textMashingStarted = Label( process.mashingStarted )

  val labelMashingCompleted = Label( context.labelMashingCompleted )
  val textMashingCompleted = Label( process.mashingCompleted )

  val labelLauteringingStarted = Label( context.labelLauteringStarted )
  val textLauteringStarted = Label( process.lauteringStarted )

  val labelLauteringCompleted = Label( context.labelLauteringCompleted )
  val textLauteringCompleted = Label( process.lauteringCompleted )

  val labelSpargingingStarted = Label( context.labelSpargingStarted )
  val textSpargingStarted = Label( process.spargingStarted )

  val labelSpargingCompleted = Label( context.labelSpargingCompleted )
  val textSpargingCompleted = Label( process.spargingCompleted )

  val labelBoilingingStarted = Label( context.labelBoilingStarted )
  val textBoilingStarted = Label( process.boilingStarted )

  val labelBoilingCompleted = Label( context.labelBoilingCompleted )
  val textBoilingCompleted = Label( process.boilingCompleted )

  val labelCoolingingStarted = Label( context.labelCoolingStarted )
  val textCoolingStarted = Label( process.coolingStarted )

  val labelCoolingCompleted = Label( context.labelCoolingCompleted )
  val textCoolingCompleted = Label( process.coolingCompleted )

  val labelWhirlpoolingingStarted = Label( context.labelWhirlpoolingStarted )
  val textWhirlpoolingStarted = Label( process.whirlpoolingStarted )

  val labelWhirlpoolingCompleted = Label( context.labelWhirlpoolingCompleted )
  val textWhirlpoolingCompleted = Label( process.whirlpoolingCompleted )

  val labelFermentingingStarted = Label( context.labelFermentingStarted )
  val textFermentingStarted = Label( process.fermentingStarted )

  val labelFermentingCompleted = Label( context.labelFermentingCompleted )
  val textFermentingCompleted = Label( process.fermentingCompleted )

  val labelConditioningingStarted = Label( context.labelConditioningStarted )
  val textConditioningStarted = Label( process.conditioningStarted )

  val labelConditioningCompleted = Label( context.labelConditioningCompleted )
  val textConditioningCompleted = Label( process.conditioningCompleted )

  val labelKeggingStarted = Label( context.labelKeggingStarted )
  val textKeggingStarted = Label( process.keggingStarted )

  val labelKeggingCompleted = Label( context.labelKeggingCompleted )
  val textKeggingCompleted = Label( process.keggingCompleted )

  val controls = List[(Label, Node)](
    labelSanitizingStarted -> textSanitizingStarted,
    labelSanitizingCompleted -> textSanitizingCompleted,
    labelPreparingStarted -> textPreparingStarted,
    labelPreparingCompleted -> textPreparingCompleted,
    labelMaltingStarted -> textMaltingStarted,
    labelMaltingCompleted -> textMaltingCompleted,
    labelMillingStarted -> textMillingStarted,
    labelMillingCompleted -> textMillingCompleted,
    labelMashingingStarted -> textMashingStarted,
    labelMashingCompleted -> textMashingCompleted,
    labelLauteringingStarted -> textLauteringStarted,
    labelLauteringCompleted -> textLauteringCompleted,
    labelSpargingingStarted -> textSpargingStarted,
    labelSpargingCompleted -> textSpargingCompleted,
    labelBoilingingStarted -> textBoilingStarted,
    labelBoilingCompleted -> textBoilingCompleted,
    labelCoolingingStarted -> textCoolingStarted,
    labelCoolingCompleted -> textCoolingCompleted,
    labelWhirlpoolingingStarted -> textWhirlpoolingStarted,
    labelWhirlpoolingCompleted -> textWhirlpoolingCompleted,
    labelFermentingingStarted -> textFermentingStarted,
    labelFermentingCompleted -> textFermentingCompleted,
    labelConditioningingStarted -> textConditioningStarted,
    labelConditioningCompleted -> textConditioningCompleted,
    labelKeggingStarted -> textKeggingStarted,
    labelKeggingCompleted -> textKeggingCompleted
  )

  dialogPane().content = ControlGrid(controls)

  dialogPane().buttonTypes = List(ButtonType.Close)