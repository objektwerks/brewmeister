package objektwerks.ui.dialog

import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType

import objektwerks.ui.App

final class RemoveConfirmationDialog() extends Alert(AlertType.Confirmation):
  initOwner(App.stage)