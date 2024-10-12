package objektwerks

import scala.collection.mutable

final class Listener(batchId: Int):
  private val listeners = mutable.ListBuffer.empty[Listener]
  val events = mutable.ListBuffer.empty[Event]

  def metrics: Metrics = Metrics.default(batchId) // TODO! Derive metrics from events!

  def register(listener: Listener): Unit =
    listeners += listener
    ()

  def onEvent(event: Event): Unit =
    events += event
    listeners.foreach( _.onEvent(event) )