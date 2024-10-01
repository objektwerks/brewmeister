package objektwerks

object Metrics:
  def empty: Metrics = Metrics(0, 0, List(0.0), 0.0, 0.0, 0.0, 0, 0, 0.0, 0.0, 0, 0.0, 0.0)

final case class Metrics(id: Long,
                         recipeId: Long,
                         temperatures: List[Double],
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