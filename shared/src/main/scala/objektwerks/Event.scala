package objektwerks

trait Event

case object Sanitized extends Event

final case class Prepared(recipe: Recipe) extends Event

final case class Malted(recipe: Recipe) extends Event

final case class Milled(recipe: Recipe) extends Event

final case class Mashed(recipe: Recipe) extends Event

final case class Lautered(recipe: Recipe) extends Event

//Sparged, Boiled, Cooled, Whirlpooled, Fermented, Conditioned, Packaged