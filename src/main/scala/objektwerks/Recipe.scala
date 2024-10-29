package objektwerks

import upickle.default.{ReadWriter => JsonSupport}

object Recipe:
  def default: Recipe =
    Recipe(name = "Objektwerks IPA",
           style = "American IPA",
           water = "spring",
           batchVolume = Volume(5.0, UoM.gl),
           packageVolume = Volume(12.0, UoM.oz),
           mashingTempDuration = TempDuration( IntRange(148, 152), 60, UoT.minutes ),
           potentialMashExtract = 4.0,
           boilingTempDuration = TempDuration( IntRange(148, 152), 60, UoT.minutes ),
           coolingTempRange = IntRange(68, 72),
           fermentatingTempDuration = TempDuration( IntRange(68, 72), 2, UoT.weeks ),
           potentialFermentableExtract = 4.0,
           conditioningTempDuration = TempDuration( IntRange(68, 72), 2, UoT.days ),
           keggingTempDuration = TempDuration( IntRange(42, 45), 2, UoT.days ),
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
           grains = List( Grain("pale ale", 4.0, UoM.lb, 6.0, 1.8, 0) ),
           hops = List( Hop("chinook", 2.0, 10.0, UoM.oz, 13.0, 30) ),
           adjuncts = List.empty[Adjunct],
           yeasts = List( Yeast("Wyeast American Ale 1056", 5.0, UoM.oz, 0) ),
           created = now())

@upickle.implicits.serializeDefaults(true)
final case class Recipe(name: String = "",
                        style: String = "",
                        water: String = "",
                        batchVolume: Volume = Volume(0.0, UoM.gl),
                        packageVolume: Volume = Volume(0.0, UoM.gl),
                        mashingTempDuration: TempDuration = TempDuration(IntRange(0, 0), 0, UoT.minutes ),
                        potentialMashExtract: Double = 0.0,
                        boilingTempDuration: TempDuration = TempDuration(IntRange(0, 0), 0, UoT.minutes ),
                        coolingTempRange: IntRange = IntRange(0, 0),
                        fermentatingTempDuration: TempDuration = TempDuration(IntRange(0, 0), 0, UoT.weeks ),
                        potentialFermentableExtract: Double = 0.0,
                        conditioningTempDuration: TempDuration = TempDuration( IntRange(0, 0), 0, UoT.days ),
                        keggingTempDuration: TempDuration = TempDuration( IntRange(0, 0), 0, UoT.days ),
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
                        grains: List[Grain] = List.empty[Grain],
                        hops: List[Hop] = List.empty[Hop],
                        adjuncts: List[Adjunct] = List.empty[Adjunct],
                        yeasts: List[Yeast] = List.empty[Yeast],
                        created: String = now()) derives JsonSupport

enum MixinStep derives JsonSupport:
  case Mashing, Boiling, Wirlpooling, Fermenting, Conditioning

final case class Grain(typeof: String,
                       weight: Double,
                       unit: UoM,
                       color: Double,
                       lovibond: Double,
                       mixinMinute: Int,
                       mixinStep: MixinStep = MixinStep.Mashing) derives JsonSupport

final case class Hop(typeof: String,
                     weight: Double,
                     volume: Double,
                     unit: UoM,
                     alphaAcid: Double,
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