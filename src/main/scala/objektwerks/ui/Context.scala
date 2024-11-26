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

  val labelName = config.getString("label.name")
  val labelStyle = config.getString("label.style")
  val labelWater = config.getString("label.water")
  val labelVolume = config.getString("label.volume")
  val labelValue = config.getString("label.value")
  val labelUnit = config.getString("label.unit")
  val labelGrains = config.getString("label.grains")
  val labelHops = config.getString("label.hops")
  val labelAdjuncts = config.getString("label.adjuncts")
  val labelYeasts = config.getString("label.yeasts")
  /* 
    weight = "Weight:"
    color = "Color:"
    lovibond = "Lovibond:"
    mixinMinute = "Mixin Minute:"
    mixinStep = "Mixin Step:"
    alphaAcid = "Alpha Acid:"
   */
  val labelWeight = config.getString("label.weight")
  val labelColor = config.getString("label.color")

  val labelTempRange = config.getString("label.tempRange")
  val labelDuration = config.getString("label.duration")
  val labelMashingTempRangeDuration = config.getString("label.mashingTempRangeDuration")
  val labelPotentialMashExtract = config.getString("label.potentialMashExtract")
  val labelBoilingTempRangeDuration = config.getString("label.boilingTempRangeDuration")
  val labelCoolingTempRange = config.getString("label.coolingTempRange")
  val labelFermentingTempRangeDuration = config.getString("label.fermentingTempRangeDuration")
  val labelPotentialFermentableExtract = config.getString("label.potentialFermentableExtract")
  val labelConditioningTempRangeDuration = config.getString("label.conditioningTempRangeDuration")
  val labelKeggingTempRangeDuration = config.getString("label.keggingTempRangeDuration")
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
  val labelRecipe = config.getString("label.recipe")
  val labelMashingTemp = config.getString("label.mashingTemp")
  val labelBoilingTemp = config.getString("label.boilingTemp")
  val labelCoolingTemp = config.getString("label.coolingTemp")
  val labelFermentingTemp = config.getString("label.fermentingTemp")
  val labelConditioningTemp = config.getString("label.conditioningTemp")
  val labelKeggingTemp = config.getString("label.keggingTemp")
  val labelAppearance = config.getString("label.appearance")
  val labelAroma = config.getString("label.aroma")
  val labelTaste = config.getString("label.taste")
  val labelLog = config.getString("label.log")
  val labelStarted = config.getString("label.started")
  val labelProcessed = config.getString("label.processed")
  val labelCompleted = config.getString("label.completed")
  val labelSanitizeStarted = config.getString("label.sanitizeStarted")
  val labelSanitizeCompleted = config.getString("label.sanitizeCompleted")
  val labelPrepareStarted = config.getString("label.prepareStarted")
  val labelPrepareCompleted = config.getString("label.prepareCompleted")
  val labelMaltStarted = config.getString("label.maltStarted")
  val labelMaltCompleted = config.getString("label.maltCompleted")
  val labelMillStarted = config.getString("label.millStarted")
  val labelMillCompleted = config.getString("label.millCompleted")
  val labelMashStarted = config.getString("label.mashStarted")
  val labelMashCompleted = config.getString("label.mashCompleted")
  val labelLauterStarted = config.getString("label.lauterStarted")
  val labelLauterCompleted = config.getString("label.lauterCompleted")
  val labelSpargeStarted = config.getString("label.spargeStarted")
  val labelSpargeCompleted = config.getString("label.spargeCompleted")
  val labelBoilStarted = config.getString("label.boilStarted")
  val labelBoilCompleted = config.getString("label.boilCompleted")
  val labelCoolStarted = config.getString("label.coolStarted")
  val labelCoolCompleted = config.getString("label.coolCompleted")
  val labelWhirlpoolStarted = config.getString("label.whirlpoolStarted")
  val labelWhirlpoolCompleted = config.getString("label.whirlpoolCompleted")
  val labelFermentStarted = config.getString("label.fermentStarted")
  val labelFermentCompleted = config.getString("label.fermentCompleted")
  val labelConditionStarted = config.getString("label.conditionStarted")
  val labelConditionCompleted = config.getString("label.conditionCompleted")
  val labelKegStarted = config.getString("label.kegStarted")
  val labelKegCompleted = config.getString("label.kegCompleted")

  def logoImageView = loadImageView("/image/logo.png")
  def addImageView = loadImageView("/image/add.png")
  def bangImageView = loadImageView("/image/bang.png")
  def editImageView = loadImageView("/image/edit.png")
  def removeImageView = loadImageView("/image/remove.png")
  def logoImage = new Image(Image.getClass.getResourceAsStream("/image/logo.png"))

  private def loadImageView(path: String): ImageView = new ImageView:
    image = new Image(Image.getClass.getResourceAsStream(path))
    fitHeight = 25
    fitWidth = 25
    preserveRatio = true
    smooth = true