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
        case Sanitizing(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Sanitized(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Preparing(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Prepared(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Malting(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Malted(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Milling(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Milled(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Mashing(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Mashed(log) =>
          batch = batch.copy(log = batch.log ++ log)
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
        case Cooled(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Whirlpooled(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case BoilingCoolingTempOriginalGravityLogged(boilingTemp,
                                                     coolingTemp,
                                                     originalGravity) =>
          batch = batch.copy(boilingTemp = boilingTemp,
                             coolingTemp = coolingTemp,
                             originalGravity = originalGravity)
        case Fermented(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case FermentingTempFinalGravityLogged(fermentingTemp,
                                              finalGravity) =>
          batch = batch.copy(fermentingTemp = fermentingTemp,
                             finalGravity = finalGravity)
        case Conditioned(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case ConditioningTempSrmColorLogged(conditioningTemp,
                                            srmColor) =>
          batch = batch.copy(conditioningTemp = conditioningTemp,
                             srmColor = srmColor)
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
                                                  brewhouseEfficiency) =>
          batch = batch.copy(keggingTemp = keggingTemp,
                             brewhouseEfficiency = brewhouseEfficiency)
    batch