package objektwerks

final case class Water(typeof: String)

final case class Style(typeof: String)

final case class Grain(typeof: String)

final case class Hop(id: Long)

final case class Adjunct(id: Long)

final case class Yeast(id: Long)

final case class Recipe(id: Long,
                        water: Water,
                        style: Style,
                        grains: List[Grain],
                        hops: List[Hop],
                        adjunts: List[Adjunct],
                        yeasts: List[Yeast])