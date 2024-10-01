package objektwerks

object Metrics:
  def empty: Metrics = Metrics(0, 0, "", 0, Range(0, 0), Range(0, 0), Range(0, 0), 0.0, 0.0, 0.0, 0, 0, 0.0, 0.0, 0, 0.0, 0.0)

final case class Metrics(id: Long,
                         recipeId: Long,
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