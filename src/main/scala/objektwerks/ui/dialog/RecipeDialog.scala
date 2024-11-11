package objektwerks.ui.dialog

import scalafx.scene.control.Dialog

import objektwerks.Recipe
import objektwerks.ui.{App, Context, Model}

final class RecipeDialog(context: Context, model: Model) extends Dialog[Recipe]:
  initOwner(App.stage)
  title = context.windowTitle
