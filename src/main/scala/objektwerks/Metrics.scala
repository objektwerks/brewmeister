package objektwerks

final case class Metrics(id: Long,
                         recipeId: Long,
                         pH: Double,
                         originalGravity: Double,
                         finalGravity: Double,
                         color: Int,
                         hopBitterness: Int,
                         alcoholByVolume: Double,
                         alcoholByWeight: Double,
                         calories: Int,
                         mashEfficiency: Double,
                         brewhouseEfficiency: Double)