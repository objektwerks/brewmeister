package objektwerks.ui.dialog

import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType

import objektwerks.ui.{App, Context}

final class RemoveConfirmationDialog(context: Context) extends Alert(AlertType.Confirmation):
  initOwner(App.stage)
  title = context.removeDialogTitle
  headerText = context.removeDialogHeaderText
  contentText = context.removeDialogContentText