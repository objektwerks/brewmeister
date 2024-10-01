package objektwerks

final case class Style(typeof: String,
                       gallons: Int,
                       originalGravity: Double,
                       finalGravity: Double,
                       color: Int,
                       bitterness: Int)

final case class Batch(water: String,
                       gallons: Int,
                       originalGravity: Double,
                       finalGravity: Double,
                       color: Int,
                       bitterness: Int)

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