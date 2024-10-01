package objektwerks

import ox.channels.{Actor, ActorRef}
import ox.supervised

sealed trait Actor:
  def close: Unit = scribe.info(s"*** actor closing ...")

final class Brewer extends Actor:
  var metrics = Metrics.empty

  def brew(recipe: Recipe): Metrics =
    supervised:
      val logger = Actor.create( Logger() )
      
      val sanitizer = Actor.create( Sanitizer() )
      sanitizer.ask( _.sanitize( Sanitize(), logger ) )

      val preparer = Actor.create( Preparer() )
      preparer.ask( _.prepare( Prepare(recipe), logger ) )

      val malter = Actor.create( Malter() )
      malter.ask( _.malt( Malt(recipe), logger ) )

      val miller = Actor.create( Miller() )
      miller.ask( _.mill( Mill(recipe), logger ) )

      val masher = Actor.create( Masher() )
      masher.ask( _.mash( Mash(recipe), logger ) )

      val lauterer = Actor.create( Lauterer() )
      lauterer.ask( _.mash( Lauter(), logger ) )

      val sparger = Actor.create( Sparger() )
      sparger.ask( _.mash( Sparge(), logger ) )

      val boiler = Actor.create( Boiler() )
      boiler.ask( _.mash( Boil(recipe), logger ) )

      val cooler = Actor.create( Cooler() )
      cooler.ask( _.mash( Cool(), logger ) )

      val whirlpooler = Actor.create( Whirlpooler() )
      whirlpooler.ask( _.mash( Whirlpool(recipe), logger ) )

      val fermenter = Actor.create( Fermenter() )
      fermenter.ask( _.mash( Ferment(recipe), logger ) )

      val conditioner = Actor.create( Conditioner() )
      conditioner.ask( _.mash( Condition(), logger ) )

    metrics

final class Logger extends Actor:
  def log(command: Command): Unit = scribe.info(s"*** command: $command")

  def log(events: Event*): Unit = events.foreach( event => scribe.info(s"*** event: $event") )

final class Sanitizer extends Actor:
  def sanitize(sanitize: Sanitize, logger: ActorRef[Logger]): Unit =
    logger.ask( _.log( sanitize ) )
    logger.ask( _.log( Sanitizing, Sanitized ) )

final class Preparer extends Actor:
  def prepare(prepare: Prepare, logger: ActorRef[Logger]): Unit =
    logger.ask( _.log( prepare ) )
    logger.ask( _.log( Preparing, Prepared ) )

final class Malter extends Actor:
  def malt(malt: Malt, logger: ActorRef[Logger]): Unit =
    logger.ask( _.log( malt ) )
    logger.ask( _.log( Malting, Malted() ) )

final class Miller extends Actor:
  def mill(mill: Mill, logger: ActorRef[Logger]): Unit =
    logger.ask( _.log( mill ) )
    logger.ask( _.log( Milling, Milled() ) )

final class Masher extends Actor:
  def mash(mash: Mash, logger: ActorRef[Logger]): Unit =
    logger.ask( _.log( mash ) )
    logger.ask( _.log( Mashing, Mashed() ) )

final class Lauterer extends Actor:
  def mash(lauter: Lauter, logger: ActorRef[Logger]): Unit =
    logger.ask( _.log( lauter ) )
    logger.ask( _.log( Lautering, Lautered() ) )

final class Sparger extends Actor:
  def mash(sparge: Sparge, logger: ActorRef[Logger]): Unit =
    logger.ask( _.log( sparge ) )
    logger.ask( _.log( Sparging, Sparged() ) )

final class Boiler extends Actor:
  def mash(boil: Boil, logger: ActorRef[Logger]): Unit =
    logger.ask( _.log( boil ) )
    logger.ask( _.log( Boiling, Boiled() ) )

final class Cooler extends Actor:
  def mash(cool: Cool, logger: ActorRef[Logger]): Unit =
    logger.ask( _.log( cool ) )
    logger.ask( _.log( Cooling, Cooled() ) )

final class Whirlpooler extends Actor:
  def mash(whirlpool: Whirlpool, logger: ActorRef[Logger]): Unit =
    logger.ask( _.log( whirlpool ) )
    logger.ask( _.log( Whirlpooling, Whirlpooled() ) )

final class Fermenter extends Actor:
  def mash(ferment: Ferment, logger: ActorRef[Logger]): Unit =
    logger.ask( _.log( ferment ) )
    logger.ask( _.log( Fermenting, Fermented() ) )

final class Conditioner extends Actor:
  def mash(condition: Condition, logger: ActorRef[Logger]): Unit =
    logger.ask( _.log( condition ) )
    logger.ask( _.log( Conditioning, Conditioned() ) )

final class Packager extends Actor:
  def mash(`package`: Package, logger: ActorRef[Logger]): Unit =
    logger.ask( _.log( `package` ) )
    logger.ask( _.log( Packaging, Packaged() ) )