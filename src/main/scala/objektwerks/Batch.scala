package objektwerks

import scalafx.beans.property.ObjectProperty

import upickle.default.{ReadWriter => JsonSupport}

object Batch:
  given Ordering[Batch] = Ordering.by[Batch, String](b => b.completed).reverse

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

  def calories(packageVolume: Volume,
               originalGravity: Double,
               finalGravity: Double): Int =
    val oz = packageVolume.unit match
      case UoM.oz => packageVolume.value
      case UoM.gl => packageVolume.value * 128
      case UoM.lb => 0
    if oz != 0 then
      val alcoholCalories = (originalGravity - finalGravity) * 7.5
      val carbohydrateCalories = (finalGravity * 13) * oz
      ( alcoholCalories + carbohydrateCalories ).toInt
    else 0

  def mashEfficiency(actualMashExtract: Double,
                     potentialMashExtract: Double): Int =
    ( (actualMashExtract / potentialMashExtract) * 100 ).toInt

  def brewhouseEfficiency(actualFermentableExtract: Double,
                          potentialFermentableExtract: Double): Int =
    ( (actualFermentableExtract / potentialFermentableExtract) * 100 ).toInt

  def brewhouseEfficiency(batches: List[Batch]): Int =
    if batches.nonEmpty then ( batches.map(batch => batch.brewhouseEfficiency).sum / batches.length ).toInt
    else 0

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
                       started: String = "",
                       timeline: Timeline = Timeline(),
                       completed: String = "",
                       stored: String = "") derives CanEqual, JsonSupport:
  val nameProperty = ObjectProperty(s"$recipe.$stored")
  val brewhouseEfficiencyProperty = ObjectProperty(brewhouseEfficiency)
  val fileProperty = ObjectProperty(s"$recipe.$stored.json")

@upickle.implicits.serializeDefaults(true)
final case class Timeline(sanitizingStarted: String = "",
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
                          keggingCompleted: String = "") derives CanEqual, JsonSupport