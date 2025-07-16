package objektwerks.ui

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging

import java.awt.{Taskbar, Toolkit}
import java.awt.Taskbar.Feature

import scalafx.application.JFXApp3
import scalafx.scene.image.Image

import objektwerks.Store

object App extends JFXApp3 with LazyLogging:
  logger.info("Brewmeister starting ...")

  val context = Context(ConfigFactory.load("app.conf"))
  val model = Model( Store() )

  override def start(): Unit =
    stage = new JFXApp3.PrimaryStage:
      scene = View(context, model).scene
      title = context.windowTitle
      minWidth = context.windowWidth
      minHeight = context.windowHeight
      icons += context.imageAppIcon

    if Taskbar.isTaskbarSupported() then
      val taskbar = Taskbar.getTaskbar()
      if taskbar.isSupported(Feature.ICON_IMAGE) then
        val appIcon = Toolkit.getDefaultToolkit.getImage(this.getClass().getResource("/image/icon.png"))
        taskbar.setIconImage(appIcon)

    stage.show()

    logger.info("Brewmeister started.")

  sys.addShutdownHook:
    logger.info("Brewmeister shutdown.")