package objektwerks

import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.image.Image
import scalafx.scene.layout.VBox

object App extends JFXApp3:
  override def start(): Unit =
    stage = new JFXApp3.PrimaryStage:
      scene = View.scene
      title = "Brewmeister"
      minWidth = View.width
      minHeight = View.height
      icons.add( new Image( Image.getClass.getResourceAsStream("/beer.png") ) )
    stage.show()

object View:
  val width = 700
  val height = 600

  val rootBox = new VBox:
    prefWidth = View.width
    prefHeight = View.height
    children = List( )

  val scene = new Scene:
    root = rootBox