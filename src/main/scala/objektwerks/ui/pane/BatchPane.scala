package objektwerks.ui.pane

import scalafx.geometry.Insets
import scalafx.scene.Node
import scalafx.scene.control.{Button, Label, ScrollPane}
import scalafx.scene.layout.{HBox, Priority, VBox}

import objektwerks.ui.{Context, Model}
import objektwerks.ui.control.ControlGrid

final class BatchPane(context: Context, model: Model) extends VBox:
  println(context)

  // Model
  model.selectedBatch.onChange { (_, _, selectedBatch) =>
    // TODO - bind selected batch to this pane!
  }

  // Methods
  def log(): Unit = ???

  def process(): Unit = ???

  // Controls
  val labelRecipe = Label( context.labelRecipe )
  val textRecipe = Label( model.selectedBatch.value.recipe )

  val labelStyle = Label( context.labelStyle )
  val textStyle = Label( model.selectedBatch.value.style )

  val labelVolume = Label( context.labelVolume )
  val textVolume = Label( s"${model.selectedBatch.value.volume.value} ${model.selectedBatch.value.volume.unit.toString}" )

  val labelMashingTemp = Label( context.labelMashingTemp )
  val textMashingTemp = Label( model.selectedBatch.value.mashingTemp.toString )

  val labelPh = Label( context.labelPh )
  val textPh = Label( model.selectedBatch.value.pH.toString )

  val labelBoilingTemp = Label( context.labelBoilingTemp )
  val textBoilingTemp = Label( model.selectedBatch.value.boilingTemp.toString )

  val labelCoolingTemp = Label( context.labelCoolingTemp )
  val textCoolingTemp = Label( model.selectedBatch.value.coolingTemp.toString )

  val labelAppearance = Label( context.labelAppearance )
  val textAppearance = Label( model.selectedBatch.value.appearance.toString )

  val labelAroma = Label( context.labelAroma )
  val textAroma = Label( model.selectedBatch.value.aroma.toString )

  val labelTaste = Label( context.labelTaste )
  val textTaste = Label( model.selectedBatch.value.taste.toString )

  val labelStarted = Label( context.labelStarted )
  val textStarted = Label( model.selectedBatch.value.started )

  val labelCompleted = Label( context.labelCompleted )
  val textCompleted = Label( model.selectedBatch.value.completed )

  val controls = List[(Label, Node)](
    labelRecipe -> textRecipe,
    labelStyle -> textStyle,
    labelVolume -> textVolume,
    labelMashingTemp -> textMashingTemp,
    labelPh -> textPh,
    labelBoilingTemp -> textBoilingTemp,
    labelAppearance -> textAppearance,
    labelAroma -> textAroma,
    labelTaste -> textTaste,
    labelStarted -> textStarted,
    labelCompleted -> textCompleted
  )

  val scrollPaneControls = new ScrollPane:
    content = ControlGrid(controls)

  val buttonLog = new Button:
    graphic = context.imageViewLog
    tooltip = context.tooltipLog
    disable = true
    onAction = { _ => log() }

  val buttonProcess = new Button:
    graphic = context.imageViewProcess
    tooltip = context.tooltipProcess
    disable = true
    onAction = { _ => process() }

  val buttonBar = new HBox:
    spacing = 6
    padding = Insets(3)
    children = List(buttonLog, buttonProcess)

  children = List(scrollPaneControls, buttonBar)

  VBox.setVgrow(this, Priority.Always)