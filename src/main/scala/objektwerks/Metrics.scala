package objektwerks

final case class Metrics(id: Long,
                         recipeId: Long,
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