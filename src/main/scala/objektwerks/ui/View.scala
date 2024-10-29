package objektwerks.ui

import scalafx.scene.Scene
import scalafx.scene.layout.VBox

object View:
  val width = 700
  val height = 600

  val rootBox = new VBox:
    prefWidth = View.width
    prefHeight = View.height
    children = List( )

  val scene = new Scene:
    root = rootBox