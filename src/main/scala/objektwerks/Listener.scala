package objektwerks

import scala.collection.mutable

final class Listener:
  private val listeners = mutable.ListBuffer.empty[Listener]
  val log = mutable.ListBuffer.empty[String]

  def register(listener: Listener): Unit =
    listeners += listener
    ()

  def onCommand(command: Command): Unit =
    listeners.foreach( _.onCommand(command) )
    log(command.toString)

  def onEvent(event: Event): Unit =
    listeners.foreach( _.onEvent(event) )
    log(event.toString)

  def onMetrics(metrics: Metrics): Unit =
    listeners.foreach( _.onMetrics(metrics) )
    log(metrics.toString)

  def log(entry: String): Unit = log += entry