package objektwerks.ui.dialog

import org.controlsfx.control.RangeSlider

import scalafx.Includes.*
import scalafx.scene.control.{ButtonType, Dialog, Label}
import scalafx.scene.control.ButtonBar.ButtonData
import scalafx.scene.layout.Region
import scalafx.scene.Node

import objektwerks.Recipe
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
  val rangeSliderOriginalGravity = new RangeSlider(1.000, 1.100, 1.050, 1.070):
     setShowTickMarks(true)
     setShowTickLabels(true)
     setBlockIncrement(0.10)

  val labelFinalGravity = Label( context.labelFinalGravity )
  val rangeSliderFinalGravity = new RangeSlider(1.000, 1.050, 1.010, 1.020):
     setShowTickMarks(true)
     setShowTickLabels(true)
     setBlockIncrement(0.10)

  val labelSrmColor = Label( context.labelSrmColor )

  val labelIbuBitterness = Label( context.labelIbuBitterness )

  val labelAlcoholByVolume = Label( context.labelAlcoholByVolume )

  val labelAlcoholByWeight = Label( context.labelAlcoholByWeight )

  val labelCalories = Label( context.labelCalories )

  val labelMashEfficiency = Label( context.labelMashEfficiency )

  val labelBrewhouseEfficiency = Label( context.labelBrewhouseEfficiency )

  val labelCreated = Label( context.labelCreated )
  val createdTextField = new NonEmptyTextField:
    text = recipe.created

  val controls = List[(Label, Node)](
    labelName -> textFieldName,
    labelStyle -> textFieldStyle,
    labelWater -> textFieldWater,
    labelPotentialMashExtract -> textFieldPotentialMashExtract,
    labelPotentialFermentableExtract -> textFieldPotentialFermentableExtract,
    labelPh -> textFieldPh,
    labelOriginalGravity -> rangeSliderOriginalGravity,
    labelFinalGravity -> rangeSliderFinalGravity,
    labelCreated -> createdTextField
  )

  dialogPane().content = ControlGrid(controls)

  val saveButtonType = new ButtonType(context.buttonSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      recipe // TODO
    else null