package objektwerks

import scala.collection.mutable

final class Listener:
  private val listeners = mutable.ListBuffer.empty[Listener]
  val events = mutable.ListBuffer.empty[Event]

  def register(listener: Listener): Unit =
    listeners += listener
    ()

  def onEvent(event: Event): Unit =
    events += event
    listeners.foreach( _.onEvent(event) )