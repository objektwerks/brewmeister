package objektwerks

trait Event

case object Sanitized extends Event

final case class Prepared(recipe: Recipe) extends Event

final case class Malted(recipe: Recipe) extends Event

//, Milled, Mashed, Lautered, Sparged, Boiled, Cooled, Whirlpooled, Fermented, Conditioned, Packaged