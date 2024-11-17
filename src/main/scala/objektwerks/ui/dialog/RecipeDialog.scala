package objektwerks.ui.dialog

import org.controlsfx.control.RangeSlider

import scalafx.Includes.*
import scalafx.scene.control.{ButtonType, Dialog, Label}
import scalafx.scene.control.ButtonBar.ButtonData
import scalafx.scene.layout.Region
import scalafx.scene.Node

import objektwerks.*
import objektwerks.ui.{App, Context}
import objektwerks.ui.control.{ControlGrid, NonEmptyTextField}
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

  val labelGrains = Label( context.labelGrains )
  
  val labelHops = Label( context.labelHops )

  val labelAdjuncts = Label( context.labelAdjuncts )

  val labelYeasts = Label( context.labelYeasts )

  val labelMashingTempRangeDuration = Label( context.labelMashingTempRangeDuration )

  val labelPotentialMashExtract = Label( context.labelPotentialMashExtract )
  val textFieldPotentialMashExtract = new DoubleTextField:
    text = recipe.potentialMashExtract.toString

  val labelBoilingTempRangeDuration = Label( context.labelBoilingTempRangeDuration )

  val labelCoolingTempRange = Label( context.labelCoolingTempRange )

  val labelFermentingTempRangeDuration = Label( context.labelFermentingTempRangeDuration )

  val labelPotentialFermentableExtract = Label( context.labelPotentialFermentableExtract )
  val textFieldPotentialFermentableExtract = new DoubleTextField:
    text = recipe.potentialFermentableExtract.toString

  val labelConditioningTempRangeDuration = Label( context.labelConditioningTempRangeDuration )

  val labelKeggingTempRangeDuration = Label( context.labelKeggingTempRangeDuration )

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
    labelPotentialMashExtract -> textFieldPotentialMashExtract,
    labelPotentialFermentableExtract -> textFieldPotentialFermentableExtract,
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
        pH = textFieldPh.double.format,
        originalGravity = DoubleRange( rangeSliderOriginalGravity.getLowValue, rangeSliderOriginalGravity.getHighValue ),
        finalGravity = DoubleRange( rangeSliderFinalGravity.getLowValue, rangeSliderFinalGravity.getHighValue ),
        srmColor = IntRange( rangeSliderSrmColor.getLowValue.toInt, rangeSliderSrmColor.getHighValue.toInt ),
        ibuBitterness = IntRange( rangeSliderIbuBitterness.getLowValue.toInt, rangeSliderIbuBitterness.getHighValue.toInt ),
        alcoholByVolume = DoubleRange( rangeSliderAlcoholByVolume.getLowValue, rangeSliderAlcoholByVolume.getHighValue ),
        alcoholByWeight = DoubleRange( rangeSliderAlcoholByWeight.getLowValue, rangeSliderAlcoholByWeight.getHighValue ),
        calories = IntRange( rangeSliderCalories.getLowValue.toInt, rangeSliderCalories.getHighValue.toInt ),
        mashEfficiency = IntRange( rangeSliderMashEfficiency.getLowValue.toInt, rangeSliderMashEfficiency.getHighValue.toInt ),

      )
    else null