package objektwerks.ui.dialog

import scalafx.Includes.*
import scalafx.scene.control.{ButtonType, Dialog}
import scalafx.scene.control.ButtonBar.ButtonData

import objektwerks.Recipe
import objektwerks.ui.{App, Context}

final class RecipeDialog(context: Context, recipe: Recipe) extends Dialog[Recipe]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogRecipe

  val saveButtonType = new ButtonType(context.buttonSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)