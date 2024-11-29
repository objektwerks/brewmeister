package objektwerks.ui

import scalafx.geometry.Orientation
import scalafx.scene.Scene
import scalafx.scene.control.SplitPane
import scalafx.scene.layout.{Priority, VBox}

import pane.{LeftPane, RightPane}

final class View(context: Context, model: Model):
  private val splitPane = new SplitPane:
    orientation = Orientation.Horizontal
    items.addAll(LeftPane(context, model), RightPane(context, model))

  splitPane.setDividerPositions(0.50, 0.50)
  VBox.setVgrow(splitPane, Priority.Always)

  private val rootBox = new VBox:
    prefWidth = context.windowWidth
    prefHeight = context.windowHeight
    children = List(splitPane)

  val scene = new Scene:
    root = rootBox
    stylesheets = List("/style.css")