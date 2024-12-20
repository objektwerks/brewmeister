package objektwerks.ui.pane

import scalafx.geometry.Insets
import scalafx.scene.Node
import scalafx.scene.control.{Button, Label, ScrollPane}
import scalafx.scene.layout.{HBox, Priority, VBox}

import objektwerks.ui.{Context, Model}
import objektwerks.ui.control.ControlGrid

final class BatchPane(context: Context, model: Model) extends VBox:
  println(context)

  // Model
  model.selectedBatch.onChange { (_, _, selectedBatch) =>
    // TODO - bind selected batch to this pane!
  }

  // Methods
  def log(): Unit = ???

  def process(): Unit = ???

  // Controls
  val labelRecipe = Label( context.labelRecipe )
  val textRecipe = Label( model.selectedBatch.value.recipe )

  val labelStyle = Label( context.labelStyle )
  val textStyle = Label( model.selectedBatch.value.style )

  val labelStarted = Label( context.labelStarted )
  val textStarted = Label( model.selectedBatch.value.started )

  val labelCompleted = Label( context.labelCompleted )
  val textCompleted = Label( model.selectedBatch.value.completed )

  val controls = List[(Label, Node)](
    labelRecipe -> textRecipe,
    labelStyle -> textStyle,
    labelStarted -> textStarted,
    labelCompleted -> textCompleted
  )

  val scrollPaneControls = new ScrollPane:
    content = ControlGrid(controls)

  val buttonLog = new Button:
    graphic = context.imageViewSave // log
    tooltip = context.tooltipLog
    disable = true
    onAction = { _ => log() }

  val buttonProcess = new Button:
    graphic = context.imageViewSave // process
    tooltip = context.tooltipProcess
    disable = true
    onAction = { _ => process() }

  val buttonBar = new HBox:
    spacing = 6
    padding = Insets(3)
    children = List(buttonLog, buttonProcess)

  children = List(scrollPaneControls, buttonBar)

  VBox.setVgrow(this, Priority.Always)