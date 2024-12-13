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
  val sanitizingStartedProperty = ObjectProperty(sanitizingStarted)
  val sanitizingCompletedProperty = ObjectProperty(sanitizingCompleted)
  val preparingStartedProperty = ObjectProperty(preparingStarted)
  val preparingCompletedProperty = ObjectProperty(preparingCompleted)
  val maltingStartedProperty = ObjectProperty(maltingStarted)
  val maltingCompletedProperty = ObjectProperty(maltingCompleted)
  val millingStartedProperty = ObjectProperty(millingStarted)
  val millingCompletedProperty = ObjectProperty(millingCompleted) 
  val mashingStartedProperty = ObjectProperty(mashingStarted)
  val mashingCompletedProperty = ObjectProperty(mashingCompleted)
  val lauteringStartedProperty = ObjectProperty(lauteringStarted)
  val lauteringCompletedProperty = ObjectProperty(lauteringCompleted)
  val spargingStartedProperty = ObjectProperty(spargingStarted)
  val spargingCompletedProperty = ObjectProperty(spargingCompleted)
  val boilingStartedProperty = ObjectProperty(boilingStarted)
  val boilingCompletedProperty = ObjectProperty(boilingCompleted)
  val coolingStartedProperty = ObjectProperty(coolingStarted)
  val coolingCompletedProperty = ObjectProperty(coolingCompleted)
  val whirlpoolingStartedProperty = ObjectProperty(whirlpoolingStarted)
  val whirlpoolingCompletedProperty = ObjectProperty(whirlpoolingCompleted)
  val fermentingStartedProperty = ObjectProperty(fermentingStarted)
  val fermentingCompletedProperty = ObjectProperty(fermentingCompleted)
  val conditioningStartedProperty = ObjectProperty(conditioningStarted)
  val conditioningCompletedProperty = ObjectProperty(conditioningCompleted)
  val keggingStartedProperty = ObjectProperty(keggingStarted)
  val keggingCompletedProperty = ObjectProperty(keggingCompleted)
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