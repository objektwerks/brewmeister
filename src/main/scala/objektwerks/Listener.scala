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
        case PhLogged(pH) =>
          batch = batch.copy(pH = pH)
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
        case OriginalGravityLogged(originalGravity) =>
          batch = batch.copy(originalGravity = originalGravity)
        case Fermented(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case FinalGravityLogged(finalGravity) =>
          batch = batch.copy(finalGravity = finalGravity)
        case Conditioned(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case SrmColorLogged(srmColor) =>
          batch = batch.copy(srmColor = srmColor)
        case Kegged(log, ibuBitterness, alcoholByVolume, alcoholByWeight, calories) =>
          batch = batch.copy(ibuBitterness = ibuBitterness,
                             alcoholByVolume = alcoholByVolume,
                             alcoholByWeight = alcoholByWeight,
                             calories = calories,
                             log = batch.log ++ log)
        case BrewhouseEfficiencyLogged(brewhouseEfficiency) =>
          batch = batch.copy(brewhouseEfficiency = brewhouseEfficiency)
    }
    batch
