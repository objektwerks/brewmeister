package objektwerks

trait Event

case object Sanitized extends Event

final case class Prepared(session: Session) extends Event

final case class Malted(session: Session) extends Event

final case class Milled(session: Session) extends Event

final case class Mashed(session: Session) extends Event

final case class Lautered(session: Session) extends Event

final case class Sparged(session: Session) extends Event

final case class Boiled(session: Session) extends Event

final case class Cooled(session: Session) extends Event

final case class Whirlpooled(session: Session) extends Event

final case class Fermented(session: Session) extends Event

final case class Conditioned(session: Session) extends Event

final case class Packaged(session: Session) extends Event