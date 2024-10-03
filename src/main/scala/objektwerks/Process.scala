package objektwerks

final case class Process(steps: List[Step])

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

final case class Conditioning() extends Step

final case class Packaging() extends Step