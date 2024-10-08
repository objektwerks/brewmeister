package objektwerks

import ox.channels.{Actor, ActorRef}
import ox.supervised

final class Brewer(batch: Batch, listener: ActorRef[Listener]):
  private val batchId = batch.id
  private val recipe = batch.recipe
  // private val metrics = batch.metrics

  def sanitize: Unit =
    supervised:
      Actor.create( Sanitizer(listener) ).tell( _.sanitize( Sanitize(batchId) ) )

  def prepare: Unit =
    supervised:
      Actor.create( Preparer(listener) ).tell( _.prepare( Prepare(batchId, recipe) ) )

  def malt: Unit =
    supervised:
      Actor.create( Malter(listener) ).tell( _.malt( Malt(recipe) ) )

  def mill: Unit =
    supervised:
      Actor.create( Miller(listener) ).tell( _.mill( Mill(recipe) ) )

  def mash: Unit =
    supervised:
      Actor.create( Masher(listener) ).tell( _.mash( Mash(recipe) ) )

  def lauter: Unit =
    supervised:
      Actor.create( Lauterer(listener) ).tell( _.lauter( Lauter(recipe) ) )

  def sparge: Unit =
    supervised:
      Actor.create( Sparger(listener) ).tell( _.sparge( Sparge(recipe) ) )

  def boil: Unit =
    supervised:
      Actor.create( Boiler(listener) ).tell( _.boil( Boil(recipe) ) )

  def cool: Unit =
    supervised:
      Actor.create( Cooler(listener) ).tell( _.cool( Cool(recipe) ) )

  def whirlpool: Unit =
    supervised:
      Actor.create( Whirlpooler(listener) ).tell( _.whirlpool( Whirlpool(recipe) ) )

  def ferment: Unit =
    supervised:
      Actor.create( Fermenter(listener) ).tell( _.ferment( Ferment(recipe) ) )

  def condition: Unit =
    supervised:
      Actor.create( Conditioner(listener) ).tell( _.condition( Condition(recipe) ) )

  def `package`: Unit =
    supervised:
      Actor.create( Packager(listener) ).tell( _.`package`( Package(recipe) ) )