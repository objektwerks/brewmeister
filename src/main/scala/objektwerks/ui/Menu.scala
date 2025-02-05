package objektwerks.ui

import scalafx.Includes._
import scalafx.application.Platform
import scalafx.event.ActionEvent
import scalafx.scene.control.{Alert, Menu => MenuRoot, MenuBar, MenuItem, SeparatorMenuItem}
import scalafx.scene.control.Alert.AlertType

final class Menu(context: Context) extends MenuBar:
  val menuItemAbout = new MenuItem:
    text = context.menuAbout
    onAction = (_: ActionEvent) =>
      new Alert(AlertType.Information) {
        initOwner(App.stage)
        title = context.windowTitle
        headerText = context.aboutAlertHeaderText
        contentText = context.aboutAlertContentText
      }.showAndWait()

  val menuItemExit = new MenuItem:
    text = context.menuExit
    onAction = (_: ActionEvent) => Platform.exit()

  val menuRoot = new MenuRoot():
    text = context.menuMenu
    items = List(menuItemAbout, SeparatorMenuItem(), menuItemExit)

  menus = List(menuRoot)