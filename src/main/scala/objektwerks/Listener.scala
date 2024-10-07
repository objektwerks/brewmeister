package objektwerks

final class Listener:
  def onCommand(command: Command): Unit = scribe.info(command.toString)

  def onEvent(event: Event): Unit = scribe.info(event.toString)

  def onMetrics(metrics: Metrics): Unit = scribe.info(metrics.toString)