package objektwerks

import upickle.default.{ReadWriter => JsonSupport}

object Recipe:
  def default: Recipe =
    Recipe(style = "American IPA",
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
           grains = List( Grain("pale ale", 4.0, UoM.lb, 6.0, 1.8, 0) ),
           hops = List( Hop("chinook", 2.0, 10.0, UoM.oz, 13.0, 30) ),
           adjuncts = List.empty[Adjunct],
           yeasts = List( Yeast("Wyeast American Ale 1056", 5.0, UoM.oz, 0) ) )

final case class Recipe(created: String = now(),
                        style: String,
                        water: String,
                        batchVolume: Volume,
                        packageVolume: Volume,
                        mashingTempDuration: TempDuration,
                        potentialMashExtract: Double,
                        boilingTempDuration: TempDuration,
                        coolingTempRange: IntRange,
                        fermentatingTempDuration: TempDuration,
                        potentialFermentableExtract: Double,
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