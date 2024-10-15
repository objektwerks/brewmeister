package objektwerks

import ox.channels.{Actor, ActorRef}
import ox.supervised

final class Brewer(listener: ActorRef[Listener]):
  def handle(command: Command): Unit =
    command match
      case sanitize: Sanitize =>
        supervised:
          Actor.create( Sanitizer(listener) ).tell( _.sanitize( sanitize ) )
      case prepare: Prepare =>
        supervised:
          Actor.create( Preparer(listener) ).tell( _.prepare( prepare ) )
      case malt: Malt =>
        supervised:
          Actor.create( Malter(listener) ).tell( _.malt( malt ) )
      case mill: Mill =>
        supervised:
          Actor.create( Miller(listener) ).tell( _.mill( mill ) )
      case mash: Mash =>
        supervised:
          Actor.create( Masher(listener) ).tell( _.mash( mash ) )
      case lauter: Lauter =>
        supervised:
          Actor.create( Lauterer(listener) ).tell( _.lauter( lauter ) )
      case sparge: Sparge =>
        supervised:
          Actor.create( Sparger(listener) ).tell( _.sparge( sparge ) )
      case boil: Boil =>
        supervised:
          Actor.create( Boiler(listener) ).tell( _.boil( boil ) )
      case cool: Cool =>
        supervised:
          Actor.create( Cooler(listener) ).tell( _.cool( cool ) )
      case whirlpool: Whirlpool =>
        supervised:
          Actor.create( Whirlpooler(listener) ).tell( _.whirlpool( whirlpool ) )
      case ferment: Ferment =>
        supervised:
          Actor.create( Fermenter(listener) ).tell( _.ferment( ferment ) )
      case condition: Condition =>
        supervised:
          Actor.create( Conditioner(listener) ).tell( _.condition( condition ) )
      case Package(batch, actualFermentableExtract) =>
        supervised:
          Actor.create( Packager(listener) ).tell( _.`package`( Package(batch, actualFermentableExtract) ) )

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
        pH = mash.pH
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
        mashEfficiency = Metrics.mashEfficiency(
          sparge.actualMashExtract,
          sparge.batch.recipe.potentialMashExtract
        )
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
        originalGravity = whirlpool.originalGravity
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
        finalGravity = ferment.finalGravity
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
        alcoholByVolume = Metrics.alcoholByVolume(
          listener.ask( _.originalGravity ),
          listener.ask( _.finalGravity )
        ),
        alcoholByWeight = Metrics.alcoholByWeight(
          Metrics.alcoholByVolume(
            listener.ask( _.originalGravity ),
            listener.ask( _.finalGravity ) ),
            listener.ask( _.finalGravity )
          ),
        calories = Metrics.calories(
          `package`.batch.recipe.packageVolume.volume,
          listener.ask( _.originalGravity ),
          listener.ask( _.finalGravity )
        ),
        brewhouseEfficiency = Metrics.brewhouseEfficiency(
          `package`.actualFermentableExtract,
          `package`.batch.recipe.potentialFermentableExtract
        )
      )
    )