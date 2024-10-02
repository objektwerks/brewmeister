package objektwerks

final class Listener:
  def handle(event: Event): Unit = scribe.info(s"*** event: $event")

  def handle(events: Event*): Unit = events.foreach( event => scribe.info(s"*** event: $event") )

  def log(command: Command): Unit = scribe.info(s"*** command: $command")