package objektwerks

trait Event

case object Sanitized extends Event

final case class Prepared(metrics: Metrics) extends Event

final case class Malted(metrics: Metrics) extends Event

final case class Milled(metrics: Metrics) extends Event

final case class Mashed(metrics: Metrics) extends Event

final case class Lautered(metrics: Metrics) extends Event

final case class Sparged(metrics: Metrics) extends Event

final case class Boiled(metrics: Metrics) extends Event

final case class Cooled(metrics: Metrics) extends Event

final case class Whirlpooled(metrics: Metrics) extends Event

final case class Fermented(metrics: Metrics) extends Event

final case class Conditioned(metrics: Metrics) extends Event

final case class Packaged(metrics: Metrics) extends Event