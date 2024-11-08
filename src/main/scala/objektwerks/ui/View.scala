package objektwerks.ui

import scalafx.geometry.Orientation
import scalafx.scene.Scene
import scalafx.scene.control.SplitPane
import scalafx.scene.layout.{Priority, VBox}

import scala.annotation.nowarn

import pane.{LeftPane, RightPane}

final class View(context: Context):
  @nowarn
  val splitPane = new SplitPane:
    orientation = Orientation.Horizontal
    items.addAll(LeftPane(context), RightPane())

  splitPane.setDividerPositions(0.20, 0.80)
  VBox.setVgrow(splitPane, Priority.Always)

  val rootBox = new VBox:
    prefWidth = context.windowWidth
    prefHeight = context.windowHeight
    children = List(splitPane)

  val scene = new Scene:
    root = rootBox