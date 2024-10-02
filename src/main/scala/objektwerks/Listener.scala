package objektwerks

final class Listener:
  def onCommand(command: Command): Unit = scribe.info(s"*** command: $command")

  def onEvent(event: Event): Unit = scribe.info(s"*** event: $event")

  def onEvents(events: Event*): Unit = events.foreach( event => scribe.info(s"*** event: $event") )