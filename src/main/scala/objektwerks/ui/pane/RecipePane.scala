package objektwerks.ui.pane

import org.controlsfx.control.RangeSlider

import scalafx.Includes.*
import scalafx.geometry.Insets
import scalafx.scene.Node
import scalafx.scene.control.{Label, ScrollPane}
import scalafx.scene.layout.{Priority, VBox}

import objektwerks.*
import objektwerks.ui.{Context, Model}
import objektwerks.ui.control.{ControlGrid, LabelButton, NonEmptyTextField}
import objektwerks.ui.control.DoubleTextField
import objektwerks.ui.dialog.{AdjunctsDialog, GrainsDialog, HopsDialog, TempRangeDurationDialog, VolumeDialog, YeastsDialog}

final class RecipePane(context: Context, model: Model) extends ScrollPane:
  padding = Insets(3)

  val recipe = model.selectedRecipe

  model.selectedRecipe.onChange { (_, _, newRecipe) =>
    textFieldName.text = newRecipe.name
    textFieldStyle.text = newRecipe.style
    textFieldWater.text = newRecipe.water
  }

  val labelName = Label( context.labelName )
  val textFieldName = NonEmptyTextField()

  val labelStyle = Label( context.labelStyle )
  val textFieldStyle = NonEmptyTextField()

  val labelWater = Label( context.labelWater )
  val textFieldWater = NonEmptyTextField()

  val labelVolume = Label( context.labelVolume )
  val labelButtonVolume = new LabelButton[Volume]:
    text = s"${recipe.value.volume.value} ${recipe.value.volume.unit.toString}"
    value = recipe.value.volume
    buttonAction = () => {
      VolumeDialog(context, recipe.value.volume).showAndWait() match
        case Some(volume: Volume) => volume
        case _ => recipe.value.volume
    }

  val labelGrains = Label( context.labelGrains )
  val labelButtonGrains = new LabelButton[List[Grain]]:
    text = s"${recipe.value.grains.map(_.name).mkString(", ")}"
    value = recipe.value.grains
    buttonAction = () => {
      GrainsDialog(context, recipe.value.grains.toArray).showAndWait() match
        case Some(grains: Array[Grain]) => grains.toList
        case _ => recipe.value.grains
    }
  
  val labelHops = Label( context.labelHops )
  val labelButtonHops = new LabelButton[List[Hop]]:
    text = s"${recipe.value.hops.map(_.name).mkString(", ")}"
    value = recipe.value.hops
    buttonAction = () => {
      HopsDialog(context, recipe.value.hops.toArray).showAndWait() match
        case Some(hops: Array[Hop]) => hops.toList
        case _ => recipe.value.hops
    }

  val labelAdjuncts = Label( context.labelAdjuncts )
  val labelButtonAdjuncts = new LabelButton[List[Adjunct]]:
    text = s"${recipe.value.adjuncts.map(_.name).mkString(", ")}"
    value = recipe.value.adjuncts
    buttonAction = () => {
      AdjunctsDialog(context, recipe.value.adjuncts.toArray).showAndWait() match
        case Some(adjuncts: Array[Adjunct]) => adjuncts.toList
        case _ => recipe.value.adjuncts
    }

  val labelYeasts = Label( context.labelYeasts )
  val labelButtonYeasts = new LabelButton[List[Yeast]]:
    text = s"${recipe.value.yeasts.map(_.name).mkString(", ")}"
    value = recipe.value.yeasts
    buttonAction = () => {
      YeastsDialog(context, recipe.value.yeasts.toArray).showAndWait() match
        case Some(yeasts: Array[Yeast]) => yeasts.toList
        case _ => recipe.value.yeasts
    }

  val labelMashingTempRangeDuration = Label( context.labelMashingTempRangeDuration )
  val labelButtonMashingTempRangeDuration = new LabelButton[TempRangeDuration]:
    text = s"${recipe.value.mashingTempRangeDuration.tempRange.low} - ${recipe.value.mashingTempRangeDuration.tempRange.high}, " +
           s"${recipe.value.mashingTempRangeDuration.duration} ${recipe.value.mashingTempRangeDuration.unit.toString}"
    value = recipe.value.mashingTempRangeDuration
    buttonAction = () => {
      TempRangeDurationDialog(context, recipe.value.mashingTempRangeDuration).showAndWait() match
        case Some(tempRangeDuration: TempRangeDuration) => tempRangeDuration
        case _ => recipe.value.mashingTempRangeDuration
    }

  val labelPotentialMashExtract = Label( context.labelPotentialMashExtract )
  val textFieldPotentialMashExtract = new DoubleTextField:
    text = recipe.value.potentialMashExtract.toString

  val labelBoilingTempRangeDuration = Label( context.labelBoilingTempRangeDuration )
  val labelButtonBoilingTempRangeDuration = new LabelButton[TempRangeDuration]:
    text = s"${recipe.value.boilingTempRangeDuration.tempRange.low} - ${recipe.value.boilingTempRangeDuration.tempRange.high}, " +
           s"${recipe.value.boilingTempRangeDuration.duration} ${recipe.value.boilingTempRangeDuration.unit.toString}"
    value = recipe.value.boilingTempRangeDuration
    buttonAction = () => {
      TempRangeDurationDialog(context, recipe.value.boilingTempRangeDuration).showAndWait() match
        case Some(tempRangeDuration: TempRangeDuration) => tempRangeDuration
        case _ => recipe.value.boilingTempRangeDuration
    }

  val labelCoolingTempRange = Label( context.labelCoolingTempRange )
  val rangeSliderCoolingTempRange = new RangeSlider(67, 73, recipe.value.coolingTempRange.low, recipe.value.coolingTempRange.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(1)

  val labelFermentingTempRangeDuration = Label( context.labelFermentingTempRangeDuration )
  val labelButtonFermentingTempRangeDuration = new LabelButton[TempRangeDuration]:
    text = s"${recipe.value.fermentingTempRangeDuration.tempRange.low} - ${recipe.value.fermentingTempRangeDuration.tempRange.high}, " +
           s"${recipe.value.fermentingTempRangeDuration.duration} ${recipe.value.fermentingTempRangeDuration.unit.toString}"
    value = recipe.value.fermentingTempRangeDuration
    buttonAction = () => {
      TempRangeDurationDialog(context, recipe.value.fermentingTempRangeDuration).showAndWait() match
        case Some(tempRangeDuration: TempRangeDuration) => tempRangeDuration
        case _ => recipe.value.fermentingTempRangeDuration
    }

  val labelPotentialFermentableExtract = Label( context.labelPotentialFermentableExtract )
  val textFieldPotentialFermentableExtract = new DoubleTextField:
    text = recipe.value.potentialFermentableExtract.toString

  val labelConditioningTempRangeDuration = Label( context.labelConditioningTempRangeDuration )
  val labelButtonConditioningTempRangeDuration = new LabelButton[TempRangeDuration]:
    text = s"${recipe.value.conditioningTempRangeDuration.tempRange.low} - ${recipe.value.conditioningTempRangeDuration.tempRange.high}, " +
           s"${recipe.value.conditioningTempRangeDuration.duration} ${recipe.value.conditioningTempRangeDuration.unit.toString}"
    value = recipe.value.conditioningTempRangeDuration
    buttonAction = () => {
      TempRangeDurationDialog(context, recipe.value.conditioningTempRangeDuration).showAndWait() match
        case Some(tempRangeDuration: TempRangeDuration) => tempRangeDuration
        case _ => recipe.value.conditioningTempRangeDuration
    }

  val labelKeggingTempRangeDuration = Label( context.labelKeggingTempRangeDuration )
  val labelButtonKeggingTempRangeDuration = new LabelButton[TempRangeDuration]:
    text = s"${recipe.value.keggingTempRangeDuration.tempRange.low} - ${recipe.value.keggingTempRangeDuration.tempRange.high}, " +
           s"${recipe.value.keggingTempRangeDuration.duration} ${recipe.value.keggingTempRangeDuration.unit.toString}"
    value = recipe.value.keggingTempRangeDuration
    buttonAction = () => {
      TempRangeDurationDialog(context, recipe.value.keggingTempRangeDuration).showAndWait() match
        case Some(tempRangeDuration: TempRangeDuration) => tempRangeDuration
        case _ => recipe.value.keggingTempRangeDuration
    }

  val labelPh = Label( context.labelPh )
  val textFieldPh = new DoubleTextField:
    text = recipe.value.pH.toString

  val labelOriginalGravity = Label( context.labelOriginalGravity )
  val rangeSliderOriginalGravity = new RangeSlider(1.000, 1.100, recipe.value.originalGravity.low, recipe.value.originalGravity.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(0.10)

  val labelFinalGravity = Label( context.labelFinalGravity )
  val rangeSliderFinalGravity = new RangeSlider(1.000, 1.050, recipe.value.finalGravity.low, recipe.value.finalGravity.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(0.10)

  val labelSrmColor = Label( context.labelSrmColor )
  val rangeSliderSrmColor = new RangeSlider(1, 80, recipe.value.srmColor.low, recipe.value.srmColor.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(10)

  val labelIbuBitterness = Label( context.labelIbuBitterness )
  val rangeSliderIbuBitterness = new RangeSlider(1, 80, recipe.value.ibuBitterness.low, recipe.value.ibuBitterness.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(10)

  val labelAlcoholByVolume = Label( context.labelAlcoholByVolume )
  val rangeSliderAlcoholByVolume = new RangeSlider(1.0, 12.0, recipe.value.alcoholByVolume.low, recipe.value.alcoholByVolume.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(1.0)

  val labelAlcoholByWeight = Label( context.labelAlcoholByWeight )
  val rangeSliderAlcoholByWeight = new RangeSlider(1.0, 12.0, recipe.value.alcoholByWeight.low, recipe.value.alcoholByWeight.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(1.0)

  val labelCalories = Label( context.labelCalories )
  val rangeSliderCalories = new RangeSlider(50, 250, recipe.value.calories.low, recipe.value.calories.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(50)

  val labelMashEfficiency = Label( context.labelMashEfficiency )
  val rangeSliderMashEfficiency = new RangeSlider(50, 100, recipe.value.mashEfficiency.low, recipe.value.mashEfficiency.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(10)

  val labelBrewhouseEfficiency = Label( context.labelBrewhouseEfficiency )
  val rangeSliderBrewhouseEfficiency = new RangeSlider(50, 100, recipe.value.brewhouseEfficiency.low, recipe.value.brewhouseEfficiency.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(10)

  val labelCreated = Label( context.labelCreated )
  val labelFieldCreated = Label( recipe.value.created )

  val controls = List[(Label, Node)](
    labelName -> textFieldName,
    labelStyle -> textFieldStyle,
    labelWater -> textFieldWater,
    labelVolume -> labelButtonVolume,
    labelGrains -> labelButtonGrains,
    labelHops -> labelButtonHops,
    labelAdjuncts -> labelButtonAdjuncts,
    labelYeasts -> labelButtonYeasts,
    labelMashingTempRangeDuration -> labelButtonMashingTempRangeDuration,
    labelPotentialMashExtract -> textFieldPotentialMashExtract,
    labelBoilingTempRangeDuration -> labelButtonBoilingTempRangeDuration,
    labelCoolingTempRange -> rangeSliderCoolingTempRange,
    labelFermentingTempRangeDuration -> labelButtonFermentingTempRangeDuration,
    labelPotentialFermentableExtract -> textFieldPotentialFermentableExtract,
    labelConditioningTempRangeDuration -> labelButtonConditioningTempRangeDuration,
    labelKeggingTempRangeDuration -> labelButtonKeggingTempRangeDuration,
    labelPh -> textFieldPh,
    labelOriginalGravity -> rangeSliderOriginalGravity,
    labelFinalGravity -> rangeSliderFinalGravity,
    labelSrmColor -> rangeSliderSrmColor,
    labelIbuBitterness -> rangeSliderIbuBitterness,
    labelAlcoholByVolume -> rangeSliderAlcoholByVolume,
    labelAlcoholByWeight -> rangeSliderAlcoholByWeight,
    labelCalories -> rangeSliderCalories,
    labelMashEfficiency -> rangeSliderMashEfficiency,
    labelBrewhouseEfficiency -> rangeSliderBrewhouseEfficiency,
    labelCreated -> labelFieldCreated
  )

  content = new VBox:
    children = List( ControlGrid(controls) )
  
  VBox.setVgrow(this, Priority.Always)