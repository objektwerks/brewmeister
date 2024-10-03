package objektwerks

final case class Grain(typeof: String)

final case class Hop(typeof: String)

final case class Adjunct(typeof: String)

final case class Yeast(typeof: String)

final case class Recipe(id: Long,
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
                        boilingHops: List[Hop],
                        whirlpoolingHops: List[Hop],
                        conditioningHops: List[Hop],
                        mashingAdjuncts: List[Adjunct],
                        boilingAdjuncts: List[Adjunct],
                        conditioningAdjuncts: List[Adjunct],
                        yeasts: List[Yeast])