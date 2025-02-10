package objektwerks.ui.pane

import scalafx.geometry.{HPos, Insets}
import scalafx.scene.Node
import scalafx.scene.control.{Label, ScrollPane}
import scalafx.scene.layout.{ColumnConstraints, Priority, VBox}

import objektwerks.Timeline
import objektwerks.ui.{Context, Model}
import objektwerks.ui.control.ControlGrid

final class TimelinePane(context: Context, model: Model) extends VBox:
  padding = Insets(3)

  // Model
  model.selectedBatch.onChange { (_, _, selectedBatch) =>
    bind(selectedBatch.timeline)
  }

  // Binding
  def bind(timeline: Timeline): Unit =
    textSanitizingStarted.text = timeline.sanitizingStarted
    textSanitizingCompleted.text = timeline.sanitizingCompleted
    textPreparingStarted.text = timeline.preparingStarted
    textPreparingCompleted.text = timeline.preparingCompleted
    textMaltingStarted.text = timeline.maltingStarted
    textMaltingCompleted.text = timeline.maltingCompleted
    textMillingStarted.text = timeline.millingStarted
    textMillingCompleted.text = timeline.millingCompleted
    textMashingStarted.text = timeline.mashingStarted
    textMashingCompleted.text = timeline.mashingCompleted
    textLauteringStarted.text = timeline.lauteringStarted
    textLauteringCompleted.text = timeline.lauteringCompleted
    textSpargingStarted.text = timeline.spargingStarted
    textSpargingCompleted.text = timeline.spargingCompleted
    textBoilingStarted.text = timeline.boilingStarted
    textBoilingCompleted.text = timeline.boilingCompleted
    textCoolingStarted.text = timeline.coolingStarted
    textCoolingCompleted.text = timeline.coolingCompleted
    textWhirlpoolingStarted.text = timeline.whirlpoolingStarted
    textWhirlpoolingCompleted.text = timeline.whirlpoolingCompleted
    textFermentingStarted.text = timeline.fermentingStarted
    textFermentingCompleted.text = timeline.fermentingCompleted
    textConditioningStarted.text = timeline.conditioningStarted
    textConditioningCompleted.text = timeline.conditioningCompleted
    textKeggingStarted.text = timeline.keggingStarted
    textKeggingCompleted.text = timeline.keggingCompleted

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

  val scrollPaneControls = new ScrollPane:
    content = controlGrid

  // Content
  children = List(scrollPaneControls)
  VBox.setVgrow(this, Priority.Always)