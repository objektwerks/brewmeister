package objektwerks

final case class Session(id: Long,
                         recipeId: Long,
                         ph: Double,
                         originalGravity: Double,
                         finalGravity: Double,
                         color: Int,
                         hopBitterness: Int,
                         alcoholByVolume: Double,
                         alcoholByWeight: Double,
                         calories: Int,
                         mashEfficiency: Double,
                         brewhouseEfficiency: Double)