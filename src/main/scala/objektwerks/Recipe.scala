package objektwerks

final case class Water(id: Long)

final case class Style(id: Long)

final case class Grain(id: Long)

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