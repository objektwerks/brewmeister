package objektwerks.ui

import scalafx.Includes.*

import scalafx.geometry.Orientation
import scalafx.scene.Scene
import javafx.scene.control.SplitPane
import scalafx.scene.layout.{Priority, VBox}

import pane.{LeftPane, RightPane}

final class View(context: Context):
  val splitPane = new SplitPane:
    orientation = Orientation.Horizontal
    items.addAll(LeftPane(), RightPane())

  splitPane.setDividerPositions(0.20, 0.80)
  VBox.setVgrow(splitPane, Priority.Always)

  val rootBox = new VBox:
    prefWidth = context.windowWidth
    prefHeight = context.windowHeight
    children = List(splitPane)

  val scene = new Scene:
    root = rootBox