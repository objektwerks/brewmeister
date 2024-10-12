package objektwerks

import scala.collection.mutable

final class Listener(batchId: Int):
  private val listeners = mutable.ListBuffer.empty[Listener]
  private val events = mutable.ListBuffer.empty[Event]

  def register(listener: Listener): Unit =
    listeners += listener
    ()

  def onEvent(event: Event): Unit =
    events += event
    listeners.foreach( _.onEvent(event) )

  def metrics: Metrics = Metrics.build(batchId, events.toList)

  def originalGravity: Double =
    events.toList match
      case e :: es if e.isInstanceOf[Whirlpooled] => e.asInstanceOf[Whirlpooled].originalGravity
      case _ => 0.0

  def finalGravity: Double =
    events.toList match
      case e :: es if e.isInstanceOf[Fermented] => e.asInstanceOf[Fermented].finalGravity
      case _ => 0.0