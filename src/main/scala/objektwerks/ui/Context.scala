package objektwerks.ui

import com.typesafe.config.Config

import scalafx.scene.image.{Image, ImageView}

final class Context(config: Config):
  val windowTitle = config.getString("window.title")
  val windowWidth = config.getDouble("window.width")
  val windowHeight = config.getDouble("window.height")

  val buttonAdd = config.getString("button.add")
  val buttonEdit = config.getString("button.edit")
  val buttonSave = config.getString("button.save")
  val buttonSimulate = config.getString("button.simulate")
  val buttonRemove = config.getString("button.remove")

  val tabBatches = config.getString("tab.batches")
  val tabBatch = config.getString("tab.batch")
  val tabRecipes = config.getString("tab.recipes")
  val tabRecipe = config.getString("tab.recipe")

  val columnBatch = config.getString("column.batch")
  val columnRecipe = config.getString("column.recipe")

  val dialogRecipe = config.getString("dialog.recipe")
  val dialogGrain = config.getString("dialog.grain")
  val dialogHop = config.getString("dialog.hop")
  val dialogAdjunct = config.getString("dialog.adjunct")
  val dialogYeast = config.getString("dialog.yeast")

  def logoImageView = loadImageView("/image/logo.png")
  def addImageView = loadImageView("/image/add.png")
  def editImageView = loadImageView("/image/edit.png")
  def logoImage = new Image(Image.getClass.getResourceAsStream("/image/logo.png"))

  private def loadImageView(path: String): ImageView = new ImageView:
    image = new Image(Image.getClass.getResourceAsStream(path))
    fitHeight = 25
    fitWidth = 25
    preserveRatio = true
    smooth = true