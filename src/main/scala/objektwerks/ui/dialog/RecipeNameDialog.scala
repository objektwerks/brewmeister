package objektwerks.ui.dialog

import scalafx.scene.control.TextInputDialog

import objektwerks.ui.{App, Context}

final class RecipeNameDialog(context: Context,
                             defaultValue: String) extends TextInputDialog(defaultValue):
  initOwner(App.stage)
  title = "Text Input Dialog"
  headerText = "Look, a Text Input Dialog."
  contentText = "Please enter your name:"