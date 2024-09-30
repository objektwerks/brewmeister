package objektwerks

final case class Session(id: Long,
                        recipeId: Long,
                        originalGravity: Double, // 1.000 - 1.072
                        finalGravity: Double, // 1.000 - 1.072
                        color: Int, // 1 - 40
                        hopBitterness: Int, // 0 - 120
                        alcoholByVolume: Double,
                        alcoholByWeight: Double,
                        calories: Double,
                        mashEfficiency: Double,
                        brewhouseEfficiency: Double)