package objektwerks

sealed trait Actor

final class Sanitizer extends Actor:
  def sanitize(sanitize: Sanitize): Sanitized =
    Sanitized(
      List( s"*** Sanitizer sanitized brewing implements." )
    )

final class Preparer extends Actor:
  def prepare(prepare: Prepare): Prepared =
    Prepared(
      List(
        s"*** Preparer prepared the following recipe ingrediants:",
        s"*** Grains ${prepare.recipe.grains}",
        s"*** Hops ${prepare.recipe.hops}",
        s"*** Adjuncts ${prepare.recipe.adjuncts}",
        s"*** Yeasts ${prepare.recipe.yeasts}"
      )
    )

final class Malter extends Actor:
  def malt(malt: Malt): Malted =
    Malted(
      List( s"*** Malter malted grains." )
    )

final class Miller extends Actor:
  def mill(mill: Mill): Milled =
    Milled(
      List( s"*** Miller crushed grains into a grist." )
    )

final class Masher extends Actor:
  def mash(mash: Mash): Mashed =
    Mashed(
      List(
        s"*** Masher ph should be: ${mash.recipe.pH}",
        s"*** Masher mashed grist into a wort at a temp of: ${mash.recipe.mashingTemp} for a duration of: ${mash.recipe.mashingDuration}",
        s"*** Masher optionally added adjuncts: ${mash.recipe.adjuncts}"
      ),
      pH = 5.6
    ) // Calculate pH!

final class Lauterer extends Actor:
  def lauter(lauter: Lauter): Lautered =
    Lautered(
      List(
        s"*** Lauter lautered wort."
      )
    )

final class Sparger extends Actor:
  def sparge(sparge: Sparge): Sparged =
    Sparged(
      List(
        s"*** Sparger mash efficiency should be with in this range: ${sparge.recipe.mashEfficiency}",
        s"*** Sparger sparged wort."
      ),
      mashEfficiency = 70
    ) // Calculate mashed efficiency!

final class Boiler extends Actor:
  def boil(boil: Boil): Boiled =
    Boiled(
      List(
        s"*** Boiler boiled the wort to a temp of: ${boil.recipe.boilingTemp} for a duration of: ${boil.recipe.boilingDuration}",
        s"*** Boiler added hops: ${boil.recipe.hops}",
        s"*** Boiler optionally added adjuncts: ${boil.recipe.adjuncts}"
      )
    )

final class Cooler extends Actor:
  def cool(cool: Cool): Cooled =
    Cooled(
      List(
        s"*** Cooler cooled the wort to a temp of: ${cool.recipe.coolingTemp}"
      )
    )

final class Whirlpooler extends Actor:
  def whirlpool(whirlpool: Whirlpool): Whirlpooled =
    Whirlpooled(
      List(
        s"*** Whirlpooler should have an orginal gravity within this range: ${whirlpool.recipe.originalGravity}",
        s"*** Whirlpooler optionally added hops: ${whirlpool.recipe.hops}"
      ),
      originalGravity = 1.030
    ) // Calculate original gravity!

final class Fermenter extends Actor:
  def ferment(ferment: Ferment): Fermented =
    Fermented(
      List(
        s"*** Fermenter should ferment within this temp range: ${ferment.recipe.fermentatingTemp}",
        s"*** Fermenter should have a final gravity within this range: ${ferment.recipe.finalGravity}"
      ),
      finalGravity = 1.015
    ) // Calculate final gravity!

final class Conditioner extends Actor:
  def condition(condition: Condition): Conditioned =
    Conditioned(
      List(
        s"*** Conditioner should condition within this temp range: ${condition.recipe.conditioningTemp}",
        s"*** Conditioner should have an SRM color within this range: ${condition.recipe.srmColor}",
        s"*** Conditioner optionally added adjuncts: ${condition.recipe.adjuncts}",
        s"*** Conditioner optionally added hops: ${condition.recipe.hops}"
      ),
      srmColor = 7
    ) // Calculate SRM color!

final class Packager extends Actor:
  def `package`(`package`: Package): Packaged =
    Packaged(
      List(
        s"*** Packager should condition within this temp range: ${`package`.recipe.packagingTemp}",
        s"*** Packager ibu bitterness should be within this range: ${`package`.recipe.ibuBitterness}",
        s"*** Packager alcohol by volume should be within this range: ${`package`.recipe.alcoholByVolume}",
        s"*** Packager alcohol by weight should be within this range: ${`package`.recipe.alcoholByWeight}",
        s"*** Packager calories should be within this range: ${`package`.recipe.calories}",
        s"*** Packager brew efficiency should be within this range: ${`package`.recipe.brewhouseEfficiency}"
      ),
      ibuBitterness = 68,
      alcoholByVolume = 6.4,
      alcoholByWeight = 6.0,
      calories = 190,
      brewhouseEfficiency = 71
    ) // Calculate ibu, abv, abw, calories and be!