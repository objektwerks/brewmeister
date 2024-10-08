package objektwerks

sealed trait Event

final case class Sanitized(batchId: Int, log: List[String]) extends Event

final case class Prepared(batchId: Int, log: List[String]) extends Event

final case class Malted(batchId: Int, log: List[String]) extends Event

final case class Milled(batchId: Int, log: List[String]) extends Event

final case class Mashed(batchId: Int, log: List[String], pH: Double) extends Event

final case class Lautered(batchId: Int, log: List[String]) extends Event

final case class Sparged(batchId: Int, log: List[String], mashEfficiency: Int) extends Event

final case class Boiled(batchId: Int, log: List[String]) extends Event

final case class Cooled(batchId: Int, log: List[String]) extends Event

final case class Whirlpooled(log: List[String], originalGravity: Double) extends Event

final case class Fermented(log: List[String], finalGravity: Double) extends Event

final case class Conditioned(log: List[String], srmColor: Int) extends Event

final case class Packaged(log: List[String],
                          ibuBitterness: Int,
                          alcoholByVolume: Double,
                          alcoholByWeight: Double,
                          calories: Int,
                          brewhouseEfficiency: Int) extends Event