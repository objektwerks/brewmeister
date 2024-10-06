package objektwerks

sealed trait Actor:
  def close: Unit = scribe.info(s"*** actor closing ...")

final class Sanitizer extends Actor:
  def sanitize(sanitize: Sanitize): Sanitized =
    Sanitized(
      List( s"*** Sanitized brewing implements." )
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
        s"*** Masher mashed grist into a wort at a temp of: ${mash.recipe.mashingTemp} for a duration of: ${mash.recipe.mashingDuration}",
        s"*** Masher optionally added adjuncts: ${mash.recipe.adjuncts}",
        s"*** Masher ph should be: ${mash.recipe.pH}"
      ),
      pH = 5.6
    ) // Calculate pH!

final class Lauterer extends Actor:
  def lauter(lauter: Lauter): Lautered =
    scribe.info(s"*** Lauter lautered wort: ${lauter.recipe.grains}")
    Lautered()

final class Sparger extends Actor:
  def sparge(sparge: Sparge): Sparged =
    scribe.info(s"*** Sparger sparged wort: ${sparge.recipe.grains}")
    scribe.info(s"*** Sparger mash efficiency should be with in this range: ${sparge.recipe.mashEfficiency}")
    Sparged(mashEfficiency = 70) // Calculate mashed efficiency!

final class Boiler extends Actor:
  def boil(boil: Boil): Boiled =
    scribe.info(s"*** Boiler boiled wort to a temp of: ${boil.recipe.boilingTemp} for a duration of: ${boil.recipe.boilingDuration}")
    scribe.info(s"*** Boiler added hops: ${boil.recipe.hops}")
    scribe.info(s"*** Boiler optionally added adjuncts: ${boil.recipe.adjuncts}")
    Boiled()

final class Cooler extends Actor:
  def cool(cool: Cool): Cooled =
    scribe.info(s"*** Cooler cooled wort to a temp of: ${cool.recipe.coolingTemp}")
    Cooled()

final class Whirlpooler extends Actor:
  def whirlpool(whirlpool: Whirlpool): Whirlpooled =
    scribe.info(s"*** Whirlpooler should have an orginal gravity within this range: ${whirlpool.recipe.originalGravity}")
    scribe.info(s"*** Whirlpooler optionally added hops: ${whirlpool.recipe.hops}")
    Whirlpooled(originalGravity = 1.030) // Calculate original gravity!

final class Fermenter extends Actor:
  def ferment(ferment: Ferment): Fermented =
    scribe.info(s"*** Fermenter should ferment within this temp range: ${ferment.recipe.fermentatingTemp}")
    scribe.info(s"*** Fermenter should have a final gravity within this range: ${ferment.recipe.finalGravity}")
    Fermented(finalGravity = 1.015) // Calculate final gravity!

final class Conditioner extends Actor:
  def condition(condition: Condition): Conditioned =
    scribe.info(s"*** Conditioner should condition within this temp range: ${condition.recipe.conditioningTemp}")
    scribe.info(s"*** Conditioner should have an SRM color within this range: ${condition.recipe.srmColor}")
    scribe.info(s"*** Conditioner optionally added adjuncts: ${condition.recipe.adjuncts}")
    scribe.info(s"*** Conditioner optionally added hops: ${condition.recipe.hops}")
    Conditioned(srmColor = 7) // Calculate SRM color!

final class Packager extends Actor:
  def `package`(`package`: Package): Packaged =
    scribe.info(s"*** Packager should condition within this temp range: ${`package`.recipe.packagingTemp}")
    scribe.info(s"*** Packager ibu bitterness: TODO!") // Calculate ibu, abv, abw, calories and be!
    scribe.info(s"*** Packager alcohol by volume: TODO!")
    scribe.info(s"*** Packager alcohol by weight: TODO!")
    scribe.info(s"*** Packager calories: TODO!")
    scribe.info(s"*** Packager brew efficiency: TODO!")
    scribe.info(s"*** Packager packaged wort in a keg.")
    Packaged(ibuBitterness = 68, alcoholByVolume = 6.4, alcoholByWeight = 6.0, calories = 190, brewhouseEfficiency = 71)