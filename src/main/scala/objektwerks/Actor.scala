package objektwerks

sealed trait Actor:
  def close: Unit = scribe.info(s"*** actor closing ...")

final class Sanitizer extends Actor:
  def sanitize(sanitize: Sanitize): Sanitized =
    scribe.info(s"*** Sanitized brewing implements.")
    Sanitized()

final class Preparer extends Actor:
  def prepare(prepare: Prepare): Prepared =
    scribe.info(s"*** Preparing recipe ingrediants.")
    scribe.info(s"*** Grains ${prepare.recipe.grains}")
    scribe.info(s"*** Hops ${prepare.recipe.hops}")
    scribe.info(s"*** Adjuncts ${prepare.recipe.adjuncts}")
    scribe.info(s"*** Yeasts ${prepare.recipe.yeasts}")
    scribe.info(s"*** Prepared recipe ingrediants.")
    Prepared()

final class Malter extends Actor:
  def malt(malt: Malt): Malted =
    scribe.info(s"*** Malted grains.")
    Malted()

final class Miller extends Actor:
  def mill(mill: Mill): Milled =
    scribe.info(s"*** Milled ( crushed ) grains to grist.")
    Milled()

final class Masher extends Actor:
  def mash(mash: Mash): Mashed =
    scribe.info(s"*** Mashed grains at temp: ${mash.recipe.mashingTemp} for duration: ${mash.recipe.mashingDuration}")
    Mashed()

final class Lauterer extends Actor:
  def lauter(lauter: Lauter): Lautered =
    scribe.info(s"*** Lauter grains.")
    Lautered()

final class Sparger extends Actor:
  def sparge(sparge: Sparge): Sparged =
    scribe.info(s"*** Sparge grains.")
    Sparged(mashedEfficienty = 70) // TODO!

final class Boiler extends Actor:
  def boil(boil: Boil): Boiled =
    scribe.info(s"*** Boil wort.")
    Boiled()

final class Cooler extends Actor:
  def cool(cool: Cool): Cooled =
    scribe.info(s"*** Cool wort.")
    Cooled()

final class Whirlpooler extends Actor:
  def whirlpool(whirlpool: Whirlpool): Whirlpooled =
    scribe.info(s"*** Whirlpool wort with orginal gravity ${whirlpool.recipe.originalGravity}") // TODO!
    Whirlpooled()

final class Fermenter extends Actor:
  def ferment(ferment: Ferment): Fermented =
    scribe.info(s"*** Fermenter wort with final gravity ${ferment.recipe.finalGravity}") // TODO!
    Fermented()

final class Conditioner extends Actor:
  def condition(condition: Condition): Conditioned = Conditioned()

final class Packager extends Actor:
  def `package`(`package`: Package): Packaged = Packaged()