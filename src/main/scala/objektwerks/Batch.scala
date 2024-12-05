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

  val batch = this

@upickle.implicits.serializeDefaults(true)
final case class Process(sanitizeStarted: String = "",
                         sanitizeCompleted: String = "",
                         prepareStarted: String = "",
                         prepareCompleted: String = "",
                         maltStarted: String = "",
                         maltCompleted: String = "",
                         millStarted: String = "",
                         millCompleted: String = "",
                         mashStarted: String = "",
                         mashCompleted: String = "",
                         lauterStarted: String = "",
                         lauterCompleted: String = "",
                         spargeStarted: String = "",
                         spargeCompleted: String = "",
                         boilStarted: String = "",
                         boilCompleted: String = "",
                         coolStarted: String = "",
                         coolCompleted: String = "",
                         whirlpoolStarted: String = "",
                         whirlpoolCompleted: String = "",
                         fermentStarted: String = "",
                         fermentCompleted: String = "",
                         conditionStarted: String = "",
                         conditionCompleted: String = "",
                         kegStarted: String = "",
                         kegCompleted: String = "") derives CanEqual, JsonSupport