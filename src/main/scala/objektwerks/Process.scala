package objektwerks

final case class Process()

sealed trait Step

final case class Sanitizing() extends Step

final case class Preparing() extends Step

final case class Malting() extends Step