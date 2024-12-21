package objektwerks.ui.dialog

import org.controlsfx.control.Rating

import scalafx.Includes.*
import scalafx.scene.Node
import scalafx.scene.control.{ButtonType, Dialog, Label}
import scalafx.scene.control.ButtonBar.ButtonData

import objektwerks.{Batch, Brewer, Listener, Recipe}
import objektwerks.ui.{App, Context}
import objektwerks.ui.control.{ControlGrid, DoubleTextField, IntTextField}
import objektwerks.formatGravity

final class BrewDialog(context: Context, recipe: Recipe) extends Dialog[Batch]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogBrew

  val labelRecipe = Label( context.labelRecipe )
  val textRecipe = Label( recipe.name )

  val labelMashingTemmp = Label( context.labelMashingTemp )
  val textFieldMashingTemp = new IntTextField:
    text = 150.toString

  val labelPh = Label( context.labelPh )
  val textFieldPh = new DoubleTextField:
    text = 5.6.toString

  val labelActualMashExtract = Label( context.labelActualMashExtract )
  val textFieldActualMashExtract = new DoubleTextField:
    text = 4.5.toString

  val labelBoilingTemmp = Label( context.labelBoilingTemp )
  val textFieldBoilingTemp = new IntTextField:
    text = 150.toString

  val labelCoolingTemmp = Label( context.labelCoolingTemp )
  val textFieldCoolingTemp = new IntTextField:
    text = 72.toString

  val labelOriginalGravity = Label( context.labelOriginalGravity )
  val textFieldOriginalGravity = new DoubleTextField:
    text = 1.060.toString

  val labelFermentingTemmp = Label( context.labelFermentingTemp )
  val textFieldFermentingTemp = new IntTextField:
    text = 72.toString

  val labelFinalGravity = Label( context.labelFinalGravity )
  val textFieldFinalGravity = new DoubleTextField:
    text = 1.012.toString

  val labelConditioningTemmp = Label( context.labelConditioningTemp )
  val textFieldConditioningTemp = new IntTextField:
    text = 72.toString

  val labelSrmColor = Label( context.labelSrmColor )
  val textFieldSrmColor = new IntTextField:
    text = 9.toString

  val labelKeggingTemmp = Label( context.labelKeggingTemp )
  val textFieldKeggingTemp = new IntTextField:
    text = 72.toString

  val labelAppearance = Label( context.labelAppearance )
  val ratingAppearance = Rating(2, 2)

  val labelAroma = Label( context.labelAroma )
  val ratingAroma = Rating(2, 2)

  val labelTaste = Label( context.labelTaste )
  val ratingTaste = Rating(2, 2)

  val labelActualFermentableExtract = Label( context.labelActualFermentableExtract )
  val textFieldActualFermentableExtract = new DoubleTextField:
    text = 4.5.toString

  val controls = List[(Label, Node)](
    labelRecipe -> textRecipe,
    labelMashingTemmp -> textFieldMashingTemp,
    labelPh -> textFieldPh,
    labelActualMashExtract -> textFieldActualMashExtract,
    labelBoilingTemmp -> textFieldBoilingTemp,
    labelCoolingTemmp -> textFieldCoolingTemp,
    labelOriginalGravity -> textFieldOriginalGravity,
    labelFermentingTemmp -> textFieldFermentingTemp,
    labelFinalGravity -> textFieldFinalGravity,
    labelConditioningTemmp -> textFieldConditioningTemp,
    labelSrmColor -> textFieldSrmColor,
    labelKeggingTemmp -> textFieldKeggingTemp,
    labelAppearance -> ratingAppearance,
    labelAroma -> ratingAroma,
    labelTaste -> ratingTaste,
    labelActualFermentableExtract -> textFieldActualFermentableExtract
  )

  dialogPane().content = ControlGrid(controls)

  val brewButtonType = ButtonType(context.buttonBrew, ButtonData.OKDone)
  dialogPane().buttonTypes = List(brewButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == brewButtonType then
      val listener = Listener()
      Brewer.brew(
        listener = listener,
        recipe = recipe,
        mashingTemp = textFieldMashingTemp.int,
        pH = textFieldPh.double,
        actualMashExtract = textFieldActualMashExtract.double,
        boilingTemp = textFieldBoilingTemp.int,
        coolingTemp = textFieldCoolingTemp.int,
        originalGravity = textFieldOriginalGravity.double.formatGravity,
        fermentingTemp = textFieldFermentingTemp.int,
        finalGravity = textFieldFinalGravity.double.formatGravity,
        conditioningTemp = textFieldConditioningTemp.int,
        srmColor = textFieldSrmColor.int,
        keggingTemp = textFieldKeggingTemp.int,
        appearance = ratingAppearance.getRating.toInt + 1,
        aroma = ratingAroma.getRating.toInt + 1,
        taste = ratingTaste.getRating.toInt + 1,
        actualFermentableExtract = textFieldActualFermentableExtract.double
      )
      listener.batch
    else null