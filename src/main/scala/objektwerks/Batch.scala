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
  val nameProperty = ObjectProperty[String](this, "name", s"$recipe.$completed")
  val styleProperty = ObjectProperty[String](this, "style", style)
  val volumeProperty = ObjectProperty[Volume](this, "volume", volume)
  val mashingTempProperty = ObjectProperty[Int](this, "mashingTemp", mashingTemp)
  val phProperty = ObjectProperty[Double](this, "pH", pH)
  val boilingTempProperty = ObjectProperty[Int](this, "boilingTemp", boilingTemp)
  val coolingTempProperty = ObjectProperty[Int](this, "coolingTemp", coolingTemp)
  val mashEfficiencyProperty = ObjectProperty[Int](this, "mashEfficiency", mashEfficiency)
  val originalGravityProperty = ObjectProperty[Double](this, "originalGravity", originalGravity)
  val fermentingTempProperty = ObjectProperty[Int](this, "fermentingTemp", fermentingTemp)
  val finalGravityProperty = ObjectProperty[Double](this, "finalGravity", finalGravity)
  val conditioningTempProperty = ObjectProperty[Int](this, "conditioningTemp", conditioningTemp)
  val srmColorProperty = ObjectProperty[Int](this, "srmColor", srmColor)
  val ibuBitternessProperty = ObjectProperty[Int](this, "ibuBitterness", ibuBitterness)
  val alcoholByVolumeProperty = ObjectProperty[Double](this, "alcoholByVolume", alcoholByVolume)
  val alcoholByWeightProperty = ObjectProperty[Double](this, "alcoholByWeight", alcoholByWeight)
  val caloriesProperty = ObjectProperty[Int](this, "calories", calories)
  val keggingTempProperty = ObjectProperty[Int](this, "keggingTemp", keggingTemp)
  val brewhouseEfficiencyProperty = ObjectProperty[Int](this, "brewhouseEfficiency", brewhouseEfficiency)
  val appearanceProperty = ObjectProperty[Int](this, "appearance", appearance)
  val aromaProperty = ObjectProperty[Int](this, "aroma", aroma)
  val tasteProperty = ObjectProperty[Int](this, "taste", taste)
  val logProperty = ObjectProperty[List[String]](this, "log", log)
  val startedProperty = ObjectProperty[String](this, "started", started)
  val processProperty = ObjectProperty[Process](this, "process", process)
  val completedProperty = ObjectProperty[String](this, "completed", completed)
  val batch = this

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
                         conditionStarted: String = "",
                         conditionCompleted: String = "",
                         kegStarted: String = "",
                         kegCompleted: String = "") derives CanEqual, JsonSupport:
  val sanitizingStartedProperty = ObjectProperty[String](this, "sanitizingStarted", sanitizingStarted)
  val sanitizingCompletedProperty = ObjectProperty[String](this, "sanitizingCompleted", sanitizingCompleted)
  val preparingStartedProperty = ObjectProperty[String](this, "preparingStarted", preparingStarted)
  val preparingCompletedProperty = ObjectProperty[String](this, "preparingCompleted", preparingCompleted)
  val maltingStartedProperty = ObjectProperty[String](this, "maltingStarted", maltingStarted)
  val maltingCompletedProperty = ObjectProperty[String](this, "maltingCompleted", maltingCompleted)
  val millingStartedProperty = ObjectProperty[String](this, "millingStarted", millingStarted)
  val millingCompletedProperty = ObjectProperty[String](this, "millingCompleted", millingCompleted) 
  val mashingStartedProperty = ObjectProperty[String](this, "mashingStarted", mashingStarted)
  val mashingCompletedProperty = ObjectProperty[String](this, "mashingCompleted", mashingCompleted)
  val lauteringStartedProperty = ObjectProperty[String](this, "lauteringStarted", lauteringStarted)
  val lauteringCompletedProperty = ObjectProperty[String](this, "lauteringCompleted", lauteringCompleted)
  val spargingStartedProperty = ObjectProperty[String](this, "spargingStarted", spargingStarted)
  val spargingCompletedProperty = ObjectProperty[String](this, "spargingCompleted", spargingCompleted)
  val boilingStartedProperty = ObjectProperty[String](this, "boilingStarted", boilingStarted)
  val boilingCompletedProperty = ObjectProperty[String](this, "boilingCompleted", boilingCompleted)
  val coolingStartedProperty = ObjectProperty[String](this, "coolingStarted", coolingStarted)
  val coolingCompletedProperty = ObjectProperty[String](this, "coolingCompleted", coolingCompleted)
  val whirlpoolingStartedProperty = ObjectProperty[String](this, "whirlpoolingStarted", whirlpoolingStarted)
  val whirlpoolingCompletedProperty = ObjectProperty[String](this, "whirlpoolingCompleted", whirlpoolingCompleted)
  val fermentingStartedProperty = ObjectProperty[String](this, "fermentingStarted", fermentingStarted)
  val fermentingCompletedProperty = ObjectProperty[String](this, "fermentingCompleted", fermentingCompleted)
  val conditionStartedProperty = ObjectProperty[String](this, "conditionStarted", conditionStarted)
  val conditionCompletedProperty = ObjectProperty[String](this, "conditionCompleted", conditionCompleted)
  val kegStartedProperty = ObjectProperty[String](this, "kegStarted", kegStarted)
  val kegCompletedProperty = ObjectProperty[String](this, "kegCompleted", kegCompleted)
  val process = this