package objektwerks

import scalafx.beans.property.ObjectProperty

import scala.util.Random

import upickle.default.ReadWriter as JsonSupport

object Recipe:
  given Ordering[Recipe] = Ordering.by[Recipe, String](r => r.name)

  def default: Recipe =
    Recipe(name = "Objektwerks IPA",
           style = "American IPA",
           water = "spring",
           volume = Volume(5.0, UoM.gl),
           grains = List( Grain("pale ale", 4.0, UoM.lb, 6.0, 1.8, 0) ),
           hops = List( Hop("chinook", 2.0, 10.0, UoM.oz, 13.0, 30) ),
           adjuncts = List.empty[Adjunct],
           yeasts = List( Yeast("Wyeast American Ale 1056", 5.0, UoM.oz, 0) ),
           mashingTempRangeDuration = TempRangeDuration( IntRange(148, 152), 60, UoT.minutes ),
           potentialMashExtract = 5.0,
           boilingTempRangeDuration = TempRangeDuration( IntRange(148, 152), 60, UoT.minutes ),
           coolingTempRange = IntRange(68, 72),
           fermentingTempRangeDuration = TempRangeDuration( IntRange(68, 72), 2, UoT.weeks ),
           potentialFermentableExtract = 5.0,
           conditioningTempRangeDuration = TempRangeDuration( IntRange(68, 72), 2, UoT.days ),
           keggingTempRangeDuration = TempRangeDuration( IntRange(42, 45), 2, UoT.days ),
           pH = 5.6,
           originalGravityRange = DoubleRange(1.050, 1.070),
           finalGravityRange = DoubleRange(1.010, 1.020),
           srmColorRange = IntRange(6, 12),
           ibuBitternessRange = IntRange(60, 75),
           alcoholByVolumeRange = DoubleRange(6.0, 7.0),
           alcoholByWeightRange = DoubleRange(5.8, 6.8),
           calorieRange = IntRange(180, 200),
           mashEfficiencyRange = IntRange(70, 80),
           brewhouseEfficiencyRange = IntRange(72, 80),
           created = now())

@upickle.implicits.serializeDefaults(true)
final case class Recipe(name: String = "",
                        style: String = "",
                        water: String = "",
                        volume: Volume = Volume(0.0, UoM.gl),
                        grains: List[Grain] = List.empty[Grain],
                        hops: List[Hop] = List.empty[Hop],
                        adjuncts: List[Adjunct] = List.empty[Adjunct],
                        yeasts: List[Yeast] = List.empty[Yeast],
                        mashingTempRangeDuration: TempRangeDuration = TempRangeDuration( IntRange(0, 0), 0, UoT.minutes ),
                        potentialMashExtract: Double = 0.0,
                        boilingTempRangeDuration: TempRangeDuration = TempRangeDuration( IntRange(0, 0), 0, UoT.minutes ),
                        coolingTempRange: IntRange = IntRange(0, 0),
                        fermentingTempRangeDuration: TempRangeDuration = TempRangeDuration( IntRange(0, 0), 0, UoT.weeks ),
                        potentialFermentableExtract: Double = 0.0,
                        conditioningTempRangeDuration: TempRangeDuration = TempRangeDuration( IntRange(0, 0), 0, UoT.days ),
                        keggingTempRangeDuration: TempRangeDuration = TempRangeDuration( IntRange(0, 0), 0, UoT.days ),
                        pH: Double = 0.0,
                        originalGravityRange: DoubleRange = DoubleRange(0.0, 0.0),
                        finalGravityRange: DoubleRange = DoubleRange(0.0, 0.0),
                        srmColorRange: IntRange = IntRange(0, 0),
                        ibuBitternessRange: IntRange = IntRange(0, 0),
                        alcoholByVolumeRange: DoubleRange = DoubleRange(0.0, 0.0),
                        alcoholByWeightRange: DoubleRange = DoubleRange(0.0, 0.0),
                        calorieRange: IntRange = IntRange(0, 0),
                        mashEfficiencyRange: IntRange = IntRange(0, 0),
                        brewhouseEfficiencyRange: IntRange = IntRange(0, 0),
                        created: String = now()) derives CanEqual, JsonSupport:
  val nameProperty = ObjectProperty[String](this, "name", name)
  val styleProperty = ObjectProperty[String](this, "style", style)
  val waterProperty = ObjectProperty[String](this, "water", water)
  val grainsProperty = ObjectProperty[List[Grain]](this, "grains", grains)
  val hopsProperty = ObjectProperty[List[Hop]](this, "hops", hops)
  val adjunctsProperty = ObjectProperty[List[Adjunct]](this, "adjuncts", adjuncts)
  val yeastsProperty = ObjectProperty[List[Yeast]](this, "yeasts", yeasts)
  val mashingTempRangeDurationProperty = ObjectProperty[TempRangeDuration](this, "mashingTempRangeDuration", mashingTempRangeDuration)
  val potentialMashExtractProperty = ObjectProperty[Double](this, "potentialMashExtract", potentialMashExtract)
  val boilingTempRangeDurationProperty = ObjectProperty[TempRangeDuration](this, "boilingTempRangeDuration", boilingTempRangeDuration)
  val coolingTempRangeProperty = ObjectProperty[IntRange](this, "coolingTempRange", coolingTempRange)
  val fermentingTempRangeDurationProperty = ObjectProperty[TempRangeDuration](this, "fermentingTempRangeDuration", fermentingTempRangeDuration)
  val potentialFermentableExtractProperty = ObjectProperty[Double](this, "potentialFermentableExtract", potentialFermentableExtract)
  val conditioningTempRangeDurationProperty = ObjectProperty[TempRangeDuration](this, "conditioningTempRangeDuration", conditioningTempRangeDuration)
  val keggingTempRangeDurationProperty = ObjectProperty[TempRangeDuration](this, "keggingTempRangeDuration", keggingTempRangeDuration)
  val phProperty = ObjectProperty[Double](this, "pH", pH)
  val originalGravityRangeProperty = ObjectProperty[DoubleRange](this, "originalGravityRange", originalGravityRange)
  val finalGravityRangeProperty = ObjectProperty[DoubleRange](this, "finalGravityRange", finalGravityRange)
  val srmColorRangeProperty = ObjectProperty[IntRange](this, "srmColorRange", srmColorRange)
  val ibuBitternessRangeProperty = ObjectProperty[IntRange](this, "ibuBitternessRange", ibuBitternessRange)
  val alcoholByVolumeRangeProperty = ObjectProperty[DoubleRange](this, "alcoholByVolumeRange", alcoholByVolumeRange)
  val alcoholByWeightRangeProperty = ObjectProperty[DoubleRange](this, "alcoholByWeightRange", alcoholByWeightRange)
  val calorieRangeProperty = ObjectProperty[IntRange](this, "calorieRange", calorieRange)
  val mashEfficiencyRangeProperty = ObjectProperty[IntRange](this, "mashEfficiencyRange", mashEfficiencyRange)
  val brewhouseEfficiencyRangeProperty = ObjectProperty[IntRange](this, "brewhouseEfficiencyRange", brewhouseEfficiencyRange)
  val createdProperty = ObjectProperty[String](this, "created", created)
  val recipe = this

object MixinStep:
  def toList: List[String] = MixinStep.values.map(ms => ms.toString).toList

enum MixinStep derives CanEqual, JsonSupport:
  case Mashing, Boiling, Wirlpooling, Fermenting, Conditioning

object Grain:
  given Ordering[Grain] = Ordering.by[Grain, String](g => g.name)

final case class Grain(name: String = Random.alphanumeric.take(7).mkString,
                       weight: Double = 1.0,
                       unit: UoM = UoM.gl,
                       color: Double = 1.0,
                       lovibond: Double = 1.0,
                       mixinMinute: Int = 1,
                       mixinStep: MixinStep = MixinStep.Mashing) derives CanEqual, JsonSupport

object Hop:
  given Ordering[Hop] = Ordering.by[Hop, String](h => h.name)

final case class Hop(name: String = Random.alphanumeric.take(7).mkString,
                     weight: Double = 1.0,
                     volume: Double = 1.0,
                     unit: UoM = UoM.oz,
                     alphaAcid: Double = 1.0,
                     mixinMinute: Int = 1,
                     mixinStep: MixinStep = MixinStep.Boiling) derives CanEqual, JsonSupport // or Whirlpooling or Conditioning
object Adjunct:
  given Ordering[Adjunct] = Ordering.by[Adjunct, String](a => a.name)

final case class Adjunct(name: String = Random.alphanumeric.take(7).mkString,
                         weight: Double = 1.0,
                         unit: UoM = UoM.oz,
                         mixinMinute: Int = 1,
                         mixinStep: MixinStep = MixinStep.Mashing) derives CanEqual, JsonSupport // or Boiling or Conditioning

object Yeast:
  given Ordering[Yeast] = Ordering.by[Yeast, String](y => y.name)

final case class Yeast(name: String = Random.alphanumeric.take(7).mkString,
                       weight: Double = 1.0,
                       unit: UoM = UoM.oz,
                       mixinMinute: Int = 1,
                       mixinStep: MixinStep = MixinStep.Fermenting) derives CanEqual, JsonSupport