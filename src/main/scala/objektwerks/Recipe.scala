package objektwerks

enum UnitType:
  case oz, gl, ml, l

enum MixinStep:
  case Mashing, Boiling, Wirlpooling, Fermenting, Conditioning

final case class Grain(typeof: String, amount: Int, mixinStep: MixinStep = MixinStep.Mashing)

final case class Hop(typeof: String, amount: Int, mixinStep: MixinStep = MixinStep.Boiling) // or Whirlpooling or Conditioning

final case class Adjunct(typeof: String, amount: Int, mixinStep: MixinStep = MixinStep.Mashing) // or Boiling or Conditioning

final case class Yeast(typeof: String, amount: Int, mixinStep: MixinStep = MixinStep.Fermenting)

final case class Recipe(created: String = now(),
                        style: String = "",
                        water: String = "",
                        gallons: Int = 1,
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
                        brewhouseEfficiency: Double = 0.0,
                        grains: List[Grain] = List.empty[Grain],
                        hops: List[Hop] = List.empty[Hop],
                        adjuncts: List[Adjunct] = List.empty[Adjunct],
                        yeasts: List[Yeast] = List.empty[Yeast])

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