package objektwerks.ui.dialog

import scalafx.scene.control.Dialog

import objektwerks.Batch

final class BrewDialog extends Dialog[Batch]:
  initOwner(App.stage)
  title = context.windowTitle
