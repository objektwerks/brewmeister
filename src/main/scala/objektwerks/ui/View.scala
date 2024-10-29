package objektwerks.ui

import scalafx.scene.Scene
import scalafx.scene.layout.VBox

final class View(context: Context):
  val width = context.windowWidth
  val height = context.windowHeight

  val rootBox = new VBox:
    prefWidth = View.width
    prefHeight = View.height
    children = List( )

  val scene = new Scene:
    root = rootBox