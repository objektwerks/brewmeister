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
          batch = batch.copy(log = batch.log ++ log)
          batch = batch.copy(process = batch.process.copy(sanitizeStarted = started))
        case Sanitized(log, completed) =>
          batch = batch.copy(log = batch.log ++ log)
          batch = batch.copy(process = batch.process.copy(sanitizeCompleted = completed))
        case Preparing(log, started) =>
          batch = batch.copy(log = batch.log ++ log)
          batch = batch.copy(process = batch.process.copy(prepareStarted = started))
        case Prepared(recipe, style, volume, log, completed) =>
          batch = batch.copy(recipe = recipe, style = style, volume = volume, log = batch.log ++ log)
          batch = batch.copy(process = batch.process.copy(prepareCompleted = completed))
        case Malting(log, started) =>
          batch = batch.copy(log = batch.log ++ log)
          batch = batch.copy(process = batch.process.copy(maltStarted = started))
        case Malted(log, completed) =>
          batch = batch.copy(log = batch.log ++ log)
          batch = batch.copy(process = batch.process.copy(maltCompleted = completed))
        case Milling(log, started) =>
          batch = batch.copy(log = batch.log ++ log)
          batch = batch.copy(process = batch.process.copy(millStarted = started))
        case Milled(log, completed) =>
          batch = batch.copy(log = batch.log ++ log)
          batch = batch.copy(process = batch.process.copy(millCompleted = completed))
        case Mashing(log, started) =>
          batch = batch.copy(log = batch.log ++ log)
          batch = batch.copy(process = batch.process.copy(mashStarted = started))
        case Mashed(log, completed) =>
          batch = batch.copy(log = batch.log ++ log)
          batch = batch.copy(process = batch.process.copy(mashCompleted = completed))
        case MashingTempPhLogged(mashingTemp, pH) =>
          batch = batch.copy(mashingTemp = mashingTemp, pH = pH)
        case Lautering(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Lautered(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Sparging(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Sparged(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case MashEfficiencyLogged(mashEfficiency) =>
          batch = batch.copy(mashEfficiency = mashEfficiency)
        case Boiling(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Boiled(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Cooling(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Cooled(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Whirlpooling(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Whirlpooled(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case BoilingCoolingTempOriginalGravityLogged(boilingTemp,
                                                     coolingTemp,
                                                     originalGravity) =>
          batch = batch.copy(boilingTemp = boilingTemp,
                             coolingTemp = coolingTemp,
                             originalGravity = originalGravity)
        case Fermenting(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Fermented(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case FermentingTempFinalGravityLogged(fermentingTemp,
                                              finalGravity) =>
          batch = batch.copy(fermentingTemp = fermentingTemp,
                             finalGravity = finalGravity)
        case Conditioning(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Conditioned(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case ConditioningTempSrmColorLogged(conditioningTemp,
                                            srmColor) =>
          batch = batch.copy(conditioningTemp = conditioningTemp,
                             srmColor = srmColor)
        case Kegging(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Kegged(ibuBitterness,
                    alcoholByVolume,
                    alcoholByWeight,
                    calories,
                    log) =>
          batch = batch.copy(ibuBitterness = ibuBitterness,
                             alcoholByVolume = alcoholByVolume,
                             alcoholByWeight = alcoholByWeight,
                             calories = calories,
                             log = batch.log ++ log)
        case KeggingTempBrewhouseEfficiencyLogged(keggingTemp,
                                                  brewhouseEfficiency,
                                                  completed) =>
          batch = batch.copy(keggingTemp = keggingTemp,
                             brewhouseEfficiency = brewhouseEfficiency,
                             completed = completed)
    batch