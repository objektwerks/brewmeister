package objektwerks

trait Event

case object Sanitized extends Event

final case class Prepared(result: Result) extends Event

final case class Malted(result: Result) extends Event

final case class Milled(result: Result) extends Event

final case class Mashed(result: Result) extends Event

final case class Lautered(result: Result) extends Event

final case class Sparged(result: Result) extends Event

final case class Boiled(result: Result) extends Event

final case class Cooled(result: Result) extends Event

final case class Whirlpooled(result: Result) extends Event

final case class Fermented(result: Result) extends Event

final case class Conditioned(result: Result) extends Event

final case class Packaged(result: Result) extends Event