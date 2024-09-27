package objektwerks

final case class Malt(id: Long)

final case class Hop(id: Long)

final case class Adjunct(id: Long)

final case class Water(id: Long)

final case class Yeast(id: Long)

final case class Result(id: Long)

final case class Recipe(id: Long,
                        malts: List[Malt],
                        hops: List[Hop],
                        adjunts: List[Adjunct],
                        waters: List[Water],
                        yeasts: List[Yeast],
                        results: List[Result])