package objektwerks

import scala.math.pow

import scalafx.beans.property.ObjectProperty

import upickle.default.{ReadWriter => JsonSupport}

object Batch:
  given Ordering[Batch] = Ordering.by[Batch, String](b => b.completed).reverse

  def srmColor(batchVolume: Volume, grains: List[Grain]): Int =
    val sum = grains.map { grain => srmColor(grain.weight, grain.color, batchVolume.value) }.sum
    sum / grains.length

  def srmColor(grainWeight: Double,
               grainColor: Double,
               batchVolume: Double): Int =
    val maltColorUnits = (grainWeight * grainColor) / batchVolume
    ( 1.4922 * pow(maltColorUnits, 0.6859) ).toInt

  def ibuBitterness(hops: List[Hop]): Int =
    hops.map { hop => ibuBitterness(hop.alphaAcid, hop.weight, hop.volume) }.sum

  def ibuBitterness(hopAlphaAcid: Double,
                    hopWeight: Double,
                    hopVolume: Double): Int =
    val hopUtilization = (hopAlphaAcid * hopWeight) / hopVolume
    ( (hopWeight * hopAlphaAcid * hopUtilization) / 7.25 ).toInt

  def alcoholByVolume(originalGravity: Double,
                      finalGravity: Double): Double =
    ( (originalGravity - finalGravity) * 131 ).format

  def alcoholByWeight(alcoholByVolume: Double,
                      finalGravity: Double): Double =
    ( (0.79 * alcoholByVolume) / finalGravity ).format

  def calories(packageVolume: Double,
               originalGravity: Double,
               finalGravity: Double): Int =
    val alcoholCalories = (originalGravity - finalGravity) * 7.5
    val carbohydrateCalories = (finalGravity * 13) * packageVolume
    ( alcoholCalories + carbohydrateCalories ).toInt

  def mashEfficiency(actualMashExtract: Double,
                     potentialMashExtract: Double): Int =
    ( (actualMashExtract / potentialMashExtract) * 100 ).toInt

  def brewhouseEfficiency(actualFermentableExtract: Double,
                          potentialFermentableExtract: Double): Int =
    ( (actualFermentableExtract / potentialFermentableExtract) * 100 ).toInt

@upickle.implicits.serializeDefaults(true)
final case class Batch(recipe: String = "",
                       style: String = "",
                       volume: Volume = Volume(0.0, UoM.gl),
                       mashingTemp: Int = 0,
                       pH: Double = 0.0,
                       boilingTemp: Int = 0,
                       coolingTemp: Int = 0,
                       mashEfficiency: Int = 0,
                       originalGravity: Double = 0.0,
                       fermentingTemp: Int = 0,
                       finalGravity: Double = 0.0,
                       conditioningTemp: Int = 0,
                       srmColor: Int = 0,
                       ibuBitterness: Int = 0,
                       alcoholByVolume: Double = 0.0,
                       alcoholByWeight: Double = 0.0,
                       calories: Int = 0,
                       keggingTemp: Int = 0,
                       brewhouseEfficiency: Int = 0,
                       appearance: Int = 1,
                       aroma: Int = 1,
                       taste: Int = 1,
                       log: List[String] = List.empty[String],
                       started: String = now(),
                       process: Process = Process(),
                       completed: String = "") derives CanEqual, JsonSupport:
  val nameProperty = ObjectProperty(s"$recipe.$completed")
  val recipeProperty = ObjectProperty(recipe)
  val styleProperty = ObjectProperty(style)
  val volumeProperty = ObjectProperty(volume)
  val mashingTempProperty = ObjectProperty(mashingTemp)
  val phProperty = ObjectProperty(pH)
  val boilingTempProperty = ObjectProperty(boilingTemp)
  val coolingTempProperty = ObjectProperty(coolingTemp)
  val mashEfficiencyProperty = ObjectProperty(mashEfficiency)
  val originalGravityProperty = ObjectProperty(originalGravity)
  val fermentingTempProperty = ObjectProperty(fermentingTemp)
  val finalGravityProperty = ObjectProperty(finalGravity)
  val conditioningTempProperty = ObjectProperty(conditioningTemp)
  val srmColorProperty = ObjectProperty(srmColor)
  val ibuBitternessProperty = ObjectProperty(ibuBitterness)
  val alcoholByVolumeProperty = ObjectProperty(alcoholByVolume)
  val alcoholByWeightProperty = ObjectProperty(alcoholByWeight)
  val caloriesProperty = ObjectProperty(calories)
  val keggingTempProperty = ObjectProperty(keggingTemp)
  val brewhouseEfficiencyProperty = ObjectProperty(brewhouseEfficiency)
  val appearanceProperty = ObjectProperty(appearance)
  val aromaProperty = ObjectProperty(aroma)
  val tasteProperty = ObjectProperty(taste)
  val logProperty = ObjectProperty(log)
  val startedProperty = ObjectProperty(started)
  val processProperty = ObjectProperty[Process](process)
  val completedProperty = ObjectProperty(completed)
  val batch = this

  def propertiesToBatch(): Batch =
    Batch(
      recipe = recipeProperty.value,
      style = styleProperty.value,
      volume = volumeProperty.value,
      mashingTemp = mashingTempProperty.value,
      pH = phProperty.value,
      boilingTemp = boilingTempProperty.value,
      coolingTemp = coolingTempProperty.value,
      mashEfficiency = mashEfficiencyProperty.value,
      originalGravity = originalGravityProperty.value,
      fermentingTemp = fermentingTempProperty.value,
      finalGravity = finalGravityProperty.value,
      conditioningTemp = conditioningTempProperty.value,
      srmColor = srmColorProperty.value,
      ibuBitterness = ibuBitternessProperty.value,
      alcoholByVolume = alcoholByVolumeProperty.value,
      alcoholByWeight = alcoholByWeightProperty.value,
      calories = caloriesProperty.value,
      keggingTemp = keggingTempProperty.value,
      brewhouseEfficiency = brewhouseEfficiencyProperty.value,
      appearance = appearanceProperty.value,
      aroma = aromaProperty.value,
      taste = tasteProperty.value,
      log = logProperty.value,
      started = startedProperty.value,
      process = processProperty.value,
      completed = completedProperty.value
    )

@upickle.implicits.serializeDefaults(true)
final case class Process(sanitizingStarted: String = "",
                         sanitizingCompleted: String = "",
                         preparingStarted: String = "",
                         preparingCompleted: String = "",
                         maltingStarted: String = "",
                         maltingCompleted: String = "",
                         millingStarted: String = "",
                         millingCompleted: String = "",
                         mashingStarted: String = "",
                         mashingCompleted: String = "",
                         lauteringStarted: String = "",
                         lauteringCompleted: String = "",
                         spargingStarted: String = "",
                         spargingCompleted: String = "",
                         boilingStarted: String = "",
                         boilingCompleted: String = "",
                         coolingStarted: String = "",
                         coolingCompleted: String = "",
                         whirlpoolingStarted: String = "",
                         whirlpoolingCompleted: String = "",
                         fermentingStarted: String = "",
                         fermentingCompleted: String = "",
                         conditioningStarted: String = "",
                         conditioningCompleted: String = "",
                         keggingStarted: String = "",
                         keggingCompleted: String = "") derives CanEqual, JsonSupport:
  val sanitizingStartedProperty = ObjectProperty(this, "sanitizingStarted", sanitizingStarted)
  val sanitizingCompletedProperty = ObjectProperty(this, "sanitizingCompleted", sanitizingCompleted)
  val preparingStartedProperty = ObjectProperty(this, "preparingStarted", preparingStarted)
  val preparingCompletedProperty = ObjectProperty(this, "preparingCompleted", preparingCompleted)
  val maltingStartedProperty = ObjectProperty(this, "maltingStarted", maltingStarted)
  val maltingCompletedProperty = ObjectProperty(this, "maltingCompleted", maltingCompleted)
  val millingStartedProperty = ObjectProperty(this, "millingStarted", millingStarted)
  val millingCompletedProperty = ObjectProperty(this, "millingCompleted", millingCompleted) 
  val mashingStartedProperty = ObjectProperty(this, "mashingStarted", mashingStarted)
  val mashingCompletedProperty = ObjectProperty(this, "mashingCompleted", mashingCompleted)
  val lauteringStartedProperty = ObjectProperty(this, "lauteringStarted", lauteringStarted)
  val lauteringCompletedProperty = ObjectProperty(this, "lauteringCompleted", lauteringCompleted)
  val spargingStartedProperty = ObjectProperty(this, "spargingStarted", spargingStarted)
  val spargingCompletedProperty = ObjectProperty(this, "spargingCompleted", spargingCompleted)
  val boilingStartedProperty = ObjectProperty(this, "boilingStarted", boilingStarted)
  val boilingCompletedProperty = ObjectProperty(this, "boilingCompleted", boilingCompleted)
  val coolingStartedProperty = ObjectProperty(this, "coolingStarted", coolingStarted)
  val coolingCompletedProperty = ObjectProperty(this, "coolingCompleted", coolingCompleted)
  val whirlpoolingStartedProperty = ObjectProperty(this, "whirlpoolingStarted", whirlpoolingStarted)
  val whirlpoolingCompletedProperty = ObjectProperty(this, "whirlpoolingCompleted", whirlpoolingCompleted)
  val fermentingStartedProperty = ObjectProperty(this, "fermentingStarted", fermentingStarted)
  val fermentingCompletedProperty = ObjectProperty(this, "fermentingCompleted", fermentingCompleted)
  val conditioningStartedProperty = ObjectProperty(this, "conditioningStarted", conditioningStarted)
  val conditioningCompletedProperty = ObjectProperty(this, "conditioningCompleted", conditioningCompleted)
  val keggingStartedProperty = ObjectProperty(this, "keggingStarted", keggingStarted)
  val keggingCompletedProperty = ObjectProperty(this, "keggingCompleted", keggingCompleted)
  val process = this

  def propertiesToProcess(): Process =
    Process(
      sanitizingStarted = sanitizingStartedProperty.value,
      sanitizingCompleted = sanitizingCompletedProperty.value,
      preparingStarted = preparingStartedProperty.value,
      preparingCompleted = preparingCompletedProperty.value,
      maltingStarted = maltingStartedProperty.value,
      maltingCompleted = maltingCompletedProperty.value,
      millingStarted = millingStartedProperty.value,
      millingCompleted = millingCompletedProperty.value,
      mashingStarted = mashingStartedProperty.value,
      mashingCompleted = mashingCompletedProperty.value,
      lauteringStarted = lauteringStartedProperty.value,
      lauteringCompleted = lauteringCompletedProperty.value,
      spargingStarted = spargingStartedProperty.value,
      spargingCompleted = spargingCompletedProperty.value,
      boilingStarted = boilingStartedProperty.value,
      boilingCompleted = boilingCompletedProperty.value,
      coolingStarted = coolingStartedProperty.value,
      coolingCompleted = coolingCompletedProperty.value,
      whirlpoolingStarted = whirlpoolingStartedProperty.value,
      whirlpoolingCompleted = whirlpoolingCompletedProperty.value,
      fermentingStarted = fermentingStartedProperty.value,
      fermentingCompleted = fermentingCompletedProperty.value,
      conditioningStarted = conditioningStartedProperty.value,
      conditioningCompleted = conditioningCompletedProperty.value,
      keggingStarted = keggingStartedProperty.value,
      keggingCompleted = keggingCompletedProperty.value
    )