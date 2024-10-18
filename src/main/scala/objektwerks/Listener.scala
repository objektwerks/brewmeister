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
    events.toList match
      case e :: es if e.isInstanceOf[OriginalGravityLogged] => e.asInstanceOf[OriginalGravityLogged].originalGravity
      case _ => 0.0

  def finalGravity: Double =
    events.toList match
      case e :: es if e.isInstanceOf[FinalGravityLogged] => e.asInstanceOf[FinalGravityLogged].finalGravity
      case _ => 0.0

  def batch: Batch =
    var batch = Batch()
    events.foreach { event =>
      event match
        case Mashed(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case PhLogged(log, pH) =>
          batch = batch.copy(pH = pH)
          batch = batch.copy(log = batch.log ++ log)
        case Sparged(log, mashEfficiency) =>
          batch = batch.copy(mashEfficiency = mashEfficiency)
          batch = batch.copy(log = batch.log ++ log)
        case Whirlpooled(log, originalGravity) =>
          batch = batch.copy(originalGravity = originalGravity)
          batch = batch.copy(log = batch.log ++ log)
        case Fermented(log, finalGravity) =>
          batch = batch.copy(finalGravity = finalGravity)
          batch = batch.copy(log = batch.log ++ log)
        case Conditioned(log, srmColor) =>
          batch = batch.copy(srmColor = srmColor)
          batch = batch.copy(log = batch.log ++ log)
        case Kegged(log, ibuBitterness, alcoholByVolume, alcoholByWeight, calories, brewhouseEfficiency) =>
          batch = batch.copy(ibuBitterness = ibuBitterness,
                             alcoholByVolume = alcoholByVolume,
                             alcoholByWeight = alcoholByWeight,
                             calories = calories,
                             brewhouseEfficiency = brewhouseEfficiency)
          batch = batch.copy(log = batch.log ++ log)
        case _ =>
    }
    batch