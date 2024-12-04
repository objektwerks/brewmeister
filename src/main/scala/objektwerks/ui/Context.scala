package objektwerks.ui

import com.typesafe.config.Config

import scalafx.scene.image.{Image, ImageView}

final class Context(config: Config):
  val windowTitle = config.getString("window.title")
  val windowWidth = config.getDouble("window.width")
  val windowHeight = config.getDouble("window.height")

  val tabBatches = config.getString("tab.batches")
  val tabBatch = config.getString("tab.batch")
  val tabRecipes = config.getString("tab.recipes")
  val tabRecipe = config.getString("tab.recipe")

  val columnBatch = config.getString("column.batch")
  val columnRecipe = config.getString("column.recipe")

  val dialogRecipe = config.getString("dialog.recipe")
  val dialogGrains = config.getString("dialog.grains")
  val dialogHops = config.getString("dialog.hops")
  val dialogAdjuncts = config.getString("dialog.adjuncts")
  val dialogYeasts = config.getString("dialog.yeasts")
  val dialogTempRangeDuration = config.getString("dialog.tempRangeDuration")
  val dialogVolume = config.getString("dialog.volume")

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
  val labelWeight = config.getString("label.weight")
  val labelColor = config.getString("label.color")
  val labelLovibond = config.getString("label.lovibond")
  val labelMixinMinute = config.getString("label.mixinMinute")
  val labelMixinStep = config.getString("label.mixinStep")
  val labelAlphaAcid = config.getString("label.alphaAcid")
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
  val labelOriginalGravityRange = config.getString("label.originalGravityRange")
  val labelFinalGravityRange = config.getString("label.finalGravityRange")
  val labelSrmColorRange = config.getString("label.srmColorRange")
  val labelIbuBitternessRange = config.getString("label.ibuBitternessRange")
  val labelAlcoholByVolumeRange = config.getString("label.alcoholByVolumeRange")
  val labelAlcoholByWeightRange = config.getString("label.alcoholByWeightRange")
  val labelCalorieRange = config.getString("label.calorieRange")
  val labelMashEfficiencyRange = config.getString("label.mashEfficiencyRange")
  val labelBrewhouseEfficiencyRange = config.getString("label.brewhouseEfficiencyRange")
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

  val tooltipAdd = config.getString("tooltip.add")
  val tooltipSave = config.getString("tooltip.save")
  val tooltipRemove = config.getString("tooltip.remove")
  val tooltipBrew = config.getString("tooltip.brew")

  def imageViewLogo = loadImageView("/image/logo.png")
  def imageViewAdd = loadImageView("/image/add.png")
  def imageViewBang = loadImageView("/image/bang.png")
  def imageViewSave = loadImageView("/image/save.png")
  def imageViewPlus = loadImageView("/image/plus.png")
  def imageViewMinus = loadImageView("/image/minus.png")
  def logoImage = new Image(Image.getClass.getResourceAsStream("/image/logo.png"))

  private def loadImageView(path: String): ImageView = new ImageView:
    image = new Image(Image.getClass.getResourceAsStream(path))
    fitHeight = 25
    fitWidth = 25
    preserveRatio = true
    smooth = true