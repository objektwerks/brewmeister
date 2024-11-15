package objektwerks.ui.dialog

import scalafx.Includes.*
import scalafx.scene.control.{ButtonType, Dialog}
import scalafx.scene.control.ButtonBar.ButtonData
import scalafx.scene.layout.Region

import objektwerks.Recipe
import objektwerks.ui.{App, Context}
import objektwerks.ui.control.NonEmptyTextField

final class RecipeDialog(context: Context, recipe: Recipe) extends Dialog[Recipe]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogRecipe

  val nameTextField = new NonEmptyTextField:
    text = recipe.name

  val styleTextField = new NonEmptyTextField:
    text = recipe.style

  val waterTextField = new NonEmptyTextField:
    text = recipe.water

  val controls = List[(String, Region)](
    context.labelName -> nameTextField,
    context.labelStyle -> styleTextField,
    context.labelWater -> waterTextField
  )

  val saveButtonType = new ButtonType(context.buttonSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      recipe // TODO
    else null