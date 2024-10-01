package objektwerks

object Metrics:
  def empty: Metrics = Metrics(0, 0, (0.0, 0.0), (0.0, 0.0), (0.0, 0.0), 0.0, 0.0, 0.0, 0, 0, 0.0, 0.0, 0, 0.0, 0.0)

final case class Metrics(id: Long,
                         recipeId: Long,
                         mashingTemp: (Double, Double),
                         boilingTemp: (Double, Double),
                         coolingTemp: (Double, Double),
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