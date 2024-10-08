package objektwerks

import ox.channels.{Actor, ActorRef}
import ox.supervised

final class Brewer(batch: Batch, listener: ActorRef[Listener]):
  private val recipe = batch.recipe
  // private val metrics = batch.metrics

  def sanitize: Unit =
    supervised:
      Actor.create( Sanitizer(listener) ).tell( _.sanitize( Sanitize() ) )

  def prepare: Unit =
    supervised:
      Actor.create( Preparer(listener) ).tell( _.prepare( Prepare(recipe) ) )

  def malt: Unit =
    supervised:
      Actor.create( Malter(listener) ).tell( _.malt( Malt(recipe) ) )

  def mill: Milled =
    supervised:
      Actor.create( Miller() ).ask( _.mill( Mill(recipe) ) )

  def mash: Mashed =
    supervised:
      Actor.create( Masher() ).ask( _.mash( Mash(recipe) ) )

  def lauter: Lautered =
    supervised:
      Actor.create( Lauterer() ).ask( _.lauter( Lauter(recipe) ) )

  def sparge: Sparged =
    supervised:
      Actor.create( Sparger() ).ask( _.sparge( Sparge(recipe) ) )

  def boil: Boiled =
    supervised:
      Actor.create( Boiler() ).ask( _.boil( Boil(recipe) ) )

  def cool: Cooled =
    supervised:
      Actor.create( Cooler() ).ask( _.cool( Cool(recipe) ) )

  def whirlpool: Whirlpooled =
    supervised:
      Actor.create( Whirlpooler() ).ask( _.whirlpool( Whirlpool(recipe) ) )

  def ferment: Fermented =
    supervised:
      Actor.create( Fermenter() ).ask( _.ferment( Ferment(recipe) ) )

  def condition: Conditioned =
    supervised:
      Actor.create( Conditioner() ).ask( _.condition( Condition(recipe) ) )

  def `package`: Packaged =
    supervised:
      Actor.create( Packager() ).ask( _.`package`( Package(recipe) ) )