package objektwerks.ui.dialog

import scalafx.Includes.*
import scalafx.scene.control.{ButtonType, Dialog, Label}
import scalafx.scene.control.ButtonBar.ButtonData
import scalafx.scene.layout.Region

import objektwerks.Recipe
import objektwerks.ui.{App, Context}
import objektwerks.ui.control.{ControlGrid, NonEmptyTextField}
import scalafx.scene.Node

final class RecipeDialog(context: Context, recipe: Recipe) extends Dialog[Recipe]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogRecipe

  val labelName = Label( context.labelName )
  val nameTextField = new NonEmptyTextField:
    text = recipe.name

  val labelStyle = Label( context.labelStyle )
  val styleTextField = new NonEmptyTextField:
    text = recipe.style

  val labelWater = Label( context.labelWater )
  val waterTextField = new NonEmptyTextField:
    text = recipe.water

  val labelVolume = Label( context.labelVolume )

  val labelGrains = Label( context.labelGrains )
  
  val labelHops = Label( context.labelHops )

  val labelAdjuncts = Label ( context.labelAdjuncts )

  val labelYeasts = Label( context.labelYeasts )

  val labelMashingTempDuration = Label( context.labelMashingTempDuration )

  val labelPotentialMashExtract = Label( context.labelPotentialMashExtract )
  
  boilingTempDuration = 
  coolingTempRange = 
  fermentingTempDuration = 
  potentialFermentableExtract = 
  conditioningTempDuration = 
  keggingTempDuration = 

  val controls = List[(Label, Node)](
    labelName -> nameTextField,
    labelStyle -> styleTextField,
    labelWater -> waterTextField
  )

  dialogPane().content = ControlGrid(controls)

  val saveButtonType = new ButtonType(context.buttonSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      recipe // TODO
    else null