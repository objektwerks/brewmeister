package objektwerks.ui.dialog

import scalafx.Includes.*
import scalafx.scene.Node
import scalafx.scene.control.{ButtonType, Dialog, Label}

import objektwerks.ui.{App, Context, Model}
import objektwerks.ui.control.ControlGrid

final class ProcessDialog(context: Context, model: Model) extends Dialog[Unit]:
  initOwner(App.stage)
  title = context.windowTitle
  headerText = context.dialogProcess

  val process = model.selectedBatch.value.process

  val labelSanitizingStarted = Label( context.labelSanitizingStarted )
  val textSanitizingStarted = Label( process.sanitizingStarted )

  val labelSanitizingCompleted = Label( context.labelSanitizingCompleted )
  val textSanitizingCompleted = Label( process.sanitizingCompleted )

  val labelPreparingStarted = Label( context.labelPreparingStarted )
  val textPreparingStarted = Label( process.preparingStarted )

  val labelPreparingCompleted = Label( context.labelPreparingCompleted )
  val textPreparingCompleted = Label( process.preparingCompleted )

  val labelMaltingStarted = Label( context.labelMaltingStarted )
  val textMaltingStarted = Label( process.maltingStarted )

  val labelMaltingCompleted = Label( context.labelMaltingCompleted )
  val textMaltingCompleted = Label( process.maltingCompleted )

  val labelMillingStarted = Label( context.labelMillingStarted )
  val textMillingStarted = Label( process.millingStarted )

  val labelMillingCompleted = Label( context.labelMillingCompleted )
  val textMillingCompleted = Label( process.millingCompleted )

  val controls = List[(Label, Node)](
    labelSanitizingStarted -> textSanitizingStarted,
    labelSanitizingCompleted -> textSanitizingCompleted,
    labelPreparingStarted -> textPreparingStarted,
    labelPreparingCompleted -> textPreparingCompleted,
    labelMaltingStarted -> textMaltingStarted,
    labelMaltingCompleted -> textMaltingCompleted,
    labelMillingStarted -> textMillingStarted,
    labelMillingCompleted -> textMillingCompleted
  )

  dialogPane().content = ControlGrid(controls)

  dialogPane().buttonTypes = List(ButtonType.Close)