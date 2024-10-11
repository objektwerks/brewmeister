package objektwerks

import ox.channels.ActorRef

sealed trait Handler

final class Sanitizer(listener: ActorRef[Listener]) extends Handler:
  def sanitize(sanitize: Sanitize): Unit =
    listener.tell( _.onEvent:
      Sanitized(
        sanitize.batchId,
        List( "Sanitized brewing implements." )
      )
    )


final class Preparer(listener: ActorRef[Listener]) extends Handler:
  def prepare(prepare: Prepare): Unit =
    listener.tell( _.onEvent:
      Prepared(
        prepare.batchId,
        List(
          "Prepared the following recipe ingrediants:",
          s"Grains: ${prepare.batch.recipe.grains}",
          s"Hops: ${prepare.batch.recipe.hops}",
          s"Adjuncts: ${prepare.batch.recipe.adjuncts}",
          s"Yeasts: ${prepare.batch.recipe.yeasts}"
        )
      )
    )

final class Malter(listener: ActorRef[Listener]) extends Handler:
  def malt(malt: Malt): Unit =
    listener.tell( _.onEvent:
      Malted(
        malt.batchId,
        List( "Malted grains." )
      )
    )

final class Miller(listener: ActorRef[Listener]) extends Handler:
  def mill(mill: Mill): Unit =
    listener.tell( _.onEvent:
      Milled(
        mill.batchId,
        List( "Milled grains into a grist." )
      )
    )

final class Masher(listener: ActorRef[Listener]) extends Handler:
  def mash(mash: Mash): Unit =
    listener.tell( _.onEvent:
      Mashed(
        mash.batchId,
        List(
          s"Mashed grist into a wort within this temp range / duration: ${mash.batch.recipe.mashingTempRangeDuration}",
          s"Optionally added adjuncts: ${mash.batch.recipe.adjuncts}",
          s"pH should be: ${mash.batch.recipe.pH}"
        ),
        pH = 5.6
      )
    ) // Calculate ph!

final class Lauterer(listener: ActorRef[Listener]) extends Handler:
  def lauter(lauter: Lauter): Unit =
    listener.tell( _.onEvent:
      Lautered(
        lauter.batchId,
        List( "Lautered wort." )
      )
    )

final class Sparger(listener: ActorRef[Listener]) extends Handler:
  def sparge(sparge: Sparge): Unit =
    listener.tell( _.onEvent:
      Sparged(
        sparge.batchId,
        List( s"Should have a mash efficiency within this range: ${sparge.batch.recipe.mashEfficiency}" ),
        mashEfficiency = 70
      )
    ) // Calculate mash efficiency!

final class Boiler(listener: ActorRef[Listener]) extends Handler:
  def boil(boil: Boil): Unit =
    listener.tell( _.onEvent:
      Boiled(
        boil.batchId,
        List(
          s"Boiled wort within this temp range / duration: ${boil.batch.recipe.boilingTempRangeDuration}",
          s"Added hops: ${boil.batch.recipe.hops}",
          s"Optionally added adjuncts: ${boil.batch.recipe.adjuncts}"
        )
      )
    )

final class Cooler(listener: ActorRef[Listener]) extends Handler:
  def cool(cool: Cool): Unit =
    listener.tell( _.onEvent:
      Cooled(
        cool.batchId,
        List( s"Cooled the wort within this temp range: ${cool.batch.recipe.coolingTempRange}" )
      )
    )

final class Whirlpooler(listener: ActorRef[Listener]) extends Handler:
  def whirlpool(whirlpool: Whirlpool): Unit =
    listener.tell( _.onEvent:
      Whirlpooled(
        whirlpool.batchId,
        List(
          s"Optionally added hops: ${whirlpool.batch.recipe.hops}",
          s"Should have an orginal gravity within this range: ${whirlpool.batch.recipe.originalGravity}"
        ),
        originalGravity = 1.030
      ) // Calculate original gravity!
    )

final class Fermenter(listener: ActorRef[Listener]) extends Handler:
  def ferment(ferment: Ferment): Unit =
    listener.tell( _.onEvent:
      Fermented(
        ferment.batchId,
        List(
          s"Fermented within this temp range / duration: ${ferment.batch.recipe.fermentatingTempRangeDuration}",
          s"Should have a final gravity within this range: ${ferment.batch.recipe.finalGravity}"
        ),
        finalGravity = 1.015
      ) // Calculate final gravity!
    )

final class Conditioner(listener: ActorRef[Listener]) extends Handler:
  def condition(condition: Condition): Unit =
    listener.tell( _.onEvent:
      Conditioned(
        condition.batchId,
        List(
          s"Conditioned within this temp range / duration: ${condition.batch.recipe.conditioningTempRangeDuration}",
          s"Optionally added adjuncts: ${condition.batch.recipe.adjuncts}",
          s"Optionally added hops: ${condition.batch.recipe.hops}",
          s"Should have an SRM color within this range: ${condition.batch.recipe.srmColor}"
        ),
        srmColor = 7
      ) // Calculate SRM color!
    )

final class Packager(listener: ActorRef[Listener]) extends Handler:
  def `package`(`package`: Package): Unit =
    listener.tell( _.onEvent:
      Packaged(
        `package`.batchId,
        List(
          s"Conditioned within this temp range / duration: ${`package`.batch.recipe.packagingTempRangeDuration}",
          s"Hop bitterness should be within this range: ${`package`.batch.recipe.ibuBitterness}",
          s"Alcohol by volume should be within this range: ${`package`.batch.recipe.alcoholByVolume}",
          s"Alcohol by weight should be within this range: ${`package`.batch.recipe.alcoholByWeight}",
          s"Calories should be within this range: ${`package`.batch.recipe.calories}",
          s"Should have a brew efficiency within this range: ${`package`.batch.recipe.brewhouseEfficiency}",
          s"Should refrigerate within this temp range: ${`package`.batch.recipe.refrigerateTempRange}",
        ),
        ibuBitterness = 68,
        alcoholByVolume = 6.4,
        alcoholByWeight = 6.0,
        calories = 190,
        brewhouseEfficiency = 71
      ) // Calculate ibu, abv, abw, calories and be!
    )