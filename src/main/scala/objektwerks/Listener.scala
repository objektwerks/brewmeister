package objektwerks

final class Listener:
  def onEvents(events: Event*): Unit = events.foreach( event => scribe.info(s"*** event: $event") )

  def log(command: Command): Unit = scribe.info(s"*** command: $command")