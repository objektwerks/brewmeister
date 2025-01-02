package objektwerks.ui.dialog

import scalafx.scene.control.TextInputDialog

import objektwerks.ui.{App, Context}

final class RecipeNameDialog(context: Context,
                             defaultValue: String) extends TextInputDialog(defaultValue):
  initOwner(App.stage)
  title = context.recipenameDialogTitle
  headerText = context.recipenameDialogHeaderText
  contentText = context.recipenameDialogContentText