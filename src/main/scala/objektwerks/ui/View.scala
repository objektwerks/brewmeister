package objektwerks.ui

import scalafx.scene.Scene
import scalafx.scene.layout.VBox

final class View(context: Context):
  val rootBox = new VBox:
    prefWidth = context.windowWidth
    prefHeight = context.windowHeight
    children = List()

  val scene = new Scene:
    root = rootBox