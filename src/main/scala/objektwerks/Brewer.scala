package objektwerks

import ox.channels.{Actor, ActorRef}
import ox.supervised

final class Brewer(batch: Batch, listener: ActorRef[Listener]):
  def sanitize: Unit =
    supervised:
      Actor.create( Sanitizer(listener) ).tell( _.sanitize( Sanitize(batch) ) )

  def prepare: Unit =
    supervised:
      Actor.create( Preparer(listener) ).tell( _.prepare( Prepare(batch) ) )

  def malt: Unit =
    supervised:
      Actor.create( Malter(listener) ).tell( _.malt( Malt(batch) ) )

  def mill: Unit =
    supervised:
      Actor.create( Miller(listener) ).tell( _.mill( Mill(batch) ) )

  def mash: Unit =
    supervised:
      Actor.create( Masher(listener) ).tell( _.mash( Mash(batch) ) )

  def lauter: Unit =
    supervised:
      Actor.create( Lauterer(listener) ).tell( _.lauter( Lauter(batch) ) )

  def sparge: Unit =
    supervised:
      Actor.create( Sparger(listener) ).tell( _.sparge( Sparge(batch) ) )

  def boil: Unit =
    supervised:
      Actor.create( Boiler(listener) ).tell( _.boil( Boil(batch) ) )

  def cool: Unit =
    supervised:
      Actor.create( Cooler(listener) ).tell( _.cool( Cool(batch) ) )

  def whirlpool: Unit =
    supervised:
      Actor.create( Whirlpooler(listener) ).tell( _.whirlpool( Whirlpool(batch) ) )

  def ferment: Unit =
    supervised:
      Actor.create( Fermenter(listener) ).tell( _.ferment( Ferment(batch) ) )

  def condition: Unit =
    supervised:
      Actor.create( Conditioner(listener) ).tell( _.condition( Condition(batch) ) )

  def `package`: Unit =
    supervised:
      Actor.create( Packager(listener) ).tell( _.`package`( Package(batch) ) )

final class Sanitizer(listener: ActorRef[Listener]):
  def sanitize(sanitize: Sanitize): Unit =
    listener.tell( _.onEvent:
      Sanitized(
        List( "Sanitized brewing implements." )
      )
    )


final class Preparer(listener: ActorRef[Listener]):
  def prepare(prepare: Prepare): Unit =
    listener.tell( _.onEvent:
      Prepared(
        List(
          "Prepared the following recipe ingrediants:",
          s"Grains: ${prepare.batch.recipe.grains}",
          s"Hops: ${prepare.batch.recipe.hops}",
          s"Adjuncts: ${prepare.batch.recipe.adjuncts}",
          s"Yeasts: ${prepare.batch.recipe.yeasts}"
        )
      )
    )

final class Malter(listener: ActorRef[Listener]):
  def malt(malt: Malt): Unit =
    listener.tell( _.onEvent:
      Malted(
        List( "Malted grains." )
      )
    )

final class Miller(listener: ActorRef[Listener]):
  def mill(mill: Mill): Unit =
    listener.tell( _.onEvent:
      Milled(
        List( "Milled grains into a grist." )
      )
    )

final class Masher(listener: ActorRef[Listener]):
  def mash(mash: Mash): Unit =
    listener.tell( _.onEvent:
      Mashed(
        List(
          s"Mashed grist into a wort within this temp range / duration: ${mash.batch.recipe.mashingTempDuration}",
          s"Optionally added adjuncts: ${mash.batch.recipe.adjuncts}",
          s"pH should be: ${mash.batch.recipe.pH}"
        ),
        pH = mash.batch.recipe.pH // get pH from brewer!
      )
    )

final class Lauterer(listener: ActorRef[Listener]):
  def lauter(lauter: Lauter): Unit =
    listener.tell( _.onEvent:
      Lautered(
        List( "Lautered wort." )
      )
    )

final class Sparger(listener: ActorRef[Listener]):
  def sparge(sparge: Sparge): Unit =
    listener.tell( _.onEvent:
      Sparged(
        List( s"Should have a mash efficiency within this range: ${sparge.batch.recipe.mashEfficiency}" ),
        mashEfficiency = sparge.batch.recipe.mashEfficiency.avg // get actual mash extract from brewer!
      )
    )

final class Boiler(listener: ActorRef[Listener]):
  def boil(boil: Boil): Unit =
    listener.tell( _.onEvent:
      Boiled(
        List(
          s"Boiled wort within this temp range / duration: ${boil.batch.recipe.boilingTempDuration}",
          s"Added hops: ${boil.batch.recipe.hops}",
          s"Optionally added adjuncts: ${boil.batch.recipe.adjuncts}"
        )
      )
    )

final class Cooler(listener: ActorRef[Listener]):
  def cool(cool: Cool): Unit =
    listener.tell( _.onEvent:
      Cooled(
        List( s"Cooled the wort within this temp range: ${cool.batch.recipe.coolingTempRange}" )
      )
    )

final class Whirlpooler(listener: ActorRef[Listener]):
  def whirlpool(whirlpool: Whirlpool): Unit =
    listener.tell( _.onEvent:
      Whirlpooled(
        List(
          s"Optionally added hops: ${whirlpool.batch.recipe.hops}",
          s"Should have an orginal gravity within this range: ${whirlpool.batch.recipe.originalGravity}"
        ),
        originalGravity = whirlpool.batch.recipe.originalGravity.avg // get OG from brewer!
      )
    )

final class Fermenter(listener: ActorRef[Listener]):
  def ferment(ferment: Ferment): Unit =
    listener.tell( _.onEvent:
      Fermented(
        List(
          s"Fermented within this temp range / duration: ${ferment.batch.recipe.fermentatingTempDuration}",
          s"Should have a final gravity within this range: ${ferment.batch.recipe.finalGravity}"
        ),
        finalGravity = ferment.batch.recipe.finalGravity.avg // get FG from brewer!
      )
    )

final class Conditioner(listener: ActorRef[Listener]):
  def condition(condition: Condition): Unit =
    listener.tell( _.onEvent:
      Conditioned(
        List(
          s"Conditioned within this temp range / duration: ${condition.batch.recipe.conditioningTempDuration}",
          s"Optionally added adjuncts: ${condition.batch.recipe.adjuncts}",
          s"Optionally added hops: ${condition.batch.recipe.hops}",
          s"Should have an SRM color within this range: ${condition.batch.recipe.srmColor}"
        ),
        srmColor = Metrics.srmColor(condition.batch.recipe.batchVolume, condition.batch.recipe.grains)
      )
    )

final class Packager(listener: ActorRef[Listener]):
  def `package`(`package`: Package): Unit =
    listener.tell( _.onEvent:
      Packaged(
        List(
          s"Conditioned within this temp range / duration: ${`package`.batch.recipe.packagingTempDuration}",
          s"Hop bitterness should be within this range: ${`package`.batch.recipe.ibuBitterness}",
          s"Alcohol by volume should be within this range: ${`package`.batch.recipe.alcoholByVolume}",
          s"Alcohol by weight should be within this range: ${`package`.batch.recipe.alcoholByWeight}",
          s"Calories should be within this range: ${`package`.batch.recipe.calories}",
          s"Should have a brew efficiency within this range: ${`package`.batch.recipe.brewhouseEfficiency}",
          s"Should refrigerate within this temp range: ${`package`.batch.recipe.refrigerateTempRange}",
        ),
        ibuBitterness = Metrics.ibuBitterness(`package`.batch.recipe.hops),
        alcoholByVolume = Metrics.alcoholByVolume( listener.ask( _.originalGravity ), listener.ask( _.finalGravity ) ),
        alcoholByWeight = Metrics.alcoholByWeight( Metrics.alcoholByVolume( listener.ask( _.originalGravity ), listener.ask( _.finalGravity ) ), listener.ask( _.finalGravity ) ),
        calories = `package`.batch.recipe.calories.avg, // calculate
        brewhouseEfficiency = `package`.batch.recipe.brewhouseEfficiency.avg // get BE from brewer!
      )
    )