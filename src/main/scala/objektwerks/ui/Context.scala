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

  val labelStyle = config.getString("label.style")
  val labelWater = config.getString("label.water")
  val labelVolume = config.getString("label.volume")
  val labelGrains = config.getString("label.grains")
  val labelHops = config.getString("label.hops")
  val labelAdjuncts = config.getString("label.adjuncts")
  val labelYeasts = config.getString("label.yeasts")
  val labelMashingTempDuration = config.getString("label.mashingTempDuration")
  val labelPotentialMashExtract = config.getString("label.potentialMashExtract")
  val labelBoilingTempDuration = config.getString("label.boilingTempDuration")
  val labelCoolingTempRange = config.getString("label.coolingTempRange")
  val labelFermentingTempDuration = config.getString("label.fermentingTempDuration")
  val labelPotentialFermentableExtract = config.getString("label.potentialFermentableExtract")
  val labelConditioningTempDuration = config.getString("label.conditioningTempDuration")
  val labelKeggingTempDuration = config.getString("label.keggingTempDuration")
  val labelPh = config.getString("label.pH")
  val labelOriginalGravity = config.getString("label.originalGravity")
  val labelFinalGravity = config.getString("label.finalGravity")
  val labelSrmColor = config.getString("label.srmColor")
  val labelIbuBitterness = config.getString("label.ibuBitterness")
  val labelAlcoholByVolume = config.getString("label.alcoholByVolume")
  val labelAlcoholByWeight = config.getString("label.alcoholByWeight")
  val labelCalories = config.getString("label.calories")
  val labelMashEfficiency = config.getString("label.mashEfficiency")
  val labelBrewhouseEfficiency = config.getString("label.brewhouseEfficiency")
  val labelCreated = config.getString("label.created")

  /* 
  recipe = "Recipe:"
  mashingTemp = "Mashing Temp:"
  boilingTemp = "Boiling Temp:"
  coolingTemp = "Cooling Temp:"
  mashEfficiency = "Mass Efficiency:"
  fermentingTemp = "Fermenting Temp:"
  conditioningTemp = "Conditioning Temp:"
  keggingTemp = "Kegging Temp:"
  brewhouseEfficiency = "Brewhouse Efficiency:"
  appearance = "Appearance:"
  aroma = "Aroma:"
  taste = "Taste:"
  log = "Log:"
  started = "Started:"
  process = "Process:"
  completed = "Completed:"
   */

  val labelRecipe = config.getString("label.recipe")
  val labelMashingTemp = config.getString("label.mashingTemp")
  val labelBoilingTemp = config.getString("label.boilingTemp")

  val labelCompleted = config.getString("label.completed")


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