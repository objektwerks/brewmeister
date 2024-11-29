package objektwerks.ui.pane

import org.controlsfx.control.RangeSlider

import scalafx.Includes.*
import scalafx.geometry.Insets
import scalafx.scene.Node
import scalafx.scene.control.{Label, ScrollPane}
import scalafx.scene.layout.{Priority, VBox}

import objektwerks.*
import objektwerks.ui.Context
import objektwerks.ui.control.{ControlGrid, LabelButton, NonEmptyTextField}
import objektwerks.ui.control.DoubleTextField
import objektwerks.ui.dialog.{AdjunctsDialog, GrainsDialog, HopsDialog, TempRangeDurationDialog, VolumeDialog, YeastsDialog}

final class RecipePane(context: Context, recipe: Recipe) extends ScrollPane:
  padding = Insets(3)

  val labelName = Label( context.labelName )
  val textFieldName = new NonEmptyTextField:
    text = recipe.name

  val labelStyle = Label( context.labelStyle )
  val textFieldStyle = new NonEmptyTextField:
    text = recipe.style

  val labelWater = Label( context.labelWater )
  val textFieldWater = new NonEmptyTextField:
    text = recipe.water

  val labelVolume = Label( context.labelVolume )
  val labelButtonVolume = new LabelButton[Volume]:
    text = s"${recipe.volume.value} ${recipe.volume.unit.toString}"
    value = recipe.volume
    buttonAction = () => {
      VolumeDialog(context, recipe.volume).showAndWait() match
        case Some(volume: Volume) => volume
        case _ => recipe.volume
    }

  val labelGrains = Label( context.labelGrains )
  val labelButtonGrains = new LabelButton[List[Grain]]:
    text = s"${recipe.grains.map(_.name).mkString(", ")}"
    value = recipe.grains
    buttonAction = () => {
      GrainsDialog(context, recipe.grains.toArray).showAndWait() match
        case Some(grains: Array[Grain]) => grains.toList
        case _ => recipe.grains
    }
  
  val labelHops = Label( context.labelHops )
  val labelButtonHops = new LabelButton[List[Hop]]:
    text = s"${recipe.hops.map(_.name).mkString(", ")}"
    value = recipe.hops
    buttonAction = () => {
      HopsDialog(context, recipe.hops.toArray).showAndWait() match
        case Some(hops: Array[Hop]) => hops.toList
        case _ => recipe.hops
    }

  val labelAdjuncts = Label( context.labelAdjuncts )
  val labelButtonAdjuncts = new LabelButton[List[Adjunct]]:
    text = s"${recipe.adjuncts.map(_.name).mkString(", ")}"
    value = recipe.adjuncts
    buttonAction = () => {
      AdjunctsDialog(context, recipe.adjuncts.toArray).showAndWait() match
        case Some(adjuncts: Array[Adjunct]) => adjuncts.toList
        case _ => recipe.adjuncts
    }

  val labelYeasts = Label( context.labelYeasts )
  val labelButtonYeasts = new LabelButton[List[Yeast]]:
    text = s"${recipe.yeasts.map(_.name).mkString(", ")}"
    value = recipe.yeasts
    buttonAction = () => {
      YeastsDialog(context, recipe.yeasts.toArray).showAndWait() match
        case Some(yeasts: Array[Yeast]) => yeasts.toList
        case _ => recipe.yeasts
    }

  val labelMashingTempRangeDuration = Label( context.labelMashingTempRangeDuration )
  val labelButtonMashingTempRangeDuration = new LabelButton[TempRangeDuration]:
    text = s"${recipe.mashingTempRangeDuration.tempRange.low} - ${recipe.mashingTempRangeDuration.tempRange.high}, " +
           s"${recipe.mashingTempRangeDuration.duration} ${recipe.mashingTempRangeDuration.unit.toString}"
    value = recipe.mashingTempRangeDuration
    buttonAction = () => {
      TempRangeDurationDialog(context, recipe.mashingTempRangeDuration).showAndWait() match
        case Some(tempRangeDuration: TempRangeDuration) => tempRangeDuration
        case _ => recipe.mashingTempRangeDuration
    }

  val labelPotentialMashExtract = Label( context.labelPotentialMashExtract )
  val textFieldPotentialMashExtract = new DoubleTextField:
    text = recipe.potentialMashExtract.toString

  val labelBoilingTempRangeDuration = Label( context.labelBoilingTempRangeDuration )
  val labelButtonBoilingTempRangeDuration = new LabelButton[TempRangeDuration]:
    text = s"${recipe.boilingTempRangeDuration.tempRange.low} - ${recipe.boilingTempRangeDuration.tempRange.high}, " +
           s"${recipe.boilingTempRangeDuration.duration} ${recipe.boilingTempRangeDuration.unit.toString}"
    value = recipe.boilingTempRangeDuration
    buttonAction = () => {
      TempRangeDurationDialog(context, recipe.boilingTempRangeDuration).showAndWait() match
        case Some(tempRangeDuration: TempRangeDuration) => tempRangeDuration
        case _ => recipe.boilingTempRangeDuration
    }

  val labelCoolingTempRange = Label( context.labelCoolingTempRange )
  val rangeSliderCoolingTempRange = new RangeSlider(67, 73, recipe.coolingTempRange.low, recipe.coolingTempRange.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(1)

  val labelFermentingTempRangeDuration = Label( context.labelFermentingTempRangeDuration )
  val labelButtonFermentingTempRangeDuration = new LabelButton[TempRangeDuration]:
    text = s"${recipe.fermentingTempRangeDuration.tempRange.low} - ${recipe.fermentingTempRangeDuration.tempRange.high}, " +
           s"${recipe.fermentingTempRangeDuration.duration} ${recipe.fermentingTempRangeDuration.unit.toString}"
    value = recipe.fermentingTempRangeDuration
    buttonAction = () => {
      TempRangeDurationDialog(context, recipe.fermentingTempRangeDuration).showAndWait() match
        case Some(tempRangeDuration: TempRangeDuration) => tempRangeDuration
        case _ => recipe.fermentingTempRangeDuration
    }

  val labelPotentialFermentableExtract = Label( context.labelPotentialFermentableExtract )
  val textFieldPotentialFermentableExtract = new DoubleTextField:
    text = recipe.potentialFermentableExtract.toString

  val labelConditioningTempRangeDuration = Label( context.labelConditioningTempRangeDuration )
  val labelButtonConditioningTempRangeDuration = new LabelButton[TempRangeDuration]:
    text = s"${recipe.conditioningTempRangeDuration.tempRange.low} - ${recipe.conditioningTempRangeDuration.tempRange.high}, " +
           s"${recipe.conditioningTempRangeDuration.duration} ${recipe.conditioningTempRangeDuration.unit.toString}"
    value = recipe.conditioningTempRangeDuration
    buttonAction = () => {
      TempRangeDurationDialog(context, recipe.conditioningTempRangeDuration).showAndWait() match
        case Some(tempRangeDuration: TempRangeDuration) => tempRangeDuration
        case _ => recipe.conditioningTempRangeDuration
    }

  val labelKeggingTempRangeDuration = Label( context.labelKeggingTempRangeDuration )
  val labelButtonKeggingTempRangeDuration = new LabelButton[TempRangeDuration]:
    text = s"${recipe.keggingTempRangeDuration.tempRange.low} - ${recipe.keggingTempRangeDuration.tempRange.high}, " +
           s"${recipe.keggingTempRangeDuration.duration} ${recipe.keggingTempRangeDuration.unit.toString}"
    value = recipe.keggingTempRangeDuration
    buttonAction = () => {
      TempRangeDurationDialog(context, recipe.keggingTempRangeDuration).showAndWait() match
        case Some(tempRangeDuration: TempRangeDuration) => tempRangeDuration
        case _ => recipe.keggingTempRangeDuration
    }

  val labelPh = Label( context.labelPh )
  val textFieldPh = new DoubleTextField:
    text = recipe.pH.toString

  val labelOriginalGravity = Label( context.labelOriginalGravity )
  val rangeSliderOriginalGravity = new RangeSlider(1.000, 1.100, recipe.originalGravity.low, recipe.originalGravity.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(0.10)

  val labelFinalGravity = Label( context.labelFinalGravity )
  val rangeSliderFinalGravity = new RangeSlider(1.000, 1.050, recipe.finalGravity.low, recipe.finalGravity.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(0.10)

  val labelSrmColor = Label( context.labelSrmColor )
  val rangeSliderSrmColor = new RangeSlider(1, 80, recipe.srmColor.low, recipe.srmColor.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(10)

  val labelIbuBitterness = Label( context.labelIbuBitterness )
  val rangeSliderIbuBitterness = new RangeSlider(1, 80, recipe.ibuBitterness.low, recipe.ibuBitterness.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(10)

  val labelAlcoholByVolume = Label( context.labelAlcoholByVolume )
  val rangeSliderAlcoholByVolume = new RangeSlider(1.0, 12.0, recipe.alcoholByVolume.low, recipe.alcoholByVolume.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(1.0)

  val labelAlcoholByWeight = Label( context.labelAlcoholByWeight )
  val rangeSliderAlcoholByWeight = new RangeSlider(1.0, 12.0, recipe.alcoholByWeight.low, recipe.alcoholByWeight.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(1.0)

  val labelCalories = Label( context.labelCalories )
  val rangeSliderCalories = new RangeSlider(50, 250, recipe.calories.low, recipe.calories.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(50)

  val labelMashEfficiency = Label( context.labelMashEfficiency )
  val rangeSliderMashEfficiency = new RangeSlider(50, 100, recipe.mashEfficiency.low, recipe.mashEfficiency.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(10)

  val labelBrewhouseEfficiency = Label( context.labelBrewhouseEfficiency )
  val rangeSliderBrewhouseEfficiency = new RangeSlider(50, 100, recipe.brewhouseEfficiency.low, recipe.brewhouseEfficiency.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(10)

  val labelCreated = Label( context.labelCreated )
  val labelFieldCreated = Label( recipe.created )

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