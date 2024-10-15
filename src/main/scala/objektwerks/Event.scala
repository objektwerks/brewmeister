package objektwerks

sealed trait Event

final case class Sanitizing(log: List[String]) extends Event

final case class Sanitized(log: List[String]) extends Event

final case class Preparing(log: List[String]) extends Event

final case class Prepared(log: List[String]) extends Event

final case class Malting(log: List[String]) extends Event

final case class Malted(log: List[String]) extends Event

final case class Milling(log: String) extends Event

final case class Milled(log: List[String]) extends Event

final case class Mashing(pH: Double) extends Event

final case class Mashed(log: List[String],
                        pH: Double) extends Event

final case class Lautered(log: List[String]) extends Event

final case class Sparged(log: List[String],
                         mashEfficiency: Int) extends Event

final case class Boiled(log: List[String]) extends Event

final case class Cooled(log: List[String]) extends Event

final case class Whirlpooled(log: List[String],
                             originalGravity: Double) extends Event

final case class Fermented(log: List[String],
                           finalGravity: Double) extends Event

final case class Conditioned(log: List[String],
                             srmColor: Int) extends Event

final case class Kegged(log: List[String],
                        ibuBitterness: Int,
                        alcoholByVolume: Double,
                        alcoholByWeight: Double,
                        calories: Int,
                        brewhouseEfficiency: Int) extends Event