package objektwerks

import scala.collection.mutable

final class Listener(batchId: Int):
  private val listeners = mutable.ListBuffer.empty[Listener]
  val events = mutable.ListBuffer.empty[Event]

  def metrics: Metrics =
    var metrics = Metrics(batchId = batchId)
    events.foreach { event =>
      event match
        case Mashed(_, pH) =>
          metrics = metrics.copy(pH = pH)
        case Sparged(_, mashEfficiency) =>
          metrics = metrics.copy(mashEfficiency = mashEfficiency)
        case Whirlpooled(_, originalGravity) =>
          metrics = metrics.copy(originalGravity = originalGravity)
        case Fermented(_, finalGravity) =>
          metrics = metrics.copy(finalGravity = finalGravity)
        case Conditioned(_, srmColor) =>
          metrics = metrics.copy(srmColor = srmColor)
        case Packaged(_, ibuBitterness, alcoholByVolume, alcoholByWeight, calories, brewhouseEfficiency) =>
          metrics = metrics.copy(ibuBitterness = ibuBitterness,
                                alcoholByVolume = alcoholByVolume,
                                alcoholByWeight = alcoholByWeight,
                                calories = calories,
                                brewhouseEfficiency = brewhouseEfficiency)
        case _ =>
    }

    metrics

  def register(listener: Listener): Unit =
    listeners += listener
    ()

  def onEvent(event: Event): Unit =
    events += event
    listeners.foreach( _.onEvent(event) )