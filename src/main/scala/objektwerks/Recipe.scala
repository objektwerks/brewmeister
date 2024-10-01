package objektwerks

final case class Style(typeof: String)

final case class Water(typeof: String)

final case class Grain(typeof: String)

final case class Hop(typeof: String)

final case class Adjunct(typeof: String)

final case class Yeast(typeof: String)

final case class Recipe(id: Long,
                        gallons: Int,
                        originalGravity: Double,
                        finalGravity: Double,
                        srmColor: Int,
                        style: Style,
                        water: Water,
                        grains: List[Grain],
                        hops: List[Hop],
                        adjunts: List[Adjunct],
                        yeasts: List[Yeast])