package objektwerks.ui.pane

import scalafx.scene.control.Label
import scalafx.scene.layout.{Priority, VBox}

import objektwerks.ui.{Context, Model}

final class BatchPane(context: Context, model: Model) extends VBox:
  println(context)

  // Model
  model.selectedBatch.onChange { (_, _, selectedBatch) =>
    // TODO - bind selected batch to this pane!
  }

  // Controls
  val labelRecipe = Label( context.labelRecipe )
  val textRecipe = Label( model.selectedBatch.value.recipe )

  val labelStyle = Label( context.labelStyle )
  val textFieldStyle = Label( model.selectedBatch.value.style )

  VBox.setVgrow(this, Priority.Always)