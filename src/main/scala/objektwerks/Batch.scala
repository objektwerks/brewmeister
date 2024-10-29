package objektwerks

import scala.math.pow

import upickle.default.{ReadWriter => JsonSupport}

object Batch:
  def default: Batch =
    Batch(recipe = "Objektwerks IPA",
          style = "American IPA",
          volume = Volume(5.0, UoM.gl),
          mashingTemp = 150,
          pH = 5.6,
          boilingTemp = 150,
          coolingTemp = 72,
          mashEfficiency = 70,
          originalGravity = 1.060,
          fermentingTemp = 72,
          finalGravity = 1.012,
          conditioningTemp = 72,
          srmColor = 9,
          ibuBitterness = 68,
          alcoholByVolume = 6.4,
          alcoholByWeight = 6.0,
          calories = 190,
          keggingTemp = 43,
          brewhouseEfficiency = 71,
          appearance = 3,
          aroma = 3,
          taste = 3,
          log = List.empty[String],
          created = now())

  def srmColor(batchVolume: Volume, grains: List[Grain]): Int =
    val sum = grains.map { grain => srmColor(grain.weight, grain.color, batchVolume.volume) }.sum
    ( sum / grains.length ).toInt

  def srmColor(grainWeight: Double,
               grainColor: Double,
               batchVolume: Double): Int =
    val maltColorUnits = (grainWeight * grainColor) / batchVolume
    ( 1.4922 * pow(maltColorUnits, 0.6859) ).toInt

  def ibuBitterness(hops: List[Hop]): Int =
    val sum = hops.map { hop => ibuBitterness(hop.alphaAcid, hop.weight, hop.volume) }.sum
    ( sum / hops.length ).toInt

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
                       appearance: Int = 3,
                       aroma: Int = 3,
                       taste: Int = 3,
                       log: List[String] = List.empty[String],
                       created: String = now()) derives JsonSupport