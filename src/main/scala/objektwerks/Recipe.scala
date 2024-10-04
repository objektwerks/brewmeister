package objektwerks

enum UnitType:
  case oz, gl, ml, l

enum MixinStep:
  case Mashing, Boiling, Wirlpooling, Fermenting, Conditioning

final case class Grain(typeof: String, amount: Double, unit: UnitType, mixinStep: MixinStep = MixinStep.Mashing)

final case class Hop(typeof: String, amount: Double, unit: UnitType, mixinStep: MixinStep = MixinStep.Boiling) // or Whirlpooling or Conditioning

final case class Adjunct(typeof: String, amount: Double, unit: UnitType, mixinStep: MixinStep = MixinStep.Mashing) // or Boiling or Conditioning

final case class Yeast(typeof: String, amount: Double, unit: UnitType, mixinStep: MixinStep = MixinStep.Fermenting)

object Recipe:
  def default: Recipe =
    Recipe(created = now(),
           style = "",
           water = "",
           gallons = 1,
           mashingTemp = Range(0, 0),
           boilingTemp = Range(0, 0),
           coolingTemp = Range(0, 0),
           pH = 0.0,
           originalGravity = 0.0,
           finalGravity = 0.0,
           color = 0,
           bitterness = 0,
           alcoholByVolume = 0.0,
           alcoholByWeight = 0.0,
           calories = 0,
           mashEfficiency = 0.0,
           brewhouseEfficiency = 0.0,
           grains = List.empty[Grain],
           hops = List.empty[Hop],
           adjuncts = List.empty[Adjunct],
           yeasts = List.empty[Yeast])
    

final case class Recipe(created: String = now(),
                        style: String,
                        water: String,
                        gallons: Double,
                        mashingTemp: Range,
                        boilingTemp: Range,
                        coolingTemp: Range,
                        pH: Double,
                        originalGravity: Double,
                        finalGravity: Double,
                        color: Int,
                        bitterness: Int,
                        alcoholByVolume: Double,
                        alcoholByWeight: Double,
                        calories: Int,
                        mashEfficiency: Double,
                        brewhouseEfficiency: Double,
                        grains: List[Grain],
                        hops: List[Hop],
                        adjuncts: List[Adjunct],
                        yeasts: List[Yeast])

final case class Metrics(created: String = now(),
                         style: String = "",
                         water: String = "",
                         gallons: Int = 0,
                         mashingTemp: Range = Range(0, 0),
                         boilingTemp: Range = Range(0, 0),
                         coolingTemp: Range = Range(0, 0),
                         pH: Double = 0.0,
                         originalGravity: Double = 0.0,
                         finalGravity: Double = 0.0,
                         color: Int = 0,
                         bitterness: Int = 0,
                         alcoholByVolume: Double = 0.0,
                         alcoholByWeight: Double = 0.0,
                         calories: Int = 0,
                         mashEfficiency: Double = 0.0,
                         brewhouseEfficiency: Double = 0.0)