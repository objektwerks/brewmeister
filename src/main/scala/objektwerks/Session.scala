package objektwerks

final case class Session(id: Long,
                        recipeId: Long,
                        originalGravity: Double,
                        finalGravity: Double,
                        color: Int,
                        hopBitterness: Double,
                        alcoholByVolume: Double,
                        alcoholByWeight: Double,
                        calories: Double,
                        mashEfficiency: Double,
                        brewhouseEfficiency: Double)