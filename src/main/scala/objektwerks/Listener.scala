package objektwerks

import scala.collection.mutable

final class Listener:
  private val listeners = mutable.ListBuffer.empty[Listener]
  val commands = mutable.ListBuffer.empty[Command]
  val events = mutable.ListBuffer.empty[Event]

  def register(listener: Listener): Unit =
    listeners += listener
    ()

  def onCommand(command: Command): Unit =
    commands += command
    listeners.foreach( _.onCommand(command) )

  def onEvent(event: Event): Unit =
    events += event
    listeners.foreach( _.onEvent(event) )