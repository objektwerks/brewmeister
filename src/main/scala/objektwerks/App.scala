package objektwerks

import scalafx.application.JFXApp3
import scalafx.scene.image.Image

import ui.View

object App extends JFXApp3:
  override def start(): Unit =
    stage = new JFXApp3.PrimaryStage:
      scene = View.scene
      title = "Brewmeister"
      minWidth = View.width
      minHeight = View.height
      icons.add( new Image( Image.getClass.getResourceAsStream("/beer.png") ) )
    stage.show()