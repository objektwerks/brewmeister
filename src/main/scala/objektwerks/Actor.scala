package objektwerks

sealed trait Actor

final class Sanitizer extends Actor:
  def sanitize(sanitize: Sanitize): Sanitized =
    Sanitized(
      List( "Sanitized brewing implements." )
    )

final class Preparer extends Actor:
  def prepare(prepare: Prepare): Prepared =
    Prepared(
      List(
        "Prepared the following recipe ingrediants:",
        s"Grains: ${prepare.recipe.grains}",
        s"Hops: ${prepare.recipe.hops}",
        s"Adjuncts: ${prepare.recipe.adjuncts}",
        s"Yeasts: ${prepare.recipe.yeasts}"
      )
    )

final class Malter extends Actor:
  def malt(malt: Malt): Malted =
    Malted(
      List( "Malted grains." )
    )

final class Miller extends Actor:
  def mill(mill: Mill): Milled =
    Milled(
      List( "Milled grains into a grist." )
    )

final class Masher extends Actor:
  def mash(mash: Mash): Mashed =
    Mashed(
      List(
        s"ph should be: ${mash.recipe.pH}",
        s"Mashed grist into a wort at a temp of: ${mash.recipe.mashingTemp} for a duration of: ${mash.recipe.mashingDuration}",
        s"Optionally added adjuncts: ${mash.recipe.adjuncts}"
      ),
      pH = 5.6
    ) // Calculate pH!

final class Lauterer extends Actor:
  def lauter(lauter: Lauter): Lautered =
    Lautered(
      List( "Lautered wort." )
    )

final class Sparger extends Actor:
  def sparge(sparge: Sparge): Sparged =
    Sparged(
      List(
        s"Mash efficiency should be with in this range: ${sparge.recipe.mashEfficiency}",
        "Sparged wort."
      ),
      mashEfficiency = 70
    ) // Calculate mashed efficiency!

final class Boiler extends Actor:
  def boil(boil: Boil): Boiled =
    Boiled(
      List(
        s"Boiled the wort to a temp of: ${boil.recipe.boilingTemp} for a duration of: ${boil.recipe.boilingDuration}",
        s"Added hops: ${boil.recipe.hops}",
        s"Optionally added adjuncts: ${boil.recipe.adjuncts}"
      )
    )

final class Cooler extends Actor:
  def cool(cool: Cool): Cooled =
    Cooled(
      List(
        s"Cooled the wort to a temp of: ${cool.recipe.coolingTemp}"
      )
    )

final class Whirlpooler extends Actor:
  def whirlpool(whirlpool: Whirlpool): Whirlpooled =
    Whirlpooled(
      List(
        s"Should have an orginal gravity within this range: ${whirlpool.recipe.originalGravity}",
        s"Optionally added hops: ${whirlpool.recipe.hops}"
      ),
      originalGravity = 1.030
    ) // Calculate original gravity!

final class Fermenter extends Actor:
  def ferment(ferment: Ferment): Fermented =
    Fermented(
      List(
        s"Should ferment within this temp range: ${ferment.recipe.fermentatingTemp}",
        s"Should have a final gravity within this range: ${ferment.recipe.finalGravity}"
      ),
      finalGravity = 1.015
    ) // Calculate final gravity!

final class Conditioner extends Actor:
  def condition(condition: Condition): Conditioned =
    Conditioned(
      List(
        s"Should condition within this temp range: ${condition.recipe.conditioningTemp}",
        s"Should have an SRM color within this range: ${condition.recipe.srmColor}",
        s"Optionally added adjuncts: ${condition.recipe.adjuncts}",
        s"Optionally added hops: ${condition.recipe.hops}"
      ),
      srmColor = 7
    ) // Calculate SRM color!

final class Packager extends Actor:
  def `package`(`package`: Package): Packaged =
    Packaged(
      List(
        s"Should condition within this temp range: ${`package`.recipe.packagingTemp}",
        s"Hop bitterness should be within this range: ${`package`.recipe.ibuBitterness}",
        s"Alcohol by volume should be within this range: ${`package`.recipe.alcoholByVolume}",
        s"Alcohol by weight should be within this range: ${`package`.recipe.alcoholByWeight}",
        s"Calories should be within this range: ${`package`.recipe.calories}",
        s"Brew efficiency should be within this range: ${`package`.recipe.brewhouseEfficiency}"
      ),
      ibuBitterness = 68,
      alcoholByVolume = 6.4,
      alcoholByWeight = 6.0,
      calories = 190,
      brewhouseEfficiency = 71
    ) // Calculate ibu, abv, abw, calories and be!