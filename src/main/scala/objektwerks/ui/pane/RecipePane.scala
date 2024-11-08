package objektwerks.ui.pane

import scalafx.scene.control.{Tab, TabPane}

import objektwerks.ui.{Context, Model}

final class RecipePane(context: Context, model: Model) extends Tab:
  text = context.tabRecipe
  closable = false

  model.selectedRecipe.onChange { (_, oldRecipe, newRecipe) =>

  }