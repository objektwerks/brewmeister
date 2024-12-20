package objektwerks.ui.pane

import scalafx.scene.Node
import scalafx.scene.control.{Label, ScrollPane}
import scalafx.scene.layout.{Priority, VBox}

import objektwerks.ui.{Context, Model}
import objektwerks.ui.control.ControlGrid

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
  val textStyle = Label( model.selectedBatch.value.style )

  val controls = List[(Label, Node)](
    labelRecipe -> textRecipe,
    labelStyle -> textStyle
  )

  val scrollPaneControls = new ScrollPane:
    content = ControlGrid(controls)

  VBox.setVgrow(this, Priority.Always)