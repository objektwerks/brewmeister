package objektwerks.ui

import scalafx.Includes._
import scalafx.application.Platform
import scalafx.event.ActionEvent
import scalafx.scene.control.{Menu => MenuRoot, MenuBar, MenuItem}

final class Menu(context: Context) extends MenuBar:
  val menuItemAbout = new MenuItem:
    text = context.menuExit
    onAction = (_: ActionEvent) => Platform.exit()

  val menuItemExit = new MenuItem:
    text = context.menuExit
    onAction = (_: ActionEvent) => Platform.exit()

  val menuRoot = new MenuRoot():
    text = context.menuMenu
    items = List(menuItemExit)

  menus = List(menuRoot)