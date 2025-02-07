package objektwerks

import java.time.LocalDateTime

import scala.collection.mutable

final class Listener:
  private val listeners = mutable.ListBuffer.empty[Listener]
  private val events = mutable.ListBuffer.empty[Event]
  private var rollingDateTime = LocalDateTime.now

  def incrProcessDateTime(number: Int, uot: UoT): String =
    rollingDateTime = uot match
      case UoT.minutes => rollingDateTime.plusMinutes(number)
      case UoT.hours => rollingDateTime.plusHours(number)
      case UoT.days => rollingDateTime.plusDays(number)
      case UoT.weeks => rollingDateTime.plusWeeks(number)
    rollingDateTime.format(nowFormatter)

  def register(listener: Listener): Unit =
    listeners += listener
    ()

  def onEvent(event: Event): Unit =
    events += event
    listeners.foreach( _.onEvent(event) )

  def originalGravity: Double =
    var originalGravity = 0.0
    events.foreach {
      case logged: BoilingCoolingTempOriginalGravityLogged => originalGravity = logged.originalGravity.formatGravity
      case _ =>
    }
    originalGravity

  def finalGravity: Double =
    var finalGravity = 0.0
    events.foreach {
      case logged: FermentingTempFinalGravityLogged => finalGravity = logged.finalGravity.formatGravity
      case _ =>
    }
    finalGravity

  def batch: Batch =
    var batch = Batch()
    batch = batch.copy(started = now())
    events.foreach {
      case Sanitizing(log, started) =>
        batch = batch.copy(
          timeline = batch.timeline.copy(sanitizingStarted = started),
          log = batch.log ++ log
        )

      case Sanitized(log, completed) =>
        batch = batch.copy(
          timeline = batch.timeline.copy(sanitizingCompleted = completed),
          log = batch.log ++ log
        )

      case Preparing(log, started) =>
        batch = batch.copy(
          timeline = batch.timeline.copy(preparingStarted = started),
          log = batch.log ++ log
        )

      case Prepared(recipe, style, volume, log, completed) =>
        batch = batch.copy(
          timeline = batch.timeline.copy(preparingCompleted = completed),
          recipe = recipe,
          style = style,
          volume = volume,
          log = batch.log ++ log
        )

      case Malting(log, started) =>
        batch = batch.copy(
          timeline = batch.timeline.copy(maltingStarted = started),
          log = batch.log ++ log
        )

      case Malted(log, completed) =>
        batch = batch.copy(
          timeline = batch.timeline.copy(maltingCompleted = completed),
          log = batch.log ++ log
        )

      case Milling(log, started) =>
        batch = batch.copy(
          timeline = batch.timeline.copy(millingStarted = started),
          log = batch.log ++ log
        )

      case Milled(log, completed) =>
        batch = batch.copy(
          timeline = batch.timeline.copy(millingCompleted = completed),
          log = batch.log ++ log
        )

      case Mashing(log, started) =>
        batch = batch.copy(
          timeline = batch.timeline.copy(mashingStarted = started),
          log = batch.log ++ log
        )

      case Mashed(log, completed) =>
        batch = batch.copy(
          timeline = batch.timeline.copy(mashingCompleted = completed),
          log = batch.log ++ log
        )

      case MashingTempPhLogged(mashingTemp, pH, log) =>
        batch = batch.copy(
          mashingTemp = mashingTemp,
          pH = pH,
          log = batch.log ++ log
        )

      case Lautering(log, started) =>
        batch = batch.copy(
          timeline = batch.timeline.copy(lauteringStarted = started),
          log = batch.log ++ log
        )

      case Lautered(log, completed) =>
        batch = batch.copy(
          timeline = batch.timeline.copy(lauteringCompleted = completed),
          log = batch.log ++ log
        )

      case Sparging(log, started) =>
        batch = batch.copy(
          timeline = batch.timeline.copy(spargingStarted = started),
          log = batch.log ++ log
        )

      case Sparged(log, completed) =>
        batch = batch.copy(
          timeline = batch.timeline.copy(spargingCompleted = completed),
          log = batch.log ++ log
        )

      case MashEfficiencyLogged(mashEfficiency, log) =>
        batch = batch.copy(
          mashEfficiency = mashEfficiency,
          log = batch.log ++ log
        )

      case Boiling(log, started) =>
        batch = batch.copy(
          timeline = batch.timeline.copy(boilingStarted = started),
          log = batch.log ++ log
        )

      case Boiled(log, completed) =>
        batch = batch.copy(
          timeline = batch.timeline.copy(boilingCompleted = completed),
          log = batch.log ++ log
        )

      case Cooling(log, started) =>
        batch = batch.copy(
          timeline = batch.timeline.copy(coolingStarted = started),
          log = batch.log ++ log
        )

      case Cooled(log, completed) =>
        batch = batch.copy(
          timeline = batch.timeline.copy(coolingCompleted = completed),
          log = batch.log ++ log
        )

      case Whirlpooling(log, started) =>
        batch = batch.copy(
          timeline = batch.timeline.copy(whirlpoolingStarted = started),
          log = batch.log ++ log
        )

      case Whirlpooled(log, completed) =>
        batch = batch.copy(
          timeline = batch.timeline.copy(whirlpoolingCompleted = completed),
          log = batch.log ++ log
        )

      case BoilingCoolingTempOriginalGravityLogged(boilingTemp, coolingTemp, originalGravity, log) =>
        batch = batch.copy(
          boilingTemp = boilingTemp,
          coolingTemp = coolingTemp,
          originalGravity = originalGravity,
          log = batch.log ++ log
        )

      case Fermenting(log, started) =>
        batch = batch.copy(
          timeline = batch.timeline.copy(fermentingStarted = started),
          log = batch.log ++ log
        )

      case Fermented(log, completed) =>
        batch = batch.copy(
          timeline = batch.timeline.copy(fermentingCompleted = completed),
          log = batch.log ++ log
        )

      case FermentingTempFinalGravityLogged(fermentingTemp, finalGravity, log) =>
        batch = batch.copy(
          fermentingTemp = fermentingTemp,
          finalGravity = finalGravity,
          log = batch.log ++ log
        )

      case Conditioning(log, started) =>
        batch = batch.copy(
          timeline = batch.timeline.copy(conditioningStarted = started),
          log = batch.log ++ log
        )

      case Conditioned(log, completed) =>
        batch = batch.copy(
          timeline = batch.timeline.copy(conditioningCompleted = completed),
          log = batch.log ++ log
        )

      case ConditioningTempSrmColorLogged(conditioningTemp, srmColor, log) =>
        batch = batch.copy(
          conditioningTemp = conditioningTemp,
          srmColor = srmColor,
          log = batch.log ++ log
        )

      case Kegging(log, started) =>
        batch = batch.copy(
          timeline = batch.timeline.copy(keggingStarted = started),
          log = batch.log ++ log
        )

      case Kegged(ibuBitterness, alcoholByVolume, alcoholByWeight, calories, keggingTemp, appearance, aroma, taste, log, completed) =>
        batch = batch.copy(
          ibuBitterness = ibuBitterness,
          alcoholByVolume = alcoholByVolume,
          alcoholByWeight = alcoholByWeight,
          calories = calories,
          keggingTemp = keggingTemp,
          appearance = appearance,
          aroma = aroma,
          taste = taste,
          log = batch.log ++ log,
          completed = completed,
          stored = completed
        )
        batch = batch.copy(
          timeline = batch.timeline.copy(keggingCompleted = completed)
        )

      case KeggingTempBrewhouseEfficiencyLogged(brewhouseEfficiency, log) =>
        batch = batch.copy(
          brewhouseEfficiency = brewhouseEfficiency,
          log = batch.log ++ log
        )
    }
    batch