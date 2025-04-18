package objektwerks.ui

import com.typesafe.config.Config

import scalafx.scene.image.{Image, ImageView}

final class Context(config: Config):
  val windowTitle = config.getString("window.title")
  val windowWidth = config.getDouble("window.width")
  val windowHeight = config.getDouble("window.height")

  val headerIngrediants = config.getString("header.ingrediants")

  val tabBatches = config.getString("tab.batches")
  val tabBatch = config.getString("tab.batch")
  val tabRecipes = config.getString("tab.recipes")
  val tabRecipe = config.getString("tab.recipe")
  val tabLog = config.getString("tab.log")
  val tabTimeline = config.getString("tab.timeline")

  val columnBatch = config.getString("column.batch")
  val columnRecipe = config.getString("column.recipe")
  val columnBrewhouseEfficiency = config.getString("column.brewhouseEfficiency")

  val dialogRecipe = config.getString("dialog.recipe")
  val dialogGrains = config.getString("dialog.grains")
  val dialogHops = config.getString("dialog.hops")
  val dialogAdjuncts = config.getString("dialog.adjuncts")
  val dialogYeasts = config.getString("dialog.yeasts")
  val dialogTempRangeDuration = config.getString("dialog.tempRangeDuration")
  val dialogVolume = config.getString("dialog.volume")
  val dialogBrew = config.getString("dialog.brew")

  val removeDialogHeaderText = config.getString("remove.dialog.headerText")
  val removeDialogContentText = config.getString("remove.dialog.contentText")

  val aboutAlertHeaderText = config.getString("about.alert.headerText")
  val aboutAlertContentText = config.getString("about.alert.contentText")
  val recipenameAlertHeaderText = config.getString("recipename.alert.headerText")
  val recipenameAlertContentText = config.getString("recipename.alert.contentText")
  val recipenameDialogHeaderText = config.getString("recipename.dialog.headerText")
  val recipenameDialogContentText = config.getString("recipename.dialog.contentText")

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
  val labelActualMashExtract = config.getString("label.actualMashExtract")
  val labelBoilingTempRangeDuration = config.getString("label.boilingTempRangeDuration")
  val labelCoolingTempRange = config.getString("label.coolingTempRange")
  val labelFermentingTempRangeDuration = config.getString("label.fermentingTempRangeDuration")
  val labelPotentialFermentableExtract = config.getString("label.potentialFermentableExtract")
  val labelActualFermentableExtract = config.getString("label.actualFermentableExtract")
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
  val labelStarted = config.getString("label.started")
  val labelLog = config.getString("label.log")
  val labelTimeline = config.getString("label.timeline")
  val labelCompleted = config.getString("label.completed")
  val labelSanitizingStarted = config.getString("label.sanitizingStarted")
  val labelSanitizingCompleted = config.getString("label.sanitizingCompleted")
  val labelPreparingStarted = config.getString("label.preparingStarted")
  val labelPreparingCompleted = config.getString("label.preparingCompleted")
  val labelMaltingStarted = config.getString("label.maltingStarted")
  val labelMaltingCompleted = config.getString("label.maltingCompleted")
  val labelMillingStarted = config.getString("label.millingStarted")
  val labelMillingCompleted = config.getString("label.millingCompleted")
  val labelMashingStarted = config.getString("label.mashingStarted")
  val labelMashingCompleted = config.getString("label.mashingCompleted")
  val labelLauteringStarted = config.getString("label.lauteringStarted")
  val labelLauteringCompleted = config.getString("label.lauteringCompleted")
  val labelSpargingStarted = config.getString("label.spargingStarted")
  val labelSpargingCompleted = config.getString("label.spargingCompleted")
  val labelBoilingStarted = config.getString("label.boilingStarted")
  val labelBoilingCompleted = config.getString("label.boilingCompleted")
  val labelCoolingStarted = config.getString("label.coolingStarted")
  val labelCoolingCompleted = config.getString("label.coolingCompleted")
  val labelWhirlpoolingStarted = config.getString("label.whirlpoolingStarted")
  val labelWhirlpoolingCompleted = config.getString("label.whirlpoolingCompleted")
  val labelFermentingStarted = config.getString("label.fermentingStarted")
  val labelFermentingCompleted = config.getString("label.fermentingCompleted")
  val labelConditioningStarted = config.getString("label.conditioningStarted")
  val labelConditioningCompleted = config.getString("label.conditioningCompleted")
  val labelKeggingStarted = config.getString("label.keggingStarted")
  val labelKeggingCompleted = config.getString("label.keggingCompleted")

  val menuMenu = config.getString("menu.menu")
  val menuAbout = config.getString("menu.about")
  val menuExit = config.getString("menu.exit")

  val buttonSave = config.getString("button.save")
  val buttonAdd = config.getString("button.add")
  val buttonRemove = config.getString("button.remove")
  val buttonBrew = config.getString("button.brew")

  def imageViewBeer = loadImageView("/image/beer.png")
  def imageViewBatch = loadImageView("/image/batch.png")
  def imageViewEdit = loadImageView("/image/edit.png")
  def imageViewSave = loadImageView("/image/save.png")
  def imageViewPlus = loadImageView("/image/plus.png")
  def imageViewMinus = loadImageView("/image/minus.png")
  def imageViewLog = loadImageView("/image/log.png")
  def imageViewRecipe = loadImageView("/image/recipe.png")
  def imageViewTimeline = loadImageView("/image/timeline.png")
  def imageAppIcon = Image(Image.getClass.getResourceAsStream("/image/icon.png"))

  private def loadImageView(path: String): ImageView = new ImageView:
    image = Image(Image.getClass.getResourceAsStream(path))
    fitHeight = 22
    fitWidth = 22
    preserveRatio = true
    smooth = true