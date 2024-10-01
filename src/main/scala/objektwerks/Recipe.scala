package objektwerks

final case class Style(name: String)

final case class Batch(water: String,
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

final case class Grain(typeof: String)

final case class Hop(typeof: String)

final case class Adjunct(typeof: String)

final case class Yeast(typeof: String)

final case class Recipe(id: Long,
                        style: Style,
                        batch: Batch,
                        grains: List[Grain],
                        hops: List[Hop],
                        adjunts: List[Adjunct],
                        yeasts: List[Yeast])