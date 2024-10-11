package objektwerks

import scala.collection.mutable

final class Listener:
  val listeners = mutable.ListBuffer.empty[Listener]
  val logs = mutable.ListBuffer.empty[String]

  def register(listener: Listener): Unit =
    listeners += listener
    ()

  def onCommand(command: Command): Unit =
    listeners.foreach( _.onCommand(command) )
    scribe.info(command.toString)

  def onEvent(event: Event): Unit =
    listeners.foreach( _.onEvent(event) )
    scribe.info(event.toString)

  def onMetrics(metrics: Metrics): Unit =
    listeners.foreach( _.onMetrics(metrics) )
    scribe.info(metrics.toString)

  def log(entry: String): Unit = logs += entry