package objektwerks

sealed trait Container

final case class MashTun() extends Container

final case class BoilKettle() extends Container

final case class FementationKettle() extends Container

final case class Bottle() extends Container

final case class Keg() extends Container