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
        case PhLogged(pH) =>
          batch = batch.copy(pH = pH)
        case Sparged(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Whirlpooled(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Fermented(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Conditioned(log) =>
          batch = batch.copy(log = batch.log ++ log)
        case Kegged(log, ibuBitterness, alcoholByVolume, alcoholByWeight, calories) =>
          batch = batch.copy(ibuBitterness = ibuBitterness,
                             alcoholByVolume = alcoholByVolume,
                             alcoholByWeight = alcoholByWeight,
                             calories = calories)
          batch = batch.copy(log = batch.log ++ log)
        case _ =>
    }
    batch