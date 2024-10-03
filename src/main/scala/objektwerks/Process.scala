package objektwerks

final case class Process()

sealed trait Step

final case class Sanitizing() extends Step

final case class Preparing() extends Step

final case class Malting() extends Step

final case class Milling() extends Step

final case class Mashing() extends Step

final case class Lautering() extends Step

final case class Sparging() extends Step