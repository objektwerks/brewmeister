package objektwerks

final class Logger:
  def log(command: Command): Unit = scribe.info(s"*** command: $command")