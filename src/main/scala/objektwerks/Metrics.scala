package objektwerks

import scala.math.pow

import upickle.default.{ReadWriter => JsonSupport}

object Metrics:
  def default(batchId: Int): Metrics =
    Metrics(batchId = batchId,
            style = "American IPA",
            volume = Volume(5.0, UoM.gl),
            pH = 5.6,
            originalGravity = 1.060,
            finalGravity = 1.012,
            srmColor = 9,
            ibuBitterness = 68,
            alcoholByVolume = 6.4,
            alcoholByWeight = 6.0,
            calories = 190,
            mashEfficiency = 70,
            brewhouseEfficiency = 71)

  def build(batchId: Int,
            events: List[Event]): Metrics =
    var metrics = Metrics(batchId = batchId)
    events.foreach { event =>
      event match
        case Mashed(_, pH) =>
          metrics = metrics.copy(pH = pH)
        case Sparged(_, mashEfficiency) =>
          metrics = metrics.copy(mashEfficiency = mashEfficiency)
        case Whirlpooled(_, originalGravity) =>
          metrics = metrics.copy(originalGravity = originalGravity)
        case Fermented(_, finalGravity) =>
          metrics = metrics.copy(finalGravity = finalGravity)
        case Conditioned(_, srmColor) =>
          metrics = metrics.copy(srmColor = srmColor)
        case Packaged(_, ibuBitterness, alcoholByVolume, alcoholByWeight, calories, brewhouseEfficiency) =>
          metrics = metrics.copy(ibuBitterness = ibuBitterness,
                                alcoholByVolume = alcoholByVolume,
                                alcoholByWeight = alcoholByWeight,
                                calories = calories,
                                brewhouseEfficiency = brewhouseEfficiency)
        case _ =>
    }
    metrics

  def srmColor(batchVolume: Volume, grains: List[Grain]): Int =
    grains.map { grain => srmColor(grain.weight, grain.color, batchVolume.volume) }.sum

  def srmColor(grainWeight: Double,
               grainColor: Double,
               batchVolume: Double): Int =
    val maltColorUnits = (grainWeight * grainColor) / batchVolume
    ( 1.4922 * pow(maltColorUnits, 0.6859) ).toInt

  def ibuBitterness(hops: List[Hop]): Double =
    val sum = hops.map { hop => ibuBitterness(hop.alphaAcid, hop.weight, hop.volume) }.sum
    format(sum)

  def ibuBitterness(hopAlphaAcid: Double,
                    hopWeight: Double,
                    hopVolume: Double): Double =
    val hopUtilization = (hopAlphaAcid * hopWeight) / hopVolume
    ( (hopWeight * hopAlphaAcid * hopUtilization) / 7.25 ).toInt

  def alcoholByVolume(originalGravity: Double,
                      finalGravity: Double): Double =
    format( (originalGravity - finalGravity) * 131 )

  def alcoholByWeight(alcoholByVolume: Double,
                      finalGravity: Double): Double =
    format( (0.79 * alcoholByVolume) / finalGravity )

  def calories(beerVolume: Int,
               originalGravity: Double,
               finalGravity: Double): Int =
    val alcoholCalories = (originalGravity - finalGravity) * 7.5
    val carbohydrateCalories = (finalGravity * 13) * beerVolume
    ( alcoholCalories + carbohydrateCalories ).toInt

  def mashEfficiency(actualMashExtract: Double,
                     potentialMashExtract: Double): Int =
    ( (actualMashExtract / potentialMashExtract) * 100 ).toInt

  def brewhouseEfficiency(actualFermentableExtract: Double,
                          potentialFermentableExtract: Double): Int =
    ( (actualFermentableExtract / potentialFermentableExtract) * 100 ).toInt

final case class Metrics(batchId: Int,
                         created: String = now(),
                         style: String = "",
                         volume: Volume = Volume(0.0, UoM.gl),
                         pH: Double = 0.0,
                         originalGravity: Double = 0.0,
                         finalGravity: Double = 0.0,
                         srmColor: Int = 0,
                         ibuBitterness: Int = 0,
                         alcoholByVolume: Double = 0.0,
                         alcoholByWeight: Double = 0.0,
                         calories: Int = 0,
                         mashEfficiency: Int = 0,
                         brewhouseEfficiency: Int = 0) derives JsonSupport