package objektwerks.ui.pane

import scalafx.geometry.{HPos, Insets}
import scalafx.scene.Node
import scalafx.scene.control.{Label, ScrollPane}
import scalafx.scene.layout.{ColumnConstraints, Priority, VBox}

import objektwerks.Batch
import objektwerks.ui.{Context, Model}
import objektwerks.ui.control.ControlGrid

final class BatchPane(context: Context, model: Model) extends VBox:
  padding = Insets(6)

  // Model
  model.selectedBatch.onChange { (_, _, selectedBatch) =>
    bind(selectedBatch)
  }

  // Binding
  def bind(batch: Batch): Unit =
    textRecipe.text = batch.recipe
    textStyle.text = batch.style
    textVolume.text = s"${batch.volume.value} ${batch.volume.unit.toString}"
    textMashingTemp.text = batch.mashingTemp.toString
    textPh.text = batch.pH.toString
    textBoilingTemp.text = batch.boilingTemp.toString
    textCoolingTemp.text = batch.coolingTemp.toString
    textMashEfficiency.text = batch.mashEfficiency.toString
    textOriginalGravity.text = batch.originalGravity.toString
    textFermentingTemp.text = batch.fermentingTemp.toString
    textFinalGravity.text = batch.finalGravity.toString
    textConditioningTemp.text = batch.conditioningTemp.toString
    textSrmColor.text = batch.srmColor.toString
    textIbuBitterness.text = batch.ibuBitterness.toString
    textAlcoholByVolume.text = batch.alcoholByVolume.toString
    textAlcoholByWeight.text = batch.alcoholByWeight.toString
    textCalories.text = batch.calories.toString
    textKeggingTemp.text = batch.keggingTemp.toString
    textBrewhouseEfficiency.text = batch.brewhouseEfficiency.toString
    textAppearance.text = batch.appearance.toString
    textAroma.text = batch.aroma.toString
    textTaste.text = batch.taste.toString
    textStarted.text = batch.started.toString
    textCompleted.text = batch.completed.toString

  // Controls
  val labelRecipe = Label( context.labelRecipe )
  val textRecipe = Label("")

  val labelStyle = Label( context.labelStyle )
  val textStyle = Label("")

  val labelVolume = Label( context.labelVolume )
  val textVolume = Label("")

  val labelMashingTemp = Label( context.labelMashingTemp )
  val textMashingTemp = Label("")

  val labelPh = Label( context.labelPh )
  val textPh = Label("")

  val labelBoilingTemp = Label( context.labelBoilingTemp )
  val textBoilingTemp = Label("")

  val labelCoolingTemp = Label( context.labelCoolingTemp )
  val textCoolingTemp = Label("")

  val labelMashEfficiency = Label( context.labelMashEfficiency )
  val textMashEfficiency = Label("")

  val labelOriginalGravity = Label( context.labelOriginalGravity )
  val textOriginalGravity = Label("")

  val labelFermentingTemp = Label( context.labelFermentingTemp )
  val textFermentingTemp = Label("")

  val labelFinalGravity = Label( context.labelFinalGravity )
  val textFinalGravity = Label("")

  val labelConditioningTemp = Label( context.labelConditioningTemp )
  val textConditioningTemp = Label("")

  val labelSrmColor = Label( context.labelSrmColor )
  val textSrmColor = Label("")

  val labelIbuBitterness = Label( context.labelIbuBitterness )
  val textIbuBitterness = Label("")

  val labelAlcoholByVolume = Label( context.labelAlcoholByVolume )
  val textAlcoholByVolume = Label("")

  val labelAlcoholByWeight = Label( context.labelAlcoholByWeight )
  val textAlcoholByWeight = Label("")

  val labelCalories = Label( context.labelCalories )
  val textCalories = Label("")

  val labelKeggingTemp = Label( context.labelKeggingTemp )
  val textKeggingTemp = Label("")

  val labelBrewhouseEfficiency = Label( context.labelBrewhouseEfficiency )
  val textBrewhouseEfficiency = Label("")

  val labelAppearance = Label( context.labelAppearance )
  val textAppearance = Label("")

  val labelAroma = Label( context.labelAroma )
  val textAroma = Label("")

  val labelTaste = Label( context.labelTaste )
  val textTaste = Label("")

  val labelStarted = Label( context.labelStarted )
  val textStarted = Label("")

  val labelCompleted = Label( context.labelCompleted )
  val textCompleted = Label("")

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
  val controlGrid = ControlGrid(
    controls,
    List(
      ColumnConstraints(
        minWidth = 150.0,
        prefWidth = 150.0,
        maxWidth = 200.0,
        hgrow = Priority.Always,
        halignment = HPos.Left,
        fillWidth = true      
      ),
      ColumnConstraints(
        minWidth = 150.0,
        prefWidth = 150.0,
        maxWidth = 1000.0,
        hgrow = Priority.Always,
        halignment = HPos.Left,
        fillWidth = true
      ) 
    )
  )

  val scrollPaneControls = new ScrollPane:
    content = controlGrid

  // Content
  children = List(scrollPaneControls)
  VBox.setVgrow(this, Priority.Always)