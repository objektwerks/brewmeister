package objektwerks

sealed trait Event

final case class Sanitized(log: List[String]) extends Event

final case class Prepared(log: List[String]) extends Event

final case class Malted(log: List[String]) extends Event

final case class Milled(log: List[String]) extends Event

final case class Mashed(log: List[String]) extends Event

final case class MashingTempPhLogged(mashingTemp: Int,
                                     pH: Double) extends Event

final case class Lautered(log: List[String]) extends Event

final case class Sparged(log: List[String]) extends Event

final case class MashEfficiencyLogged(mashEfficiency: Int) extends Event

final case class Boiled(log: List[String]) extends Event

final case class Cooled(log: List[String]) extends Event

final case class Whirlpooled(log: List[String]) extends Event

final case class BoilingCoolingTempOriginalGravityLogged(boilingTemp: Int,
                                                         coolingTemp: Int,
                                                         originalGravity: Double) extends Event

final case class Fermented(log: List[String]) extends Event

final case class FermentingTempFinalGravityLogged(fermentingTemp: Int,
                                                  finalGravity: Double) extends Event

final case class Conditioned(log: List[String]) extends Event

final case class ConditioningTempSrmColorLogged(conditioningTemp: Int,
                                                srmColor: Int) extends Event

final case class Kegged(ibuBitterness: Int,
                        alcoholByVolume: Double,
                        alcoholByWeight: Double,
                        calories: Int,
                        log: List[String]) extends Event

final case class KeggingTempBrewhouseEfficiencyLogged(keggingTemp: Int,
                                                      brewhouseEfficiency: Int) extends Event