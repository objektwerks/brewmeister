package objektwerks

sealed trait Event

final case class Sanitized() extends Event

final case class Prepared() extends Event

final case class Malted() extends Event

final case class Milled() extends Event

final case class Mashed() extends Event

final case class Lautered() extends Event

final case class Sparged(mashEfficiency: Int) extends Event

final case class Boiled() extends Event

final case class Cooled() extends Event

final case class Whirlpooled(originalGravity: Double) extends Event

final case class Fermented(finalGravity: Double) extends Event

final case class Conditioned(srmColor: Int) extends Event

final case class Packaged(ibuBitterness: Int,
                          alcoholByVolume: Double,
                          alcoholByWeight: Double,
                          calories: Int,
                          brewhouseEfficiency: Int) extends Event

final case class Brewed(events: List[Event]) extends Event