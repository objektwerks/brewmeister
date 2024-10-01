package objektwerks

sealed trait Event

case object Sanitizing extends Event

case object Sanitized extends Event

case object Preparing extends Event

case object Prepared extends Event

case object Malting extends Event

final case class Malted() extends Event

case object Milling extends Event

final case class Milled() extends Event

case object Mashing extends Event

final case class Mashed() extends Event

case object Lautering extends Event

final case class Lautered() extends Event

case object Sparging extends Event

final case class Sparged() extends Event

case object Boiling extends Event

final case class Boiled() extends Event

case object Cooling extends Event

final case class Cooled() extends Event

case object Whirlpooling extends Event

final case class Whirlpooled() extends Event

case object Fermenting extends Event

final case class Fermented() extends Event

case object Conditioning extends Event

final case class Conditioned() extends Event

case object Packaging extends Event

final case class Packaged() extends Event