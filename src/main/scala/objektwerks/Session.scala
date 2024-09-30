package objektwerks

final case class Session(id: Long,
                         recipeId: Long,
                         originalGravity: Double, // 1.000 - 1.072
                         finalGravity: Double, // 1.000 - 1.072
                         color: Int, // 1 - 40
                         hopBitterness: Int, // 0 - 120
                         alcoholByVolume: Double, // 3.0 - 13.0 %
                         alcoholByWeight: Double, // 3.0 - 10.0 %
                         calories: Int, // 10 - 600
                         mashEfficiency: Double, // 70.0 - 85 %
                         brewhouseEfficiency: Double) // 68.0 - 72.0 %