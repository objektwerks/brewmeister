package objektwerks.ui.dialog

import scalafx.scene.control.TextInputDialog

import objektwerks.ui.{App, Context}

final class RecipeNameDialog(context: Context,
                             defaultValue: String) extends TextInputDialog(defaultValue):
  initOwner(App.stage)
  title = context.textinputDialogTitle
  headerText = context.textinputDialogHeaderText
  contentText = context.textinputDialogContentText