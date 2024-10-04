package objektwerks

import java.time.LocalDateTime

def now(): String = LocalDateTime.now.toString
def localDateTime(now: String): LocalDateTime = if now.nonEmpty then LocalDateTime.parse(now) else LocalDateTime.now

sealed trait Entity

final case class Process(started: String = now(),
                         completed: String = "",
                         recipe: Recipe = Recipe.default,
                         metrics: Metrics = Metrics(),
                         steps: List[Step] = Steps.default) extends Entity

object Steps:
  def default: List[Step] =
    List(
      Sanitizing(),
      Preparing(),
      Malting(),
      Milling(),
      Mashing(),
      Lautering(),
      Sparging(),
      Boiling(),
      Cooling(),
      Whirlpooling(),
      Fermenting(),
      Conditioning(),
      Packaging()
    )

sealed trait Step extends Entity:
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
  case oz, gl, ml, l

object Container:
  def mashTun: Container = Container(ContainerType.mashTun, 5.0, UnitType.gl)
  def boilKettle: Container = Container(ContainerType.boilKettle, 5.0, UnitType.gl)
  def fermentationKettle: Container = Container(ContainerType.fermentationKettle, 5.0, UnitType.gl)
  def bottle: Container = Container(ContainerType.bottle, 12.0, UnitType.oz)
  def keg: Container = Container(ContainerType.keg, 5.0, UnitType.gl)

final case class Container(typeof: ContainerType,
                           volume: Double,
                           unit: UnitType) extends Entity

enum MixinStep:
  case Mashing, Boiling, Wirlpooling, Fermenting, Conditioning

final case class Grain(typeof: String,
                       amount: Double,
                       unit: UnitType,
                       mixinStep: MixinStep = MixinStep.Mashing) extends Entity

final case class Hop(typeof: String,
                     amount: Double,
                     unit: UnitType,
                     mixinStep: MixinStep = MixinStep.Boiling) extends Entity // or Whirlpooling or Conditioning

final case class Adjunct(typeof: String,
                         amount: Double,
                         unit: UnitType,
                         mixinStep: MixinStep = MixinStep.Mashing) extends Entity // or Boiling or Conditioning

final case class Yeast(typeof: String,
                       amount: Double,
                       unit: UnitType,
                       mixinStep: MixinStep = MixinStep.Fermenting) extends Entity

object Recipe:
  def default: Recipe =
    Recipe(created = now(),
           style = "",
           water = "",
           gallons = 0.0,
           mashingTemp = Range(0, 0),
           boilingTemp = Range(0, 0),
           coolingTemp = Range(0, 0),
           pH = 0.0,
           originalGravity = 0.0,
           finalGravity = 0.0,
           color = 0,
           bitterness = 0,
           alcoholByVolume = 0.0,
           alcoholByWeight = 0.0,
           calories = 0,
           mashEfficiency = 0.0,
           brewhouseEfficiency = 0.0,
           grains = List.empty[Grain],
           hops = List.empty[Hop],
           adjuncts = List.empty[Adjunct],
           yeasts = List.empty[Yeast])
    

final case class Recipe(created: String = now(),
                        style: String,
                        water: String,
                        gallons: Double,
                        mashingTemp: Range,
                        boilingTemp: Range,
                        coolingTemp: Range,
                        pH: Double,
                        originalGravity: Double,
                        finalGravity: Double,
                        color: Int,
                        bitterness: Int,
                        alcoholByVolume: Double,
                        alcoholByWeight: Double,
                        calories: Int,
                        mashEfficiency: Double,
                        brewhouseEfficiency: Double,
                        grains: List[Grain],
                        hops: List[Hop],
                        adjuncts: List[Adjunct],
                        yeasts: List[Yeast]) extends Entity

final case class Metrics(created: String = now(),
                         style: String = "",
                         water: String = "",
                         gallons: Int = 0,
                         mashingTemp: Range = Range(0, 0),
                         boilingTemp: Range = Range(0, 0),
                         coolingTemp: Range = Range(0, 0),
                         pH: Double = 0.0,
                         originalGravity: Double = 0.0,
                         finalGravity: Double = 0.0,
                         color: Int = 0,
                         bitterness: Int = 0,
                         alcoholByVolume: Double = 0.0,
                         alcoholByWeight: Double = 0.0,
                         calories: Int = 0,
                         mashEfficiency: Double = 0.0,
                         brewhouseEfficiency: Double = 0.0) extends Entity