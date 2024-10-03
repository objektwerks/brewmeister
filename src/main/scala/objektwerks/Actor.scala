package objektwerks

sealed trait Actor:
  def close: Unit = scribe.info(s"*** actor closing ...")

final class Sanitizer extends Actor:
  def sanitize(sanitize: Sanitize): Sanitized = Sanitized()

final class Preparer extends Actor:
  def prepare(prepare: Prepare): Prepared = Prepared()

final class Malter extends Actor:
  def malt(malt: Malt): Malted = Malted()

final class Miller extends Actor:
  def mill(mill: Mill, listener: Listener): Milled = Milled()

final class Masher extends Actor:
  def mash(mash: Mash, listener: Listener): Mashed = Mashed()

final class Lauterer extends Actor:
  def mash(lauter: Lauter, listener: Listener): Unit =
    listener.onCommand( lauter )
    listener.onEvent( Lautering )
    listener.onEvent( Lautered() )

final class Sparger extends Actor:
  def mash(sparge: Sparge, listener: Listener): Unit =
    listener.onCommand( sparge )
    listener.onEvent( Sparging )
    listener.onEvent( Sparged() )

final class Boiler extends Actor:
  def mash(boil: Boil, listener: Listener): Unit =
    listener.onCommand( boil )
    listener.onEvent( Boiling )
    listener.onEvent( Boiled() )

final class Cooler extends Actor:
  def mash(cool: Cool, listener: Listener): Unit =
    listener.onCommand( cool )
    listener.onEvent( Cooling )
    listener.onEvent( Cooled() )

final class Whirlpooler extends Actor:
  def mash(whirlpool: Whirlpool, listener: Listener): Unit =
    listener.onCommand( whirlpool )
    listener.onEvent( Whirlpooling )
    listener.onEvent( Whirlpooled() )

final class Fermenter extends Actor:
  def mash(ferment: Ferment, listener: Listener): Unit =
    listener.onCommand( ferment )
    listener.onEvent( Fermenting )
    listener.onEvent( Fermented() )

final class Conditioner extends Actor:
  def mash(condition: Condition, listener: Listener): Unit =
    listener.onCommand( condition )
    listener.onEvent( Conditioning )
    listener.onEvent( Conditioned() )

final class Packager extends Actor:
  def mash(`package`: Package, listener: Listener): Unit =
    listener.onCommand( `package` )
    listener.onEvent( Packaging )
    listener.onEvent( Packaged() )