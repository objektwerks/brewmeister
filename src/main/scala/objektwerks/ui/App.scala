package objektwerks.ui

import com.typesafe.config.ConfigFactory

import scalafx.application.JFXApp3
import scalafx.scene.image.Image

import objektwerks.Store

object App extends JFXApp3:
  val context = Context( ConfigFactory.load("app.conf") )
  val store = Store()
  val model = Model(store.listRecipes, store.listBatches)

  override def start(): Unit =
    stage = new JFXApp3.PrimaryStage:
      scene = View(context, model).scene
      title = context.windowTitle
      minWidth = context.windowWidth
      minHeight = context.windowHeight
      icons.add(context.logoImage)
    stage.show()