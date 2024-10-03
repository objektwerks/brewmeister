package objektwerks

enum MixinStep:
  case Mashing, Boiling, Wirlpooling, Fermenting, Conditioning

final case class Grain(typeof: String, amount: Int, mixinStep: String = MixinStep.Mashing.toString)

final case class Hop(typeof: String, amount: Int, mixinStep: String = MixinStep.Boiling.toString) // or Whirlpooling or Conditioning

final case class Adjunct(typeof: String, amount: Int, mixinStep: String = MixinStep.Mashing.toString) // or Boiling or Conditioning

final case class Yeast(typeof: String, amount: Int, mixinStep: String = MixinStep.Fermenting.toString)

final case class Recipe(created: String = now(),
                        style: String,
                        water: String,
                        gallons: Int,
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
                         style: String,
                         water: String,
                         gallons: Int,
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
                         brewhouseEfficiency: Double)