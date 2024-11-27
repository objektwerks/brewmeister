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
           originalGravity = DoubleRange(1.050, 1.070),
           finalGravity = DoubleRange(1.010, 1.020),
           srmColor = IntRange(6, 12),
           ibuBitterness = IntRange(60, 75),
           alcoholByVolume = DoubleRange(6.0, 7.0),
           alcoholByWeight = DoubleRange(5.8, 6.8),
           calories = IntRange(180, 200),
           mashEfficiency = IntRange(70, 80),
           brewhouseEfficiency = IntRange(72, 80),
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
                        originalGravity: DoubleRange = DoubleRange(0.0, 0.0),
                        finalGravity: DoubleRange = DoubleRange(0.0, 0.0),
                        srmColor: IntRange = IntRange(0, 0),
                        ibuBitterness: IntRange = IntRange(0, 0),
                        alcoholByVolume: DoubleRange = DoubleRange(0.0, 0.0),
                        alcoholByWeight: DoubleRange = DoubleRange(0.0, 0.0),
                        calories: IntRange = IntRange(0, 0),
                        mashEfficiency: IntRange = IntRange(0, 0),
                        brewhouseEfficiency: IntRange = IntRange(0, 0),
                        created: String = now()) derives CanEqual, JsonSupport:
  val nameProperty = ObjectProperty[String](this, "name", name)
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