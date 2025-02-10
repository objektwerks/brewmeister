package objektwerks.ui.pane

import scalafx.collections.ObservableBuffer
import scalafx.geometry.Insets
import scalafx.scene.control.{Label, ListView}
import scalafx.scene.layout.VBox

import objektwerks.ui.{Context, Model}

final class LogPane(context: Context, model: Model) extends VBox:
  padding = Insets(3)

  // Model
  model.selectedBatch.onChange { (_, _, selectedBatch) =>
    bind(selectedBatch.log)
  }  

  // Controls
  val labelLog = Label( context.dialogLog )
  val listViewLog = ListView( model.selectedBatch.value.log )

  // Bind
  def bind(log: List[String]): Unit =
    listViewLog.items = ObservableBuffer.from(log)
