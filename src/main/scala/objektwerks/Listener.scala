package objektwerks

import scala.collection.mutable

final class Listener:
  private val listeners = mutable.ListBuffer.empty[Listener]
  private val events = mutable.ListBuffer.empty[Event]

  def register(listener: Listener): Unit =
    listeners += listener
    ()

  def onEvent(event: Event): Unit =
    events += event
    listeners.foreach( _.onEvent(event) )

  def originalGravity: Double =
    var originalGravity = 0.0
    events.foreach: event =>
      if event.isInstanceOf[BoilingCoolingTempOriginalGravityLogged] then
        originalGravity = event.asInstanceOf[BoilingCoolingTempOriginalGravityLogged].originalGravity.formatGravity
    originalGravity

  def finalGravity: Double =
    var finalGravity = 0.0
    events.foreach: event =>
      if event.isInstanceOf[FermentingTempFinalGravityLogged] then
        finalGravity = event.asInstanceOf[FermentingTempFinalGravityLogged].finalGravity.formatGravity
    finalGravity

  def batch: Batch =
    var batch = Batch()
    events.foreach: event =>
      event match
        case Sanitizing(log, started) =>
          batch = batch.copy(process = batch.process.copy(sanitizeStarted = started),
                             log = batch.log ++ log)
        case Sanitized(log, completed) =>
          batch = batch.copy(process = batch.process.copy(sanitizeCompleted = completed),
                             log = batch.log ++ log)
        case Preparing(log, started) =>
          batch = batch.copy(process = batch.process.copy(prepareStarted = started),
                             log = batch.log ++ log)
        case Prepared(recipe, style, volume, log, completed) =>
          batch = batch.copy(process = batch.process.copy(prepareCompleted = completed),
                             recipe = recipe,
                             style = style,
                             volume = volume,
                             log = batch.log ++ log)
        case Malting(log, started) =>
          batch = batch.copy(process = batch.process.copy(maltStarted = started),
                             log = batch.log ++ log)
        case Malted(log, completed) =>
          batch = batch.copy(process = batch.process.copy(maltCompleted = completed),
                             log = batch.log ++ log)
        case Milling(log, started) =>
          batch = batch.copy(process = batch.process.copy(millStarted = started),
                             log = batch.log ++ log)
        case Milled(log, completed) =>
          batch = batch.copy(process = batch.process.copy(millCompleted = completed),
                             log = batch.log ++ log)
        case Mashing(log, started) =>
          batch = batch.copy(process = batch.process.copy(mashStarted = started),
                             log = batch.log ++ log)
        case Mashed(log, completed) =>
          batch = batch.copy(process = batch.process.copy(mashCompleted = completed),
                             log = batch.log ++ log)
        case MashingTempPhLogged(mashingTemp, pH, log) =>
          batch = batch.copy(mashingTemp = mashingTemp,
                             pH = pH,
                             log = batch.log ++ log)
        case Lautering(log, started) =>
          batch = batch.copy(process = batch.process.copy(lauterStarted = started),
                             log = batch.log ++ log)
        case Lautered(log, completed) =>
          batch = batch.copy(process = batch.process.copy(lauterCompleted = completed),
                             log = batch.log ++ log)
        case Sparging(log, started) =>
          batch = batch.copy(process = batch.process.copy(spargeStarted = started),
                             log = batch.log ++ log)
        case Sparged(log, completed) =>
          batch = batch.copy(process = batch.process.copy(spargeCompleted = completed),
                             log = batch.log ++ log)
        case MashEfficiencyLogged(mashEfficiency, log) =>
          batch = batch.copy(mashEfficiency = mashEfficiency,
                             log = batch.log ++ log)
        case Boiling(log, started) =>
          batch = batch.copy(process = batch.process.copy(boilStarted = started),
                             log = batch.log ++ log)
        case Boiled(log, completed) =>
          batch = batch.copy(process = batch.process.copy(boilCompleted = completed),
                             log = batch.log ++ log)
        case Cooling(log, started) =>
          batch = batch.copy(process = batch.process.copy(coolStarted = started),
                             log = batch.log ++ log)
        case Cooled(log, completed) =>
          batch = batch.copy(process = batch.process.copy(coolCompleted = completed),
                             log = batch.log ++ log)
        case Whirlpooling(log, started) =>
          batch = batch.copy(log = batch.log ++ log)
          batch = batch.copy(process = batch.process.copy(whirlpoolStarted = started))
        case Whirlpooled(log, completed) =>
          batch = batch.copy(log = batch.log ++ log)
          batch = batch.copy(process = batch.process.copy(whirlpoolCompleted = completed))
        case BoilingCoolingTempOriginalGravityLogged(boilingTemp,
                                                     coolingTemp,
                                                     originalGravity,
                                                     log) =>
          batch = batch.copy(boilingTemp = boilingTemp,
                             coolingTemp = coolingTemp,
                             originalGravity = originalGravity,
                             log = batch.log ++ log)
        case Fermenting(log, started) =>
          batch = batch.copy(log = batch.log ++ log)
          batch = batch.copy(process = batch.process.copy(fermentStarted = started))
        case Fermented(log, completed) =>
          batch = batch.copy(log = batch.log ++ log)
          batch = batch.copy(process = batch.process.copy(fermentCompleted = completed))
        case FermentingTempFinalGravityLogged(fermentingTemp,
                                              finalGravity,
                                              log) =>
          batch = batch.copy(fermentingTemp = fermentingTemp,
                             finalGravity = finalGravity,
                             log = batch.log ++ log)
        case Conditioning(log, started) =>
          batch = batch.copy(log = batch.log ++ log)
          batch = batch.copy(process = batch.process.copy(conditionStarted = started))
        case Conditioned(log, completed) =>
          batch = batch.copy(log = batch.log ++ log)
          batch = batch.copy(process = batch.process.copy(conditionCompleted = completed))
        case ConditioningTempSrmColorLogged(conditioningTemp,
                                            srmColor,
                                            log) =>
          batch = batch.copy(conditioningTemp = conditioningTemp,
                             srmColor = srmColor,
                             log = batch.log ++ log)
        case Kegging(log, started) =>
          batch = batch.copy(log = batch.log ++ log)
          batch = batch.copy(process = batch.process.copy(kegStarted = started))
        case Kegged(ibuBitterness,
                    alcoholByVolume,
                    alcoholByWeight,
                    calories,
                    keggingTemp,
                    appearance,
                    aroma,
                    taste,
                    log,
                    completed) =>
          batch = batch.copy(ibuBitterness = ibuBitterness,
                             alcoholByVolume = alcoholByVolume,
                             alcoholByWeight = alcoholByWeight,
                             calories = calories,
                             keggingTemp = keggingTemp,
                             appearance = appearance,
                             aroma = aroma,
                             taste = taste,
                             log = batch.log ++ log)
          batch = batch.copy(process = batch.process.copy(kegCompleted = completed))
        case KeggingTempBrewhouseEfficiencyLogged(brewhouseEfficiency,
                                                  log,
                                                  completed) =>
          batch = batch.copy(brewhouseEfficiency = brewhouseEfficiency,
                             log = batch.log ++ log,
                             completed = completed)
    batch