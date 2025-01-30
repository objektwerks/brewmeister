package objektwerks.ui

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging

import java.awt.{Taskbar, Toolkit}
import java.awt.Taskbar.Feature

import scalafx.application.JFXApp3
import scalafx.scene.image.Image

import objektwerks.Store

object App extends JFXApp3 with LazyLogging:
  logger.info("Starting app ...")

  val context = Context( ConfigFactory.load("app.conf") )
  val model = Model( Store() )

  if Taskbar.isTaskbarSupported() then
    val taskbar = Taskbar.getTaskbar()
    if (taskbar.isSupported(Feature.ICON_IMAGE)) then
      val defaultToolkit = Toolkit.getDefaultToolkit()
      val dockIcon = defaultToolkit.getImage(getClass().getResource("/image/logo.png"))
      taskbar.setIconImage(dockIcon)

  override def start(): Unit =
    stage = new JFXApp3.PrimaryStage:
      scene = View(context, model).scene
      title = context.windowTitle
      minWidth = context.windowWidth
      minHeight = context.windowHeight
      icons += context.logoImage
    stage.show()

    logger.info("Started app.")

  sys.addShutdownHook {
    logger.info("Shutdown app.")
  }