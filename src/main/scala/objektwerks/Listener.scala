package objektwerks

final class Listener:
  def handle(event: Event): Unit = ()

  def log(command: Command): Unit = scribe.info(s"*** command: $command")