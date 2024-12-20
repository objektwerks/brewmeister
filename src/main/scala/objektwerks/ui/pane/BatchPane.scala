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

  val labelMashEfficiency = Label( context.labelMashEfficiency )
  val textMashEfficiency = Label( model.selectedBatch.value.mashEfficiency.toString )

  val labelOriginalGravity = Label( context.labelOriginalGravity )
  val textOriginalGravity = Label( model.selectedBatch.value.originalGravity.toString )

  val labelFermentingTemp = Label( context.labelFermentingTemp )
  val textFermentingTemp = Label( model.selectedBatch.value.fermentingTemp.toString )

  val labelFinalGravity = Label( context.labelFinalGravity )
  val textFinalGravity = Label( model.selectedBatch.value.finalGravity.toString )

  val labelConditioningTemp = Label( context.labelConditioningTemp )
  val textConditioningTemp = Label( model.selectedBatch.value.conditioningTemp.toString )

  val labelSrmColor = Label( context.labelSrmColor )
  val textSrmColor = Label( model.selectedBatch.value.srmColor.toString )

  val labelIbuBitterness = Label( context.labelIbuBitterness )
  val textIbuBitterness = Label( model.selectedBatch.value.ibuBitterness.toString )

  val labelAlcoholByVolume = Label( context.labelAlcoholByVolume )
  val textAlcoholByVolume = Label( model.selectedBatch.value.alcoholByVolume.toString )

  val labelAlcoholByWeight = Label( context.labelAlcoholByWeight )
  val textAlcoholByWeight = Label( model.selectedBatch.value.alcoholByWeight.toString )

  val labelCalories = Label( context.labelCalories )
  val textCalories = Label( model.selectedBatch.value.calories.toString )

  val labelKeggingTemp = Label( context.labelKeggingTemp )
  val textKeggingTemp = Label( model.selectedBatch.value.keggingTemp.toString )

  val labelBrewhouseEfficiency = Label( context.labelBrewhouseEfficiency )
  val textBrewhouseEfficiency = Label( model.selectedBatch.value.brewhouseEfficiency.toString )

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
    labelCoolingTemp -> textCoolingTemp,
    labelMashEfficiency -> textMashEfficiency,
    labelOriginalGravity -> textOriginalGravity,
    labelFermentingTemp -> textFermentingTemp,
    labelFinalGravity -> textFinalGravity,
    labelConditioningTemp -> textConditioningTemp,
    labelSrmColor -> textSrmColor,
    labelIbuBitterness -> textIbuBitterness,
    labelAlcoholByVolume -> textAlcoholByVolume,
    labelAlcoholByWeight -> textAlcoholByWeight,
    labelCalories -> textCalories,
    labelKeggingTemp -> textKeggingTemp,
    labelBrewhouseEfficiency -> textBrewhouseEfficiency,
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