package objektwerks.ui.dialog

import scalafx.Includes.*
import scalafx.scene.Node
import scalafx.scene.control.{ButtonType, Dialog, Label}
import scalafx.scene.control.ButtonBar.ButtonData

import objektwerks.{Batch, Listener, Recipe}
import objektwerks.ui.{App, Context}
import objektwerks.ui.control.ControlGrid

final class BrewDialog(context: Context, listener: Listener, recipe: Recipe) extends Dialog[Batch]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogBrew

  println(listener) // REMOVE
  println(recipe) // REMOVE

  val labelRecipe = Label( context.labelRecipe )
  val textRecipe = Label( recipe.name )

  val controls = List[(Label, Node)](
    labelRecipe -> textRecipe
  )

  dialogPane().content = ControlGrid(controls)

  val saveButtonType = new ButtonType(context.tooltipSave, ButtonData.OKDone)
  dialogPane().buttonTypes = List(saveButtonType, ButtonType.Cancel)

  resultConverter = dialogButton =>
    if dialogButton == saveButtonType then
      null // TODO
    else null