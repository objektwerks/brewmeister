package objektwerks

sealed trait Event

case object Sanitized extends Event

final case class Prepared() extends Event

final case class Malted() extends Event

final case class Milled() extends Event

final case class Mashed() extends Event

final case class Lautered() extends Event

final case class Sparged() extends Event

final case class Boiled() extends Event

final case class Cooled() extends Event

final case class Whirlpooled() extends Event

final case class Fermented() extends Event

final case class Conditioned() extends Event

final case class Packaged() extends Event

case object Brewed extends Event