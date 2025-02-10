package objektwerks.ui.pane

import scalafx.collections.ObservableBuffer
import scalafx.geometry.Insets
import scalafx.scene.control.{Label, ListView}
import scalafx.scene.layout.{Priority, VBox}

import objektwerks.ui.{Context, Model}

final class LogPane(context: Context, model: Model) extends VBox:
  padding = Insets(3)

  model.selectedBatch.onChange { (_, _, selectedBatch) =>
    bind(selectedBatch.log)
  }  

  val labelLog = Label( context.labelLog )
  val listViewLog = ListView( model.selectedBatch.value.log )

  def bind(log: List[String]): Unit =
    listViewLog.items = ObservableBuffer.from(log)

  children = List(listViewLog)
  VBox.setVgrow(this, Priority.Always)