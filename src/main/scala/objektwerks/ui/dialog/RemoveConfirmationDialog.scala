package objektwerks.ui.dialog

import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType

import objektwerks.ui.App

final class RemoveConfirmationDialog() extends Alert(AlertType.Confirmation):
  initOwner(App.stage)
  title = "Remove Confirmation Dialog"
  headerText = "Confirm Remove"
  contentText = "Select Ok to confirm, Cancel to cancel."