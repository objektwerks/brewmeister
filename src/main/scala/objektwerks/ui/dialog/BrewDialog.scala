package objektwerks.ui.dialog

import org.controlsfx.control.Rating

import scalafx.Includes.*
import scalafx.scene.Node
import scalafx.scene.control.{ButtonType, Dialog, Label}
import scalafx.scene.control.ButtonBar.ButtonData

import objektwerks.{Batch, Listener, Recipe}
import objektwerks.ui.{App, Context}
import objektwerks.ui.control.{ControlGrid, DoubleTextField, IntTextField}

final class BrewDialog(context: Context, listener: Listener, recipe: Recipe) extends Dialog[Batch]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogBrew

  println(listener) // REMOVE

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
    labelActualFermentableExtract -> textFieldActualFermentableExtract
  )

  dialogPane().content = ControlGrid(controls)

  val saveButtonType = new ButtonType(context.tooltipSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      null // TODO
    else null