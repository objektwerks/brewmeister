package objektwerks

final case class Process(id: Long, steps: List[Step])

sealed trait Step

final case class Sanitizing() extends Step

final case class Preparing() extends Step

final case class Malting() extends Step

final case class Milling() extends Step

final case class Mashing(mashTun: Container) extends Step

final case class Lautering(mashTun: Container) extends Step

final case class Sparging(mashTun: Container) extends Step

final case class Boiling(boilKettle: Container) extends Step

final case class Cooling(boilKettle: Container) extends Step

final case class Whirlpooling(boilKettle: Container) extends Step

final case class Fermenting(fermentationKettle: Container) extends Step

final case class Conditioning(fermentationKettle: Container) extends Step

final case class Packaging(container: Container) extends Step

enum ContainerType:
  case mashTun, boilKettle, fermentationKettle, bottle, keg

enum Unit:
  case oz, gl, l

final case class Container(id: Long,
                           container: ContainerType,
                           volume: Double,
                           unit: Unit)