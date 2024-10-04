package objektwerks

import java.time.LocalDateTime

import upickle.default.*

def now(): String = LocalDateTime.now.toString
def localDateTime(now: String): LocalDateTime = if now.nonEmpty then LocalDateTime.parse(now) else LocalDateTime.now

enum UnitType derives ReadWriter:
  case oz, gl, ml, l, lb, kg

final case class Process(started: String = now(),
                         completed: String = "",
                         recipe: Recipe = Recipe.default,
                         metrics: Metrics = Metrics(),
                         steps: List[Step] = Steps.default) derives ReadWriter

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

sealed trait Step derives ReadWriter:
  def step: Int
  def started: String = now()
  def completed: String = ""

final case class Sanitizing(step: Int = 1) extends Step
final case class Preparing(step: Int = 2) extends Step
final case class Malting(step: Int = 3) extends Step
final case class Milling(step: Int = 4) extends Step
final case class Mashing(step: Int = 5, mashTun: Container = MashTun(5.0, UnitType.gl)) extends Step
final case class Lautering(step: Int = 6, mashTun: Container = MashTun(5.0, UnitType.gl)) extends Step
final case class Sparging(step: Int = 7, mashTun: Container = MashTun(5.0, UnitType.gl)) extends Step
final case class Boiling(step: Int = 8, boilKettle: Container = BoilKettle(5.0, UnitType.gl)) extends Step
final case class Cooling(step: Int = 9, boilKettle: Container = BoilKettle(5.0, UnitType.gl)) extends Step
final case class Whirlpooling(step: Int = 10, boilKettle: Container = BoilKettle(5.0, UnitType.gl)) extends Step
final case class Fermenting(step: Int = 11, fermentationKettle: Container = FermentationKettle(5.0, UnitType.gl)) extends Step
final case class Conditioning(step: Int = 12, fermentationKettle: Container = FermentationKettle(5.0, UnitType.gl)) extends Step
final case class Packaging(step: Int = 13, bottleOrKeg: Container = Keg(5.0, UnitType.gl)) extends Step

sealed trait Container derives ReadWriter:
  def volume: Double
  def unit: UnitType

final case class MashTun(volume: Double, unit: UnitType) extends Container
final case class BoilKettle(volume: Double, unit: UnitType) extends Container
final case class FermentationKettle(volume: Double, unit: UnitType) extends Container
final case class Bottle(volume: Double, unit: UnitType) extends Container
final case class Keg(volume: Double, unit: UnitType) extends Container

enum MixinStep derives ReadWriter:
  case Mashing, Boiling, Wirlpooling, Fermenting, Conditioning

final case class Grain(typeof: String,
                       amount: Double,
                       unit: UnitType,
                       mixinStep: MixinStep = MixinStep.Mashing) derives ReadWriter

final case class Hop(typeof: String,
                     amount: Double,
                     unit: UnitType,
                     mixinStep: MixinStep = MixinStep.Boiling) derives ReadWriter // or Whirlpooling or Conditioning

final case class Adjunct(typeof: String,
                         amount: Double,
                         unit: UnitType,
                         mixinStep: MixinStep = MixinStep.Mashing) derives ReadWriter // or Boiling or Conditioning

final case class Yeast(typeof: String,
                       amount: Double,
                       unit: UnitType,
                       mixinStep: MixinStep = MixinStep.Fermenting) derives ReadWriter

object Recipe:  
  def default: Recipe =
    Recipe(created = now(),
           style = "American IPA",
           water = "spring",
           gallons = 5.0,
           mashingTemp = (0, 0),
           boilingTemp = (0, 0),
           coolingTemp = (0, 0),
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
           grains = List( Grain("pale ale", 5.0, UnitType.lb) ),
           hops = List( Hop("cascade", 5.0, UnitType.oz), Hop("chinook", 5.0, UnitType.oz), Hop("simcoe", 5.0, UnitType.oz) ),
           adjuncts = List.empty[Adjunct],
           yeasts = List(  Yeast("Wyeast American Ale 1056", 5.0, UnitType.oz) )
    )
    

final case class Recipe(created: String = now(),
                        style: String,
                        water: String,
                        gallons: Double,
                        mashingTemp: (Int, Int),
                        boilingTemp: (Int, Int),
                        coolingTemp: (Int, Int),
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
                        yeasts: List[Yeast]) derives ReadWriter

final case class Metrics(created: String = now(),
                         style: String = "",
                         water: String = "",
                         gallons: Int = 0,
                         mashingTemp: (Int, Int) = (0, 0),
                         boilingTemp: (Int, Int) = (0, 0),
                         coolingTemp: (Int, Int) = (0, 0),
                         pH: Double = 0.0,
                         originalGravity: Double = 0.0,
                         finalGravity: Double = 0.0,
                         color: Int = 0,
                         bitterness: Int = 0,
                         alcoholByVolume: Double = 0.0,
                         alcoholByWeight: Double = 0.0,
                         calories: Int = 0,
                         mashEfficiency: Double = 0.0,
                         brewhouseEfficiency: Double = 0.0) derives ReadWriter