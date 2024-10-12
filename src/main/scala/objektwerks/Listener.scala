package objektwerks

import scala.collection.mutable

final class Listener(batchId: Int):
  private val listeners = mutable.ListBuffer.empty[Listener]
  val events = mutable.ListBuffer.empty[Event]

  // TODO! Derive metrics from events, to include batch completed date and time!
  def metrics: Metrics = Metrics.default(batchId)

  def register(listener: Listener): Unit =
    listeners += listener
    ()

  def onEvent(event: Event): Unit =
    events += event
    listeners.foreach( _.onEvent(event) )