package objektwerks

final case class Session(id: Long,
                        recipeId: Long,
                        originalGravity: Double,
                        finalGravity: Double,
                        color: Double,
                        hopBitterness: Double,
                        alcoholByVolume: Double,
                        alcoholByWeight: Double,
                        calories: Double,
                        mashEfficiency: Double)