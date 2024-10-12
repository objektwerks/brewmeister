package objektwerks

import java.time.LocalDateTime

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
  def default: Batch = Batch(recipe = Recipe.default)

final case class Batch(id: Int = 1,
                       started: String = now(),
                       completed: String = "",
                       recipe: Recipe) derives JsonSupport

enum MixinStep derives JsonSupport:
  case Mashing, Boiling, Wirlpooling, Fermenting, Conditioning

final case class Grain(typeof: String,
                       weight: Double,
                       unit: UoM,
                       color: Double,
                       lovibond: Double,
                       potentialMashExtract: Double,
                       mixinMinute: Int,
                       mixinStep: MixinStep = MixinStep.Mashing) derives JsonSupport

final case class Hop(typeof: String,
                     weight: Double,
                     volume: Double,
                     unit: UoM,
                     alphaAcid: DoubleRange,
                     mixinMinute: Int,
                     mixinStep: MixinStep = MixinStep.Boiling) derives JsonSupport // or Whirlpooling or Conditioning

final case class Adjunct(typeof: String,
                         weight: Double,
                         unit: UoM,
                         mixinMinute: Int,
                         mixinStep: MixinStep = MixinStep.Mashing) derives JsonSupport // or Boiling or Conditioning

final case class Yeast(typeof: String,
                       weight: Double,
                       unit: UoM,
                       mixinMinute: Int,
                       mixinStep: MixinStep = MixinStep.Fermenting) derives JsonSupport

final case class DoubleRange(low: Double, high: Double) derives JsonSupport:
  def avg: Double = format( (low + high) / 2 )

final case class IntRange(low: Int, high: Int) derives JsonSupport:
  def avg: Int = ( (low + high) / 2 ).toInt

final case class TempDuration(tempRange: IntRange, duration: Int, unit: UoT) derives JsonSupport

final case class Volume(volume: Double, unit: UoM) derives JsonSupport

object Recipe:
  def default: Recipe =
    Recipe(style = "American IPA",
           water = "spring",
           brewVolume = Volume(5.0, UoM.gl),
           packageVolume = Volume(12.0, UoM.oz),
           mashingTempDuration = TempDuration( IntRange(148, 152), 60, UoT.minutes ),
           boilingTempDuration = TempDuration( IntRange(148, 152), 60, UoT.minutes ),
           coolingTempRange = IntRange(68, 72),
           fermentatingTempDuration = TempDuration( IntRange(68, 72), 2, UoT.weeks ),
           conditioningTempDuration = TempDuration( IntRange(68, 72), 2, UoT.days ),
           packagingTempDuration = TempDuration( IntRange(42, 45), 2, UoT.days ),
           refrigerateTempRange = IntRange(42, 45),
           pH = 5.6,
           originalGravity = DoubleRange(1.057, 1.67),
           finalGravity = DoubleRange(1.010, 1.015),
           srmColor = IntRange(6, 12),
           ibuBitterness = IntRange(60, 75),
           alcoholByVolume = DoubleRange(6.0, 7.0),
           alcoholByWeight = DoubleRange(5.8, 6.8),
           calories = IntRange(180, 200),
           mashEfficiency = IntRange(70, 80),
           brewhouseEfficiency = IntRange(72, 80),
           grains = List( Grain("pale ale", 4.0, UoM.lb, 6.0, 4.0, 1.8, 0) ),
           hops = List( Hop("chinook", 2.0, 10.0, UoM.oz, DoubleRange(12.0, 14.0), 30) ),
           adjuncts = List.empty[Adjunct],
           yeasts = List( Yeast("Wyeast American Ale 1056", 5.0, UoM.oz, 0) )
    )

final case class Recipe(created: String = now(),
                        style: String,
                        water: String,
                        brewVolume: Volume,
                        packageVolume: Volume,
                        mashingTempDuration: TempDuration,
                        boilingTempDuration: TempDuration,
                        coolingTempRange: IntRange,
                        fermentatingTempDuration: TempDuration,
                        conditioningTempDuration: TempDuration,
                        packagingTempDuration: TempDuration,
                        refrigerateTempRange: IntRange,
                        pH: Double,
                        originalGravity: DoubleRange,
                        finalGravity: DoubleRange,
                        srmColor: IntRange,
                        ibuBitterness: IntRange,
                        alcoholByVolume: DoubleRange,
                        alcoholByWeight: DoubleRange,
                        calories: IntRange,
                        mashEfficiency: IntRange,
                        brewhouseEfficiency: IntRange,
                        grains: List[Grain],
                        hops: List[Hop],
                        adjuncts: List[Adjunct],
                        yeasts: List[Yeast]) derives JsonSupport