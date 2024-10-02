package objektwerks

import ox.channels.Actor
import ox.supervised

sealed trait Actor:
  def close: Unit = scribe.info(s"*** actor closing ...")

final class Brewer extends Actor:
  def brew(recipe: Recipe, listener: Listener): Metrics =
    supervised:
      Actor.create( Sanitizer() ).ask( _.sanitize( Sanitize(), listener ) )

      Actor.create( Preparer() ).ask( _.prepare( Prepare(recipe), listener ) )

      Actor.create( Malter() ).ask( _.malt( Malt(recipe), listener ) )

      Actor.create( Miller() ).ask( _.mill( Mill(recipe), listener ) )

      Actor.create( Masher() ).ask( _.mash( Mash(recipe), listener ) )

      Actor.create( Lauterer() ).ask( _.mash( Lauter(), listener ) )

      Actor.create( Sparger() ).ask( _.mash( Sparge(), listener ) )

      Actor.create( Boiler() ).ask( _.mash( Boil(recipe), listener ) )

      Actor.create( Cooler() ).ask( _.mash( Cool(), listener ) )

      val whirlpooler = Actor.create( Whirlpooler() )
      whirlpooler.ask( _.mash( Whirlpool(recipe), listener ) )

      val fermenter = Actor.create( Fermenter() )
      fermenter.ask( _.mash( Ferment(recipe), listener ) )

      val conditioner = Actor.create( Conditioner() )
      conditioner.ask( _.mash( Condition(), listener ) )

      val packager = Actor.create( Packager() )
      packager.ask( _.mash( Package(), listener ) )

      Metrics.empty


final class Sanitizer extends Actor:
  def sanitize(sanitize: Sanitize, listener: Listener): Unit =
    listener.onCommand( sanitize )
    listener.onEvents( Sanitizing, Sanitized )

final class Preparer extends Actor:
  def prepare(prepare: Prepare, listener: Listener): Unit =
    listener.onCommand( prepare )
    listener.onEvents( Preparing, Prepared )

final class Malter extends Actor:
  def malt(malt: Malt, listener: Listener): Unit =
    listener.onCommand( malt )
    listener.onEvents( Malting, Malted() )

final class Miller extends Actor:
  def mill(mill: Mill, listener: Listener): Unit =
    listener.onCommand( mill )
    listener.onEvents( Milling, Milled() )

final class Masher extends Actor:
  def mash(mash: Mash, listener: Listener): Unit =
    listener.onCommand( mash )
    listener.onEvents( Mashing, Mashed() )

final class Lauterer extends Actor:
  def mash(lauter: Lauter, listener: Listener): Unit =
    listener.onCommand( lauter )
    listener.onEvents( Lautering, Lautered() )

final class Sparger extends Actor:
  def mash(sparge: Sparge, listener: Listener): Unit =
    listener.onCommand( sparge )
    listener.onEvents( Sparging, Sparged() )

final class Boiler extends Actor:
  def mash(boil: Boil, listener: Listener): Unit =
    listener.onCommand( boil )
    listener.onEvents( Boiling, Boiled() )

final class Cooler extends Actor:
  def mash(cool: Cool, listener: Listener): Unit =
    listener.onCommand( cool )
    listener.onEvents( Cooling, Cooled() )

final class Whirlpooler extends Actor:
  def mash(whirlpool: Whirlpool, listener: Listener): Unit =
    listener.onCommand( whirlpool )
    listener.onEvents( Whirlpooling, Whirlpooled() )

final class Fermenter extends Actor:
  def mash(ferment: Ferment, listener: Listener): Unit =
    listener.onCommand( ferment )
    listener.onEvents( Fermenting, Fermented() )

final class Conditioner extends Actor:
  def mash(condition: Condition, listener: Listener): Unit =
    listener.onCommand( condition )
    listener.onEvents( Conditioning, Conditioned() )

final class Packager extends Actor:
  def mash(`package`: Package, listener: Listener): Unit =
    listener.onCommand( `package` )
    listener.onEvents( Packaging, Packaged() )