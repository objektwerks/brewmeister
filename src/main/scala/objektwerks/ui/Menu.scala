package objektwerks.ui

import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.control.{Menu => MenuRoot, MenuBar, MenuItem}

final class Menu(context: Context) extends MenuBar:
  val menuItemExit = new MenuItem:
    text = context.menuExit
    onAction = (_: ActionEvent) => System.exit(0)

  val menuRoot = new MenuRoot():
    text = context.menuMenu
    items = List(menuItemExit)

  menus = List(menuRoot)