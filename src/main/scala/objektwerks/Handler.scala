package objektwerks

import ox.channels.ActorRef

sealed trait Handler

final class Sanitizer(listener: ActorRef[Listener]) extends Handler:
  def sanitize(sanitize: Sanitize): Unit =
    listener.tell( _.onEvent:
      Sanitized:
        List( "Sanitized brewing implements." )
    )


final class Preparer(listener: ActorRef[Listener]) extends Handler:
  def prepare(prepare: Prepare): Unit =
    listener.tell( _.onEvent:
      Prepared:
        List(
          "Prepared the following recipe ingrediants:",
          s"Grains: ${prepare.recipe.grains}",
          s"Hops: ${prepare.recipe.hops}",
          s"Adjuncts: ${prepare.recipe.adjuncts}",
          s"Yeasts: ${prepare.recipe.yeasts}"
        )
    )

final class Malter(listener: ActorRef[Listener]) extends Handler:
  def malt(malt: Malt): Unit =
    listener.tell( _.onEvent:
      Malted:
        List( "Malted grains." )
    )

final class Miller(listener: ActorRef[Listener]) extends Handler:
  def mill(mill: Mill): Unit =
    listener.tell( _.onEvent:
      Milled:
        List( "Milled grains into a grist." )
    )

final class Masher(listener: ActorRef[Listener]) extends Handler:
  def mash(mash: Mash): Unit =
    listener.tell( _.onEvent:
      Mashed(
        List(
          s"Mashed grist into a wort within this temp range / duration: ${mash.recipe.mashingTempRangeDuration}",
          s"Optionally added adjuncts: ${mash.recipe.adjuncts}",
          s"pH should be: ${mash.recipe.pH}"
        ),
        pH = 5.6
      )
    ) // Calculate pH!

final class Lauterer(listener: ActorRef[Listener]) extends Handler:
  def lauter(lauter: Lauter): Unit =
    listener.tell( _.onEvent:
      Lautered(
        List( "Lautered wort." )
      )
    )

final class Sparger(listener: ActorRef[Listener]) extends Handler:
  def sparge(sparge: Sparge): Unit =
    listener.tell( _.onEvent:
      Sparged(
        List( s"Should have a mash efficiency within this range: ${sparge.recipe.mashEfficiency}" ),
        mashEfficiency = 70
      )
    ) // Calculate mash efficiency!

final class Boiler(listener: ActorRef[Listener]) extends Handler:
  def boil(boil: Boil): Unit =
    listener.tell( _.onEvent:
      Boiled(
        List(
          s"Boiled wort within this temp range / duration: ${boil.recipe.boilingTempRangeDuration}",
          s"Added hops: ${boil.recipe.hops}",
          s"Optionally added adjuncts: ${boil.recipe.adjuncts}"
        )
      )
    )

final class Cooler extends Handler:
  def cool(cool: Cool): Cooled =
    Cooled(
      List(
        s"Cooled the wort within this temp range: ${cool.recipe.coolingTempRange}"
      )
    )

final class Whirlpooler extends Handler:
  def whirlpool(whirlpool: Whirlpool): Whirlpooled =
    Whirlpooled(
      List(
        s"Optionally added hops: ${whirlpool.recipe.hops}",
        s"Should have an orginal gravity within this range: ${whirlpool.recipe.originalGravity}"
      ),
      originalGravity = 1.030
    ) // Calculate original gravity!

final class Fermenter extends Handler:
  def ferment(ferment: Ferment): Fermented =
    Fermented(
      List(
        s"Fermented within this temp range / duration: ${ferment.recipe.fermentatingTempRangeDuration}",
        s"Should have a final gravity within this range: ${ferment.recipe.finalGravity}"
      ),
      finalGravity = 1.015
    ) // Calculate final gravity!

final class Conditioner extends Handler:
  def condition(condition: Condition): Conditioned =
    Conditioned(
      List(
        s"Conditioned within this temp range / duration: ${condition.recipe.conditioningTempRangeDuration}",
        s"Optionally added adjuncts: ${condition.recipe.adjuncts}",
        s"Optionally added hops: ${condition.recipe.hops}",
        s"Should have an SRM color within this range: ${condition.recipe.srmColor}"
      ),
      srmColor = 7
    ) // Calculate SRM color!

final class Packager extends Handler:
  def `package`(`package`: Package): Packaged =
    Packaged(
      List(
        s"Conditioned within this temp range / duration: ${`package`.recipe.packagingTempRangeDuration}",
        s"Hop bitterness should be within this range: ${`package`.recipe.ibuBitterness}",
        s"Alcohol by volume should be within this range: ${`package`.recipe.alcoholByVolume}",
        s"Alcohol by weight should be within this range: ${`package`.recipe.alcoholByWeight}",
        s"Calories should be within this range: ${`package`.recipe.calories}",
        s"Should have a brew efficiency within this range: ${`package`.recipe.brewhouseEfficiency}",
        s"Should refrigerate within this temp range: ${`package`.recipe.refrigerateTempRange}",
      ),
      ibuBitterness = 68,
      alcoholByVolume = 6.4,
      alcoholByWeight = 6.0,
      calories = 190,
      brewhouseEfficiency = 71
    ) // Calculate ibu, abv, abw, calories and be!