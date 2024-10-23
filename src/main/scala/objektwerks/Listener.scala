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
        case Sanitized(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Prepared(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Malted(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Milled(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Mashed(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case MashTempPhLogged(mashTemp, pH) =>
          batch = batch.copy(mashTemp = mashTemp, pH = pH)
        case Lautered(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Sparged(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case MashEfficiencyLogged(mashEfficiency) =>
          batch = batch.copy(mashEfficiency = mashEfficiency)
        case Boiled(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Cooled(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Whirlpooled(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case BoilingCoolingTempOriginalGravityLogged(boilingTemp, coolingTemp, originalGravity) =>
          batch = batch.copy(boilingTemp = boilingTemp)
          batch = batch.copy(coolingTemp = coolingTemp)
          batch = batch.copy(originalGravity = originalGravity)
        case Fermented(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case FermentingTempFinalGravityLogged(fermentingTemp, finalGravity) =>
          batch = batch.copy(fermentingTemp = fermentingTemp)
          batch = batch.copy(finalGravity = finalGravity)
        case Conditioned(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case ConditioningTempSrmColorLogged(conditioningTemp, srmColor) =>
          batch = batch.copy(conditioningTemp = conditioningTemp)
          batch = batch.copy(srmColor = srmColor)
        case Kegged(log,
                    ibuBitterness,
                    alcoholByVolume,
                    alcoholByWeight,
                    calories) =>
          batch = batch.copy(ibuBitterness = ibuBitterness,
                             alcoholByVolume = alcoholByVolume,
                             alcoholByWeight = alcoholByWeight,
                             calories = calories,
                             log = batch.log ++ log)
        case BrewhouseEfficiencyLogged(keggingTemp, brewhouseEfficiency) =>
          batch = batch.copy(keggingTemp = keggingTemp)
          batch = batch.copy(brewhouseEfficiency = brewhouseEfficiency)
    batch