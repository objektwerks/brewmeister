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
  def mill(mill: Mill): Milled = Milled()

final class Masher extends Actor:
  def mash(mash: Mash): Mashed = Mashed()

final class Lauterer extends Actor:
  def mash(lauter: Lauter): Lautered = Lautered()

final class Sparger extends Actor:
  def mash(sparge: Sparge): Sparged = Sparged()

final class Boiler extends Actor:
  def mash(boil: Boil): Boiled = Boiled()

final class Cooler extends Actor:
  def mash(cool: Cool): Cooled = Cooled()

final class Whirlpooler extends Actor:
  def mash(whirlpool: Whirlpool): Whirlpooled = Whirlpooled()

final class Fermenter extends Actor:
  def mash(ferment: Ferment): Fermented = Fermented()

final class Conditioner extends Actor:
  def mash(condition: Condition): Conditioned = Conditioned()

final class Packager extends Actor:
  def mash(`package`: Package): Unit =
    listener.onCommand( `package` )
    listener.onEvent( Packaging )
    listener.onEvent( Packaged() )