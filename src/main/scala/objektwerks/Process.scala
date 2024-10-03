package objektwerks

final case class Process()

sealed trait Step

final case class Sanitizing() extends Step