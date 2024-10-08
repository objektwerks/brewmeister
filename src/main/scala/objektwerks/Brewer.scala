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
      Actor.create( Malter(listener) ).tell( _.malt( Malt(batchId, recipe) ) )

  def mill: Unit =
    supervised:
      Actor.create( Miller(listener) ).tell( _.mill( Mill(batchId, recipe) ) )

  def mash: Unit =
    supervised:
      Actor.create( Masher(listener) ).tell( _.mash( Mash(batchId, recipe) ) )

  def lauter: Unit =
    supervised:
      Actor.create( Lauterer(listener) ).tell( _.lauter( Lauter(batchId, recipe) ) )

  def sparge: Unit =
    supervised:
      Actor.create( Sparger(listener) ).tell( _.sparge( Sparge(batchId, recipe) ) )

  def boil: Unit =
    supervised:
      Actor.create( Boiler(listener) ).tell( _.boil( Boil(batchId, recipe) ) )

  def cool: Unit =
    supervised:
      Actor.create( Cooler(listener) ).tell( _.cool( Cool(batchId, recipe) ) )

  def whirlpool: Unit =
    supervised:
      Actor.create( Whirlpooler(listener) ).tell( _.whirlpool( Whirlpool(batchId, recipe) ) )

  def ferment: Unit =
    supervised:
      Actor.create( Fermenter(listener) ).tell( _.ferment( Ferment(batchId, recipe) ) )

  def condition: Unit =
    supervised:
      Actor.create( Conditioner(listener) ).tell( _.condition( Condition(batchId, recipe) ) )

  def `package`: Unit =
    supervised:
      Actor.create( Packager(listener) ).tell( _.`package`( Package(recipe) ) )