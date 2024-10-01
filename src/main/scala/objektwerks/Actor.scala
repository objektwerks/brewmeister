package objektwerks

import ox.channels.*
import ox.supervised

sealed trait Actor:
  def close: Unit = println(s"*** ${getClass.getSimpleName}.close")

final class Brewer extends Actor:
  var metrics = Metrics.empty

  def brew(brew: Brew): Metrics =
    supervised:
      val logger = Actor.create( Logger() )
      logger.ask( _.log(brew) )
      
      val sanitizer = Actor.create( Sanitizer() )
      sanitizer.ask( _.process( Sanitize(), logger ) )

      val preparer = Actor.create( Preparer() )
      preparer.ask( _.process( Prepare(brew.recipe), logger ) )

      logger.ask( _.log(Brewed) )

    metrics

final class Logger extends Actor:
  def log(command: Command): Unit = scribe.info(command.toString)

  def log(state: State): Unit = scribe.info(state.toString)

  def log(event: Event): Unit = scribe.info(event.toString)

  def log(command: Command, state: State, event: Event): Unit =
    log(command)
    log(state)
    log(event)

final class Sanitizer extends Actor:
  def process(sanitize: Sanitize, logger: ActorRef[Logger]): Unit =
    logger.ask( _.log( sanitize, Sanitizing, Sanitized ) )

final class Preparer extends Actor:
  def process(prepare: Prepare, logger: ActorRef[Logger]): Unit =
    logger.ask( _.log( prepare, Preparing, Prepared ) )

final class Malter extends Actor

final class Miller extends Actor

final class Masher extends Actor

final class Lauterer extends Actor

final class Sparger extends Actor

final class Boiler extends Actor

final class Cooler extends Actor

final class Whirlpooler extends Actor

final class Fermenter extends Actor

final class Conditioner extends Actor

final class Packager extends Actor