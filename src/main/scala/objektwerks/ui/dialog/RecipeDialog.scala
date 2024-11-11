package objektwerks.ui.dialog

import scalafx.scene.control.Dialog

import objektwerks.Recipe
import objektwerks.ui.{App, Context}

final class RecipeDialog(context: Context, recipe: Recipe) extends Dialog[Recipe]:
  initOwner(App.stage)
  title = context.windowTitle
