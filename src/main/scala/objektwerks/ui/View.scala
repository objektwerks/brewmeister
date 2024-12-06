package objektwerks.ui

import scalafx.geometry.Orientation
import scalafx.scene.Scene
import scalafx.scene.control.SplitPane
import scalafx.scene.layout.{Priority, VBox}

import pane.{LeftPane, RightPane}

final class View(context: Context, model: Model):
  val menu = Menu(context)

  val splitPane = new SplitPane:
    orientation = Orientation.Horizontal
    items.addAll(LeftPane(context, model), RightPane(context, model))
  splitPane.setDividerPositions(0.40, 0.60)
  VBox.setVgrow(splitPane, Priority.Always)

  val vboxRoot = new VBox:
    prefWidth = context.windowWidth
    prefHeight = context.windowHeight
    children = List(menu, splitPane)

  val scene = new Scene:
    root = vboxRoot
    stylesheets = List("/style.css")