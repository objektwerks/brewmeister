package objektwerks

enum Unit:
  case oz, gl, l

sealed trait Container:
  def volume: Double
  def unit: Unit

final case class MashTun(volume: Double, unit: Unit) extends Container

final case class BoilKettle(volume: Double, unit: Unit) extends Container

final case class FementationKettle(volume: Double, unit: Unit) extends Container

final case class Bottle(volume: Double, unit: Unit) extends Container

final case class Keg(volume: Double, unit: Unit) extends Container