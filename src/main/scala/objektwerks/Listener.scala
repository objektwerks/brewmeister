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
      case e :: es if e.isInstanceOf[Whirlpooled] => e.asInstanceOf[Whirlpooled].originalGravity
      case _ => 0.0

  def finalGravity: Double =
    events.toList match
      case e :: es if e.isInstanceOf[Fermented] => e.asInstanceOf[Fermented].finalGravity
      case _ => 0.0

  def batch: Batch =
    var batch = Batch()
    events.foreach { event =>
      event match
        case Mashed(_, pH) =>
          batch = batch.copy(pH = pH)
        case Sparged(_, mashEfficiency) =>
          batch = batch.copy(mashEfficiency = mashEfficiency)
        case Whirlpooled(_, originalGravity) =>
          batch = batch.copy(originalGravity = originalGravity)
        case Fermented(_, finalGravity) =>
          batch = batch.copy(finalGravity = finalGravity)
        case Conditioned(_, srmColor) =>
          batch = batch.copy(srmColor = srmColor)
        case Kegged(_, ibuBitterness, alcoholByVolume, alcoholByWeight, calories, brewhouseEfficiency) =>
          batch = batch.copy(ibuBitterness = ibuBitterness,
                                alcoholByVolume = alcoholByVolume,
                                alcoholByWeight = alcoholByWeight,
                                calories = calories,
                                brewhouseEfficiency = brewhouseEfficiency)
        case _ =>
    }
    batch