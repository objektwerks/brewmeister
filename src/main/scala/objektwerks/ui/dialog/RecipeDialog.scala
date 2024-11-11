package objektwerks.ui.dialog

import scalafx.scene.control.Dialog

import objektwerks.Recipe
import objektwerks.ui.App

final class RecipeDialog extends Dialog[Recipe]:
  initOwner(App.stage)
