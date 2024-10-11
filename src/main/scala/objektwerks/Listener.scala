package objektwerks

import scala.collection.mutable

final class Listener:
  private val listeners = mutable.ListBuffer.empty[Listener]

  def register(listener: Listener): Unit =
    listeners += listener
    ()

  def onCommand(command: Command): Unit =
    listeners.foreach( _.onCommand(command) )
    scribe.info(command.toString)

  def onEvent(event: Event): Unit =
    listeners.foreach( _.onEvent(event) )
    scribe.info(event.toString)

  def onMetrics(metrics: Metrics): Unit = scribe.info(metrics.toString)