package objektwerks.ui.dialog

import scalafx.Includes.*
import scalafx.geometry.HPos
import scalafx.scene.Node
import scalafx.scene.control.{ButtonType, Dialog, Label}
import scalafx.scene.layout.{ColumnConstraints, Priority}

import objektwerks.ui.{App, Context, Model}
import objektwerks.ui.control.ControlGrid

final class TimelineDialog(context: Context, model: Model) extends Dialog[Unit]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogTimeline

  val timeline = model.selectedBatch.value.timeline

  val labelSanitizingStarted = Label( context.labelSanitizingStarted )
  val textSanitizingStarted = Label( timeline.sanitizingStarted )

  val labelSanitizingCompleted = Label( context.labelSanitizingCompleted )
  val textSanitizingCompleted = Label( timeline.sanitizingCompleted )

  val labelPreparingStarted = Label( context.labelPreparingStarted )
  val textPreparingStarted = Label( timeline.preparingStarted )

  val labelPreparingCompleted = Label( context.labelPreparingCompleted )
  val textPreparingCompleted = Label( timeline.preparingCompleted )

  val labelMaltingStarted = Label( context.labelMaltingStarted )
  val textMaltingStarted = Label( timeline.maltingStarted )

  val labelMaltingCompleted = Label( context.labelMaltingCompleted )
  val textMaltingCompleted = Label( timeline.maltingCompleted )

  val labelMillingStarted = Label( context.labelMillingStarted )
  val textMillingStarted = Label( timeline.millingStarted )

  val labelMillingCompleted = Label( context.labelMillingCompleted )
  val textMillingCompleted = Label( timeline.millingCompleted )

  val labelMashingingStarted = Label( context.labelMashingStarted )
  val textMashingStarted = Label( timeline.mashingStarted )

  val labelMashingCompleted = Label( context.labelMashingCompleted )
  val textMashingCompleted = Label( timeline.mashingCompleted )

  val labelLauteringingStarted = Label( context.labelLauteringStarted )
  val textLauteringStarted = Label( timeline.lauteringStarted )

  val labelLauteringCompleted = Label( context.labelLauteringCompleted )
  val textLauteringCompleted = Label( timeline.lauteringCompleted )

  val labelSpargingingStarted = Label( context.labelSpargingStarted )
  val textSpargingStarted = Label( timeline.spargingStarted )

  val labelSpargingCompleted = Label( context.labelSpargingCompleted )
  val textSpargingCompleted = Label( timeline.spargingCompleted )

  val labelBoilingingStarted = Label( context.labelBoilingStarted )
  val textBoilingStarted = Label( timeline.boilingStarted )

  val labelBoilingCompleted = Label( context.labelBoilingCompleted )
  val textBoilingCompleted = Label( timeline.boilingCompleted )

  val labelCoolingingStarted = Label( context.labelCoolingStarted )
  val textCoolingStarted = Label( timeline.coolingStarted )

  val labelCoolingCompleted = Label( context.labelCoolingCompleted )
  val textCoolingCompleted = Label( timeline.coolingCompleted )

  val labelWhirlpoolingingStarted = Label( context.labelWhirlpoolingStarted )
  val textWhirlpoolingStarted = Label( timeline.whirlpoolingStarted )

  val labelWhirlpoolingCompleted = Label( context.labelWhirlpoolingCompleted )
  val textWhirlpoolingCompleted = Label( timeline.whirlpoolingCompleted )

  val labelFermentingingStarted = Label( context.labelFermentingStarted )
  val textFermentingStarted = Label( timeline.fermentingStarted )

  val labelFermentingCompleted = Label( context.labelFermentingCompleted )
  val textFermentingCompleted = Label( timeline.fermentingCompleted )

  val labelConditioningingStarted = Label( context.labelConditioningStarted )
  val textConditioningStarted = Label( timeline.conditioningStarted )

  val labelConditioningCompleted = Label( context.labelConditioningCompleted )
  val textConditioningCompleted = Label( timeline.conditioningCompleted )

  val labelKeggingStarted = Label( context.labelKeggingStarted )
  val textKeggingStarted = Label( timeline.keggingStarted )

  val labelKeggingCompleted = Label( context.labelKeggingCompleted )
  val textKeggingCompleted = Label( timeline.keggingCompleted )

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
  val controlGrid = ControlGrid(
    controls,
    List(
      ColumnConstraints(
        minWidth = 150.0,
        prefWidth = 150.0,
        maxWidth = 1000.0,
        hgrow = Priority.Always,
        halignment = HPos.LEFT,
        fillWidth = true      
      ),
      ColumnConstraints(
        minWidth = 120.0,
        prefWidth = 120.0,
        maxWidth = 1000.0,
        hgrow = Priority.Always,
        halignment = HPos.LEFT,
        fillWidth = true
      ) 
    )
  )

  dialogPane().content = controlGrid
  dialogPane().buttonTypes = List(ButtonType.Close)