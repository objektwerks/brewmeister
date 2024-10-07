package objektwerks

trait Listener:
  def onCommand(command: Command): Unit

  def onEvent(event: Event): Unit

  def onMetrics(metrics: Metrics): Unit