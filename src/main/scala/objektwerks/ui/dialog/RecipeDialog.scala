package objektwerks.ui.dialog

import org.controlsfx.control.RangeSlider

import scalafx.Includes.*
import scalafx.scene.control.{ButtonType, Dialog, Label}
import scalafx.scene.control.ButtonBar.ButtonData
import scalafx.scene.Node

import objektwerks.*
import objektwerks.ui.{App, Context}
import objektwerks.ui.control.{ControlGrid, LabelButton, NonEmptyTextField}
import objektwerks.ui.control.DoubleTextField

final class RecipeDialog(context: Context, recipe: Recipe) extends Dialog[Recipe]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogRecipe

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
    labelText = s"${recipe.volume.value} ${recipe.volume.unit.toString}"
    buttonAction = VolumeDialog(context, recipe.volume).showAndWait() match
      case Some(volume: Volume) => () => volume
      case _ => () => recipe.volume

  val labelGrains = Label( context.labelGrains )
  
  val labelHops = Label( context.labelHops )

  val labelAdjuncts = Label( context.labelAdjuncts )

  val labelYeasts = Label( context.labelYeasts )

  val labelMashingTempRangeDuration = Label( context.labelMashingTempRangeDuration )
  val labelButtonMashingTempRangeDuration = new LabelButton[TempRangeDuration]:
    labelText = s"""${recipe.mashingTempRangeDuration.tempRange.low} - ${recipe.mashingTempRangeDuration.tempRange.high},
                    ${recipe.mashingTempRangeDuration.duration} ${recipe.mashingTempRangeDuration.unit.toString}"""
    buttonAction = TempRangeDurationDialog(context, recipe.mashingTempRangeDuration).showAndWait() match
      case Some(tempRangeDuration: TempRangeDuration) => () => tempRangeDuration
      case _ => () => recipe.mashingTempRangeDuration

  val labelPotentialMashExtract = Label( context.labelPotentialMashExtract )
  val textFieldPotentialMashExtract = new DoubleTextField:
    text = recipe.potentialMashExtract.toString

  val labelBoilingTempRangeDuration = Label( context.labelBoilingTempRangeDuration )
  val labelButtonBoilingTempRangeDuration = new LabelButton[TempRangeDuration]:
    labelText = s"""${recipe.boilingTempRangeDuration.tempRange.low} - ${recipe.boilingTempRangeDuration.tempRange.high},
                    ${recipe.boilingTempRangeDuration.duration} ${recipe.boilingTempRangeDuration.unit.toString}"""
    buttonAction = TempRangeDurationDialog(context, recipe.boilingTempRangeDuration).showAndWait() match
      case Some(tempRangeDuration: TempRangeDuration) => () => tempRangeDuration
      case _ => () => recipe.boilingTempRangeDuration

  val labelCoolingTempRange = Label( context.labelCoolingTempRange )
  val rangeSliderCoolingTempRange = new RangeSlider(67, 73, recipe.coolingTempRange.low, recipe.coolingTempRange.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(1)

  val labelFermentingTempRangeDuration = Label( context.labelFermentingTempRangeDuration )
  val labelButtonFermentingTempRangeDuration = new LabelButton[TempRangeDuration]:
    labelText = s"""${recipe.fermentingTempRangeDuration.tempRange.low} - ${recipe.fermentingTempRangeDuration.tempRange.high},
                    ${recipe.fermentingTempRangeDuration.duration} ${recipe.fermentingTempRangeDuration.unit.toString}"""
    buttonAction = TempRangeDurationDialog(context, recipe.fermentingTempRangeDuration).showAndWait() match
      case Some(tempRangeDuration: TempRangeDuration) => () => tempRangeDuration
      case _ => () => recipe.fermentingTempRangeDuration

  val labelPotentialFermentableExtract = Label( context.labelPotentialFermentableExtract )
  val textFieldPotentialFermentableExtract = new DoubleTextField:
    text = recipe.potentialFermentableExtract.toString

  val labelConditioningTempRangeDuration = Label( context.labelConditioningTempRangeDuration )
  val labelButtonConditioningTempRangeDuration = new LabelButton[TempRangeDuration]:
    labelText = s"""${recipe.conditioningTempRangeDuration.tempRange.low} - ${recipe.conditioningTempRangeDuration.tempRange.high},
                    ${recipe.conditioningTempRangeDuration.duration} ${recipe.conditioningTempRangeDuration.unit.toString}"""
    buttonAction = TempRangeDurationDialog(context, recipe.conditioningTempRangeDuration).showAndWait() match
      case Some(tempRangeDuration: TempRangeDuration) => () => tempRangeDuration
      case _ => () => recipe.conditioningTempRangeDuration

  val labelKeggingTempRangeDuration = Label( context.labelKeggingTempRangeDuration )
  val labelButtonKeggingTempRangeDuration = new LabelButton[TempRangeDuration]:
    labelText = s"""${recipe.keggingTempRangeDuration.tempRange.low} - ${recipe.keggingTempRangeDuration.tempRange.high},
                    ${recipe.keggingTempRangeDuration.duration} ${recipe.keggingTempRangeDuration.unit.toString}"""
    buttonAction = TempRangeDurationDialog(context, recipe.keggingTempRangeDuration).showAndWait() match
      case Some(tempRangeDuration: TempRangeDuration) => () => tempRangeDuration
      case _ => () => recipe.keggingTempRangeDuration

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
  val createdTextField = Label( recipe.created )

  val controls = List[(Label, Node)](
    labelName -> textFieldName,
    labelStyle -> textFieldStyle,
    labelWater -> textFieldWater,
    labelVolume -> labelButtonVolume,
    labelMashingTempRangeDuration -> labelButtonMashingTempRangeDuration,
    labelPotentialMashExtract -> textFieldPotentialMashExtract,
    labelBoilingTempRangeDuration -> labelButtonBoilingTempRangeDuration,
    labelPotentialFermentableExtract -> textFieldPotentialFermentableExtract,
    labelConditioningTempRangeDuration -> labelButtonConditioningTempRangeDuration,
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
    labelCreated -> createdTextField
  )

  dialogPane().content = ControlGrid(controls)

  val saveButtonType = new ButtonType(context.buttonSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      recipe.copy(
        name = textFieldName.string,
        style = textFieldStyle.string,
        water = textFieldWater.string,
        potentialMashExtract = textFieldPotentialMashExtract.double.format,
        potentialFermentableExtract = textFieldPotentialFermentableExtract.double.format,
        coolingTempRange = IntRange( rangeSliderCoolingTempRange.getLowValue.toInt, rangeSliderCoolingTempRange.getHighValue.toInt ),
        pH = textFieldPh.double.format,
        originalGravity = DoubleRange( rangeSliderOriginalGravity.getLowValue, rangeSliderOriginalGravity.getHighValue ),
        finalGravity = DoubleRange( rangeSliderFinalGravity.getLowValue, rangeSliderFinalGravity.getHighValue ),
        srmColor = IntRange( rangeSliderSrmColor.getLowValue.toInt, rangeSliderSrmColor.getHighValue.toInt ),
        ibuBitterness = IntRange( rangeSliderIbuBitterness.getLowValue.toInt, rangeSliderIbuBitterness.getHighValue.toInt ),
        alcoholByVolume = DoubleRange( rangeSliderAlcoholByVolume.getLowValue, rangeSliderAlcoholByVolume.getHighValue ),
        alcoholByWeight = DoubleRange( rangeSliderAlcoholByWeight.getLowValue, rangeSliderAlcoholByWeight.getHighValue ),
        calories = IntRange( rangeSliderCalories.getLowValue.toInt, rangeSliderCalories.getHighValue.toInt ),
        mashEfficiency = IntRange( rangeSliderMashEfficiency.getLowValue.toInt, rangeSliderMashEfficiency.getHighValue.toInt ),
        brewhouseEfficiency = IntRange( rangeSliderBrewhouseEfficiency.getLowValue.toInt, rangeSliderBrewhouseEfficiency.getHighValue.toInt )
      )
    else null