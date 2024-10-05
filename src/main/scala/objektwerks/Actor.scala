package objektwerks

sealed trait Actor:
  def close: Unit = scribe.info(s"*** actor closing ...")

final class Sanitizer extends Actor:
  def sanitize(sanitize: Sanitize): Sanitized =
    scribe.info(s"*** Sanitized brewing implements.")
    Sanitized()

final class Preparer extends Actor:
  def prepare(prepare: Prepare): Prepared =
    scribe.info(s"*** Preparer preparing recipe ingrediants.")
    scribe.info(s"*** Grains ${prepare.recipe.grains}")
    scribe.info(s"*** Hops ${prepare.recipe.hops}")
    scribe.info(s"*** Adjuncts ${prepare.recipe.adjuncts}")
    scribe.info(s"*** Yeasts ${prepare.recipe.yeasts}")
    scribe.info(s"*** Preparer prepared recipe ingrediants.")
    Prepared()

final class Malter extends Actor:
  def malt(malt: Malt): Malted =
    scribe.info(s"*** Malter malted grains.")
    Malted()

final class Miller extends Actor:
  def mill(mill: Mill): Milled =
    scribe.info(s"*** Miller crushed grains into a grist.")
    Milled()

final class Masher extends Actor:
  def mash(mash: Mash): Mashed =
    scribe.info(s"*** Masher mashed grist into a wort at temp: ${mash.recipe.mashingTemp} for duration: ${mash.recipe.mashingDuration}")
    scribe.info(s"*** Masher ph should be: ${mash.recipe.pH}")
    Mashed(pH = 5.6) // Calculate pH!

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
    scribe.info(s"*** Boiler boiled wort to temp: ${boil.recipe.boilingTemp} for duration: ${boil.recipe.boilingDuration}")
    scribe.info(s"*** Boiler added hops: ${boil.recipe.hops}")
    Boiled()

final class Cooler extends Actor:
  def cool(cool: Cool): Cooled =
    scribe.info(s"*** Cooled wort to temp of: ${cool.recipe.coolingTemp}")
    Cooled()

final class Whirlpooler extends Actor:
  def whirlpool(whirlpool: Whirlpool): Whirlpooled =
    scribe.info(s"*** Whirlpooled wort with an orginal gravity of: ${whirlpool.recipe.originalGravity}") // Calculate original gravity!
    Whirlpooled(originalGravity = 1.030)

final class Fermenter extends Actor:
  def ferment(ferment: Ferment): Fermented =
    scribe.info(s"*** Fermented wort with a final gravity of: ${ferment.recipe.finalGravity}")
    Fermented(finalGravity = 1.015) // Calculate final gravity!

final class Conditioner extends Actor:
  def condition(condition: Condition): Conditioned =
    scribe.info(s"*** Conditioned wort with an SRM color of: ${condition.recipe.srmColor}")
    Conditioned(srmColor = 7) // Calculate SRM color!

final class Packager extends Actor:
  def `package`(`package`: Package): Packaged =
    scribe.info(s"*** Packaged ibu bitterness: TODO!") // Calculate ibu, abv, abw, calories and be!
    scribe.info(s"*** Packaged alcohol by volume: TODO!")
    scribe.info(s"*** Packaged alcohol by weight: TODO!")
    scribe.info(s"*** Packaged calories: TODO!")
    scribe.info(s"*** Packaged brew efficiency: TODO!")
    scribe.info(s"*** Packaged wort into keg.")
    Packaged(ibuBitterness = 68, alcoholByVolume = 6.4, alcoholByWeight = 6.0, calories = 190, brewhouseEfficiency = 71)