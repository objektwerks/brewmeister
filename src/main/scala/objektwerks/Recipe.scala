package objektwerks

final case class Grain(id: Long, recipeId: Long, typeof: String, amount: Int, step: Step)

final case class Hop(id: Long, recipeId: Long, typeof: String, amount: Int, step: Step)

final case class Adjunct(id: Long, recipeId: Long, typeof: String, amount: Int, step: Step)

final case class Yeast(id: Long, recipeId: Long, typeof: String, amount: Int, step: Step)

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
                        hops: List[Hop],
                        adjuncts: List[Adjunct],
                        yeasts: List[Yeast])

final case class Metrics(id: Long,
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