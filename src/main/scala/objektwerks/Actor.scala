package objektwerks

import ox.channels.*
import ox.supervised

sealed trait Actor:
  def close: Unit = scribe.info(s"*** ${getClass.getSimpleName}.close")

final class Brewer extends Actor:
  var metrics = Metrics.empty

  def brew(brew: Brew): Metrics =
    supervised:
      val logger = Actor.create( Logger() )
      logger.ask( _.log(brew) )
      
      val sanitizer = Actor.create( Sanitizer() )
      sanitizer.ask( _.sanitize( Sanitize(), logger ) )

      val preparer = Actor.create( Preparer() )
      preparer.ask( _.prepare( Prepare(brew.recipe), logger ) )

      val malter = Actor.create( Malter() )
      malter.ask( _.malt( Malt(brew.recipe), logger ) )

      val miller = Actor.create( Miller() )
      miller.ask( _.mill( Mill(brew.recipe), logger ) )

    metrics

final class Logger extends Actor:
  def log(command: Command): Unit = scribe.info(s"*** command: $command")

  def log(state: State): Unit = scribe.info(s"*** state: $state")

  def log(event: Event): Unit = scribe.info(s"*** event: $event")

  def log(command: Command, state: State, event: Event): Unit =
    log(command)
    log(state)
    log(event)

final class Sanitizer extends Actor:
  def sanitize(sanitize: Sanitize, logger: ActorRef[Logger]): Unit =
    logger.ask( _.log( sanitize, Sanitizing, Sanitized ) )

final class Preparer extends Actor:
  def prepare(prepare: Prepare, logger: ActorRef[Logger]): Unit =
    logger.ask( _.log( prepare, Preparing, Prepared ) )

final class Malter extends Actor:
  def malt(malt: Malt, logger: ActorRef[Logger]): Unit =
    logger.ask( _.log( malt, Malting, Malted() ) )

final class Miller extends Actor:
  def mill(mill: Mill, logger: ActorRef[Logger]): Unit =
    logger.ask( _.log( mill, Milling, Milled() ) )

final class Masher extends Actor

final class Lauterer extends Actor

final class Sparger extends Actor

final class Boiler extends Actor

final class Cooler extends Actor

final class Whirlpooler extends Actor

final class Fermenter extends Actor

final class Conditioner extends Actor

final class Packager extends Actor