package objektwerks

final case class Process(id: Long, steps: List[Step])

sealed trait Step

final case class Sanitizing() extends Step

final case class Preparing() extends Step

final case class Malting() extends Step

final case class Milling() extends Step

final case class Mashing(mashTun: MashTun) extends Step

final case class Lautering(mashTun: MashTun) extends Step

final case class Sparging(mashTun: MashTun) extends Step

final case class Boiling(boilKettle: BoilKettle) extends Step

final case class Cooling(boilKettle: BoilKettle) extends Step

final case class Whirlpooling(boilKettle: BoilKettle) extends Step

final case class Fermenting(fermentationKettle: FementationKettle) extends Step

final case class Conditioning(fermentationKettle: FementationKettle) extends Step

final case class Packaging(container: Bottle | Keg) extends Step

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