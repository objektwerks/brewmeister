package objektwerks

import ox.channels.{Actor, ActorRef}
import ox.supervised

sealed trait Actor:
  def close: Unit = scribe.info(s"*** actor closing ...")

final class Brewer extends Actor:
  var metrics = Metrics.empty

  def brew(recipe: Recipe, listener: Listener): Metrics =
    supervised:      
      val sanitizer = Actor.create( Sanitizer() )
      sanitizer.ask( _.sanitize( Sanitize(), listener ) )

      val preparer = Actor.create( Preparer() )
      preparer.ask( _.prepare( Prepare(recipe), listener ) )

      val malter = Actor.create( Malter() )
      malter.ask( _.malt( Malt(recipe), listener ) )

      val miller = Actor.create( Miller() )
      miller.ask( _.mill( Mill(recipe), listener ) )

      val masher = Actor.create( Masher() )
      masher.ask( _.mash( Mash(recipe), listener ) )

      val lauterer = Actor.create( Lauterer() )
      lauterer.ask( _.mash( Lauter(), listener ) )

      val sparger = Actor.create( Sparger() )
      sparger.ask( _.mash( Sparge(), listener ) )

      val boiler = Actor.create( Boiler() )
      boiler.ask( _.mash( Boil(recipe), listener ) )

      val cooler = Actor.create( Cooler() )
      cooler.ask( _.mash( Cool(), listener ) )

      val whirlpooler = Actor.create( Whirlpooler() )
      whirlpooler.ask( _.mash( Whirlpool(recipe), listener ) )

      val fermenter = Actor.create( Fermenter() )
      fermenter.ask( _.mash( Ferment(recipe), listener ) )

      val conditioner = Actor.create( Conditioner() )
      conditioner.ask( _.mash( Condition(), listener ) )

      val packager = Actor.create( Packager() )
      packager.ask( _.mash( Package(), listener ) )

    metrics

final class Sanitizer extends Actor:
  def sanitize(sanitize: Sanitize, listener: Listener): Unit =
    listener.log( sanitize )
    listener.handle( Sanitizing )
    listener.handle( Sanitized )

final class Preparer extends Actor:
  def prepare(prepare: Prepare, listener: Listener): Unit =
    listener.log( prepare )
    listener.handle( Preparing )
    listener.handle( Prepared )

final class Malter extends Actor:
  def malt(malt: Malt, listener: Listener): Unit =
    listener.log( malt )
    listener.handle( Malting )
    listener.handle( Malted() )

final class Miller extends Actor:
  def mill(mill: Mill, listener: Listener): Unit =
    listener.log( mill )
    listener.handle( Milling )
    listener.handle( Milled() )

final class Masher extends Actor:
  def mash(mash: Mash, listener: Listener): Unit =
    listener.log( mash ) )
    listener.log( Mashing, Mashed() ) )

final class Lauterer extends Actor:
  def mash(lauter: Lauter, listener: Listener): Unit =
    listener.log( lauter ) )
    listener.log( Lautering, Lautered() ) )

final class Sparger extends Actor:
  def mash(sparge: Sparge, listener: Listener): Unit =
    listener.log( sparge ) )
    listener.log( Sparging, Sparged() ) )

final class Boiler extends Actor:
  def mash(boil: Boil, listener: Listener): Unit =
    listener.log( boil ) )
    listener.log( Boiling, Boiled() ) )

final class Cooler extends Actor:
  def mash(cool: Cool, listener: Listener): Unit =
    listener.log( cool ) )
    listener.log( Cooling, Cooled() ) )

final class Whirlpooler extends Actor:
  def mash(whirlpool: Whirlpool, listener: Listener): Unit =
    listener.log( whirlpool ) )
    listener.log( Whirlpooling, Whirlpooled() ) )

final class Fermenter extends Actor:
  def mash(ferment: Ferment, listener: Listener): Unit =
    listener.log( ferment ) )
    listener.log( Fermenting, Fermented() ) )

final class Conditioner extends Actor:
  def mash(condition: Condition, listener: Listener): Unit =
    listener.log( condition ) )
    listener.log( Conditioning, Conditioned() ) )

final class Packager extends Actor:
  def mash(`package`: Package, listener: Listener): Unit =
    listener.log( `package` ) )
    listener.log( Packaging, Packaged() ) )