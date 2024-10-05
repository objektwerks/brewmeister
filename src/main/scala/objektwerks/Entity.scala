package objektwerks

import java.time.LocalDateTime

import upickle.default.*

def now(): String = LocalDateTime.now.toString
def localDateTime(now: String): LocalDateTime = if now.nonEmpty then LocalDateTime.parse(now) else LocalDateTime.now

enum UoM derives ReadWriter:
  case oz, gl, ml, l, lb, kg

final case class Process(started: String = now(),
                         completed: String = "",
                         recipe: Recipe = Recipe.default,
                         metrics: Metrics = Metrics(),
                         steps: List[Step] = Steps.default) derives ReadWriter

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
      Mashing(mashTun = mashTun),
      Lautering(mashTun = mashTun),
      Sparging(mashTun = mashTun),
      Boiling(boilKettle = boilKettle),
      Cooling(boilKettle = boilKettle),
      Whirlpooling(boilKettle = boilKettle),
      Fermenting(fermentationKettle = fermentationKettle),
      Conditioning(fermentationKettle = fermentationKettle),
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
final case class Mashing(step: Int = 5, mashTun: MashTun) extends Step
final case class Lautering(step: Int = 6, mashTun: MashTun) extends Step
final case class Sparging(step: Int = 7, mashTun: MashTun) extends Step
final case class Boiling(step: Int = 8, boilKettle: BoilKettle) extends Step
final case class Cooling(step: Int = 9, boilKettle: BoilKettle) extends Step
final case class Whirlpooling(step: Int = 10, boilKettle: BoilKettle) extends Step
final case class Fermenting(step: Int = 11, fermentationKettle: FermentationKettle) extends Step
final case class Conditioning(step: Int = 12, fermentationKettle: FermentationKettle) extends Step
final case class Packaging(step: Int = 13, keg: Keg = Keg(5.0, UoM.gl)) extends Step

sealed trait Container derives ReadWriter:
  def volume: Double
  def unit: UoM

final case class MashTun(volume: Double, unit: UoM) extends Container
final case class BoilKettle(volume: Double, unit: UoM) extends Container
final case class FermentationKettle(volume: Double, unit: UoM) extends Container
final case class Keg(volume: Double, unit: UoM) extends Container

enum MixinStep derives ReadWriter:
  case Mashing, Boiling, Wirlpooling, Fermenting, Conditioning

final case class Grain(typeof: String,
                       amount: Double,
                       unit: UoM,
                       mixinMinute: Int,
                       mixinStep: MixinStep = MixinStep.Mashing) derives ReadWriter

final case class Hop(typeof: String,
                     amount: Double,
                     unit: UoM,
                     mixinMinute: Int,
                     mixinStep: MixinStep = MixinStep.Boiling) derives ReadWriter // or Whirlpooling or Conditioning

final case class Adjunct(typeof: String,
                         amount: Double,
                         unit: UoM,
                         mixinMinute: Int,
                         mixinStep: MixinStep = MixinStep.Mashing) derives ReadWriter // or Boiling or Conditioning

final case class Yeast(typeof: String,
                       amount: Double,
                       unit: UoM,
                       mixinMinute: Int,
                       mixinStep: MixinStep = MixinStep.Fermenting) derives ReadWriter

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
                        yeasts: List[Yeast]) derives ReadWriter

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
                         mashEfficiency: Double = 0.0,
                         brewhouseEfficiency: Double = 0.0) derives ReadWriter