package objektwerks

final case class Process(id: Long,
                         recipe: Recipe,
                         metrics: Metrics,
                         steps: List[Step],
                         currentStep: CurrentStep)

final case class CurrentStep(processId: Long, stepId: Long)

sealed trait Step

final case class Sanitizing(id: Long, processId: Long) extends Step

final case class Preparing(id: Long, processId: Long) extends Step

final case class Malting(id: Long, processId: Long) extends Step

final case class Milling(id: Long, processId: Long) extends Step

final case class Mashing(id: Long, processId: Long, mashTun: Container) extends Step

final case class Lautering(id: Long, processId: Long, mashTun: Container) extends Step

final case class Sparging(id: Long, processId: Long, mashTun: Container) extends Step

final case class Boiling(id: Long, processId: Long, boilKettle: Container) extends Step

final case class Cooling(id: Long, processId: Long, boilKettle: Container) extends Step

final case class Whirlpooling(id: Long, processId: Long, boilKettle: Container) extends Step

final case class Fermenting(id: Long, processId: Long, fermentationKettle: Container) extends Step

final case class Conditioning(id: Long, processId: Long, fermentationKettle: Container) extends Step

final case class Packaging(id: Long, processId: Long, bottleOrKeg: Container) extends Step

enum ContainerType:
  case mashTun, boilKettle, fermentationKettle, bottle, keg

enum UnitType:
  case oz, gl

final case class Container(id: Long,
                           stepId: Long,
                           typeof: ContainerType,
                           volume: Double,
                           unit: UnitType)