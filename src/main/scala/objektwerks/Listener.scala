package objektwerks

import scala.collection.mutable

final class Listener(batchId: Int):
  private val listeners = mutable.ListBuffer.empty[Listener]
  val events = mutable.ListBuffer.empty[Event]

  def originalGravity: Double =
    var og = 0.0
    events.foreach { event =>
      event match
        case Whirlpooled(_, originalGravity) => og = originalGravity
        case _ =>
    }
    og

  def finalGravity: Double =
    var fg = 0.0
    events.foreach { event =>
      event match
        case Fermented(_, finalGravity) => fg = finalGravity
        case _ =>
    }
    fg

  def metrics: Metrics = Metrics.build(batchId, events.toList)

  def register(listener: Listener): Unit =
    listeners += listener
    ()

  def onEvent(event: Event): Unit =
    events += event
    listeners.foreach( _.onEvent(event) )