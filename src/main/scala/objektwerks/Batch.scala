package objektwerks

import java.time.LocalDateTime

import scala.math.pow

import upickle.default.{ReadWriter => JsonSupport}

def now(): String = LocalDateTime.now.toString
def localDateTime(now: String): LocalDateTime = if now.nonEmpty then LocalDateTime.parse(now) else LocalDateTime.now

def format(double: Double): Double = f"$double%1.1f".toDouble
def formatGravity(double: Double): Double = f"$double%1.3f".toDouble

enum UoM derives JsonSupport:
  case oz, gl, ml, l, lb, kg

enum UoT derives JsonSupport:
  case minutes, days, weeks, months

object Batch:
  def default: Batch = Batch(recipe = Recipe.default, metrics = Metrics.default)

final case class Batch(id: Int = 1,
                       started: String = now(),
                       completed: String = "",
                       recipe: Recipe,
                       metrics: Metrics) derives JsonSupport

enum MixinStep derives JsonSupport:
  case Mashing, Boiling, Wirlpooling, Fermenting, Conditioning

final case class Grain(typeof: String,
                       amount: Double,
                       unit: UoM,
                       lovibond: Double,
                       mixinMinute: Int,
                       mixinStep: MixinStep = MixinStep.Mashing) derives JsonSupport

final case class Hop(typeof: String,
                     amount: Double,
                     unit: UoM,
                     alphaAcid: (Double, Double),
                     mixinMinute: Int,
                     mixinStep: MixinStep = MixinStep.Boiling) derives JsonSupport // or Whirlpooling or Conditioning

final case class Adjunct(typeof: String,
                         amount: Double,
                         unit: UoM,
                         mixinMinute: Int,
                         mixinStep: MixinStep = MixinStep.Mashing) derives JsonSupport // or Boiling or Conditioning

final case class Yeast(typeof: String,
                       amount: Double,
                       unit: UoM,
                       mixinMinute: Int,
                       mixinStep: MixinStep = MixinStep.Fermenting) derives JsonSupport

final case class TempRangeDuration(range: (Int, Int), duration: Int, unit: UoT) derives JsonSupport

object Recipe:
  def default: Recipe =
    Recipe(created = now(),
           style = "American IPA",
           water = "spring",
           gallons = 5.0,
           mashingTempRangeDuration = TempRangeDuration( (148, 152), 60, UoT.minutes ),
           boilingTempRangeDuration = TempRangeDuration( (148, 152), 60, UoT.minutes ),
           coolingTempRange = (68, 72),
           fermentatingTempRangeDuration = TempRangeDuration( (68, 72), 2, UoT.weeks ),
           conditioningTempRangeDuration = TempRangeDuration( (68, 72), 2, UoT.days ),
           packagingTempRangeDuration = TempRangeDuration( (42, 45), 2, UoT.days ),
           refrigerateTempRange = (42, 45),
           pH = 5.6,
           originalGravity = (1.057, 1.67),
           finalGravity = (1.010, 1.015),
           srmColor = (6, 12),
           ibuBitterness = (60, 75),
           alcoholByVolume = (6.0, 7.0),
           alcoholByWeight = (5.8, 6.8),
           calories = (180, 200),
           mashEfficiency = (70, 80),
           brewhouseEfficiency = (72, 80),
           grains = List( Grain("pale ale", 4.0, UoM.lb, 1.8, 0) ),
           hops = List( Hop("amarillo", 2.0, UoM.oz, (8.0, 11.0), 0), Hop("cascade", 2.0, UoM.oz, (4.5, 8.9), 15), Hop("chinook", 2.0, UoM.oz, (12.0, 14.0), 30) ),
           adjuncts = List.empty[Adjunct],
           yeasts = List( Yeast("Wyeast American Ale 1056", 5.0, UoM.oz, 0) )
    )

final case class Recipe(created: String = now(),
                        style: String,
                        water: String,
                        gallons: Double,
                        mashingTempRangeDuration: TempRangeDuration,
                        boilingTempRangeDuration: TempRangeDuration,
                        coolingTempRange: (Int, Int),
                        fermentatingTempRangeDuration: TempRangeDuration,
                        conditioningTempRangeDuration: TempRangeDuration,
                        packagingTempRangeDuration: TempRangeDuration,
                        refrigerateTempRange: (Int, Int),
                        pH: Double,
                        originalGravity: (Double, Double),
                        finalGravity: (Double, Double),
                        srmColor: (Int, Int),
                        ibuBitterness: (Int, Int),
                        alcoholByVolume: (Double, Double),
                        alcoholByWeight: (Double, Double),
                        calories: (Int, Int),
                        mashEfficiency: (Int, Int),
                        brewhouseEfficiency: (Int, Int),
                        grains: List[Grain],
                        hops: List[Hop],
                        adjuncts: List[Adjunct],
                        yeasts: List[Yeast]) derives JsonSupport

object Metrics:
  def default: Metrics =
    Metrics(created = now(),
            style = "American IPA",
            gallons = 5.0,
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

  def srmColor(grainWeight: Double,
               grainColor: Double,
               batchVolume: Double): Double =
    val maltColorUnits = (grainWeight * grainColor) / batchVolume
    format( 1.4922 * pow(maltColorUnits, 0.6859) )

  def ibuBitterness(hopAlphaAcid: Double,
                    hopWeight: Double,
                    hopVolume: Double): Double =
    val hopUtilization = (hopAlphaAcid * hopWeight) / hopVolume
    format( (hopWeight * hopAlphaAcid * hopUtilization) / 7.25 )

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
    format( alcoholCalories + carbohydrateCalories ).toInt

  def mashEfficiency(actualMashExtract: Double,
                     potentialMashExtract: Double): Double =
    format( (actualMashExtract / potentialMashExtract) * 100 )

  def brewhouseEfficiency(actualFermentableExtract: Double,
                          idealFermentableExtract: Double): Double =
    format( (actualFermentableExtract / idealFermentableExtract) * 100 )

final case class Metrics(created: String = now(),
                         style: String = "",
                         gallons: Double = 0.0,
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