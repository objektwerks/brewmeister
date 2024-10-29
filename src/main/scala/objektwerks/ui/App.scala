package objektwerks.ui

import com.typesafe.config.ConfigFactory

import scalafx.application.JFXApp3
import scalafx.scene.image.Image

object App extends JFXApp3:
  val config = ConfigFactory.load("app.conf")
  val context = Context(config)

  override def start(): Unit =
    stage = new JFXApp3.PrimaryStage:
      scene = View(context).scene
      title = context.windowTitle
      minWidth = context.windowWidth
      minHeight = context.windowHeight
      icons.add(context.logoImage)
    stage.show()