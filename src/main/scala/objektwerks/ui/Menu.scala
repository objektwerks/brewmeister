package objektwerks.ui

import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.control.{Menu, MenuBar, MenuItem}

final class Menu(context: Context) extends MenuBar:
  val menuItemExit = new MenuItem:
    text = context.labelAdjuncts
    onAction = (_: ActionEvent) => System.exit(0)

  val menu = new Menu():
    text = context.labelAdjuncts
    items = List(menuItemExit)

  menus = List(menu)