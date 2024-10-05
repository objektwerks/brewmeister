package objektwerks

import java.time.LocalDateTime

import upickle.default.{ReadWriter => JsonSupport}

def now(): String = LocalDateTime.now.toString
def localDateTime(now: String): LocalDateTime = if now.nonEmpty then LocalDateTime.parse(now) else LocalDateTime.now

enum UoM derives JsonSupport:
  case oz, gl, ml, l, lb, kg

object Process:
  def default: Process =
    Process(recipe = Recipe.default,
            metrics = Metrics(),
            steps = Steps.default)

final case class Process(started: String = now(),
                         completed: String = "",
                         recipe: Recipe,
                         metrics: Metrics,
                         steps: List[Step]) derives JsonSupport

object Steps:
  def default: List[Step] =
    val mashTun = MashTun(5.0, UoM.gl)
    val boilKettle = BoilKettle(5.0, UoM.gl)
    val fermentationKettle = FermentationKettle(5.0, UoM.gl)
    List(
      Sanitizing(),
      Preparing(),
      Malting(),
      Milling(),
      Mashing(container = mashTun),
      Lautering(container = mashTun),
      Sparging(container = mashTun),
      Boiling(container = boilKettle),
      Cooling(container = boilKettle),
      Whirlpooling(container = boilKettle),
      Fermenting(container = fermentationKettle),
      Conditioning(container = fermentationKettle),
      Packaging(container = Keg(5.0, UoM.gl))
    )

sealed trait Step derives JsonSupport:
  def step: Int
  def started: String = now()
  def completed: String = ""

final case class Sanitizing(step: Int = 1) extends Step
final case class Preparing(step: Int = 2) extends Step
final case class Malting(step: Int = 3) extends Step
final case class Milling(step: Int = 4) extends Step
final case class Mashing(step: Int = 5, container: Container) extends Step
final case class Lautering(step: Int = 6, container: Container) extends Step
final case class Sparging(step: Int = 7, container: Container) extends Step
final case class Boiling(step: Int = 8, container: Container) extends Step
final case class Cooling(step: Int = 9, container: Container) extends Step
final case class Whirlpooling(step: Int = 10, container: Container) extends Step
final case class Fermenting(step: Int = 11, container: Container) extends Step
final case class Conditioning(step: Int = 12, container: Container) extends Step
final case class Packaging(step: Int = 13, container: Container) extends Step

sealed trait Container derives JsonSupport:
  def volume: Double
  def unit: UoM

final case class MashTun(volume: Double, unit: UoM) extends Container
final case class BoilKettle(volume: Double, unit: UoM) extends Container
final case class FermentationKettle(volume: Double, unit: UoM) extends Container
final case class Keg(volume: Double, unit: UoM) extends Container

enum MixinStep derives JsonSupport:
  case Mashing, Boiling, Wirlpooling, Fermenting, Conditioning

final case class Grain(typeof: String,
                       amount: Double,
                       unit: UoM,
                       mixinMinute: Int,
                       mixinStep: MixinStep = MixinStep.Mashing) derives JsonSupport

final case class Hop(typeof: String,
                     amount: Double,
                     unit: UoM,
                     mixinMinute: Int,
                     mixinStep: MixinStep = MixinStep.Boiling) derives JsonSupport // or Whirlpooling or Conditioning

final case class Adjunct(typeof: String,
                         amount: Double,
                         unit: UoM,
                         mixinMinute: Int,
                         mixinStep: MixinStep = MixinStep.Mashing) derives JsonSupport // or Boiling or Conditioning

final case class Yeast(typeof: String,
                       amount: Double,
                       unit: UoM,
                       mixinMinute: Int,
                       mixinStep: MixinStep = MixinStep.Fermenting) derives JsonSupport

object Recipe:
  def default: Recipe =
    Recipe(created = now(),
           style = "American IPA",
           water = "spring",
           gallons = 5.0,
           mashingTemp = (148, 152),
           mashingDuration = 60,
           boilingTemp = (148, 152),
           boilingDuration = 60,
           coolingTemp = (68, 72),
           pH = 5.6,
           originalGravity = (1.057, 1.67),
           finalGravity = (1.010, 1.015),
           srmColor = (6, 8),
           ibuBitterness = (60, 75),
           alcoholByVolume = (6.0, 7.0),
           alcoholByWeight = (5.8, 6.8),
           calories = (180, 200),
           mashEfficiency = (70, 80),
           brewhouseEfficiency = (72, 80),
           grains = List( Grain("pale ale", 4.0, UoM.lb, 0) ),
           hops = List( Hop("amarillo", 2.0, UoM.oz, 0), Hop("cascade", 2.0, UoM.oz, 15), Hop("chinook", 2.0, UoM.oz, 30) ),
           adjuncts = List.empty[Adjunct],
           yeasts = List( Yeast("Wyeast American Ale 1056", 5.0, UoM.oz, 0) )
    )

final case class Recipe(created: String = now(),
                        style: String,
                        water: String,
                        gallons: Double,
                        mashingTemp: (Int, Int),
                        mashingDuration: Int,
                        boilingTemp: (Int, Int),
                        boilingDuration: Int,
                        coolingTemp: (Int, Int),
                        pH: Double,
                        originalGravity: (Double, Double),
                        finalGravity: (Double, Double),
                        srmColor: (Int, Int),
                        ibuBitterness: (Int, Int),
                        alcoholByVolume: (Double, Double),
                        alcoholByWeight: (Double, Double),
                        calories: (Int, Int),
                        mashEfficiency: (Int, Int),
                        brewhouseEfficiency: (Int, Int),
                        grains: List[Grain],
                        hops: List[Hop],
                        adjuncts: List[Adjunct],
                        yeasts: List[Yeast]) derives JsonSupport

object Metrics:
  def default: Metrics =
    Metrics(created = now(),
            style = "American IPA",
            gallons = 5.0,
            pH = 5.6,
            originalGravity = 1.060,
            finalGravity = 1.012,
            srmColor = 7,
            ibuBitterness = 68,
            alcoholByVolume = 6.4,
            alcoholByWeight = 6.0,
            calories = 190,
            mashEfficiency = 70,
            brewhouseEfficiency = 71)                            

final case class Metrics(created: String = now(),
                         style: String = "",
                         gallons: Double = 0.0,
                         pH: Double = 0.0,
                         originalGravity: Double = 0.0,
                         finalGravity: Double = 0.0,
                         srmColor: Int = 0,
                         ibuBitterness: Int = 0,
                         alcoholByVolume: Double = 0.0,
                         alcoholByWeight: Double = 0.0,
                         calories: Int = 0,
                         mashEfficiency: Int = 0,
                         brewhouseEfficiency: Int = 0) derives JsonSupport