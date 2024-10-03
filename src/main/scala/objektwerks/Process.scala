package objektwerks

import java.time.LocalDateTime

def now(): String = LocalDateTime.now.toString
def localDateTime(now: String): LocalDateTime = if now.nonEmpty then LocalDateTime.parse(now) else LocalDateTime.now

final case class Process(recipe: Recipe,
                         metrics: Metrics,
                         steps: List[Step],
                         started: String = now(),
                         completed: String = "")

sealed trait Step:
  def step: Int
  def started: String = now()
  def completed: String = ""

final case class Sanitizing(step: Int = 1) extends Step

final case class Preparing(step: Int = 2) extends Step

final case class Malting(step: Int = 3) extends Step

final case class Milling(step: Int = 4) extends Step

final case class Mashing(step: Int = 5, mashTun: Container = Container.mashTun) extends Step

final case class Lautering(step: Int = 6, mashTun: Container = Container.mashTun) extends Step

final case class Sparging(step: Int = 7, mashTun: Container = Container.mashTun) extends Step

final case class Boiling(step: Int = 8, boilKettle: Container = Container.boilKettle) extends Step

final case class Cooling(step: Int = 9, boilKettle: Container = Container.boilKettle) extends Step

final case class Whirlpooling(step: Int = 10, boilKettle: Container = Container.boilKettle) extends Step

final case class Fermenting(step: Int = 11, fermentationKettle: Container = Container.fermentationKettle) extends Step

final case class Conditioning(step: Int = 12, fermentationKettle: Container = Container.fermentationKettle) extends Step

final case class Packaging(step: Int = 13, bottleOrKeg: Container = Container.keg) extends Step

enum ContainerType:
  case mashTun, boilKettle, fermentationKettle, bottle, keg

enum UnitType:
  case oz, gl

object Container:
  def mashTun = Container(ContainerType.mashTun, 5.0, UnitType.gl)
  def boilKettle = Container(ContainerType.boilKettle, 5.0, UnitType.gl)
  def fermentationKettle = Container(ContainerType.fermentationKettle, 5.0, UnitType.gl)
  def bottle = Container(ContainerType.bottle, 12, UnitType.oz)
  def keg = Container(ContainerType.keg, 5.0, UnitType.gl)

final case class Container(typeof: ContainerType,
                           volume: Double,
                           unit: UnitType)