package objektwerks

import ox.channels.{Actor, ActorRef}
import ox.supervised

final class Brewer(listener: Listener):
  def brew(command: Command): Unit =
    command match
      case sanitize: Sanitize =>
        supervised:
          Actor
            .create( Sanitizer(listener) )
            .ask( _.sanitize( sanitize ) )
      case prepare: Prepare =>
        supervised:
          Actor
            .create( Preparer(listener) )
            .ask( _.prepare( prepare ) )
      case malt: Malt =>
        supervised:
          Actor
            .create( Malter(listener) )
            .ask( _.malt( malt ) )
      case mill: Mill =>
        supervised:
          Actor
            .create( Miller(listener) )
            .ask( _.mill( mill ) )
      case mash: Mash =>
        supervised:
          Actor
            .create( Masher(listener) )
            .ask( _.mash( mash ) )
      case logMashTempPh: LogMashingTempPh =>
        supervised:
          Actor
            .create( Masher(listener) )
            .ask( _.logMashTempPh( logMashTempPh ) )
      case lauter: Lauter =>
        supervised:
          Actor
            .create( Lauterer(listener) )
            .ask( _.lauter( lauter ) )
      case sparge: Sparge =>
        supervised:
          Actor
            .create( Sparger(listener) )
            .ask( _.sparge( sparge ) )
      case logMashEfficiency: LogMashEfficiency =>
        supervised:
          Actor
            .create( Sparger(listener) )
            .ask( _.logMashEfficiency( logMashEfficiency ) )
      case boil: Boil =>
        supervised:
          Actor
            .create( Boiler(listener) )
            .ask( _.boil( boil ) )
      case cool: Cool =>
        supervised:
          Actor
            .create( Cooler(listener) )
            .ask( _.cool( cool ) )
      case whirlpool: Whirlpool =>
        supervised:
          Actor
            .create( Whirlpooler(listener) )
            .ask( _.whirlpool( whirlpool ) )
      case logBoilingCoolingTempOriginalGravity: LogBoilingCoolingTempOriginalGravity =>
        supervised:
          Actor
            .create( Whirlpooler(listener) )
            .ask( _.logBoilingCoolingTempOriginalGravity( logBoilingCoolingTempOriginalGravity ) )
      case ferment: Ferment =>
        supervised:
          Actor
            .create( Fermenter(listener) )
            .ask( _.ferment( ferment ) )
      case logFermentingTempFinalGravity: LogFermentingTempFinalGravity =>
        supervised:
          Actor
            .create( Fermenter(listener) )
            .ask( _.logFermentingTempFinalGravity( logFermentingTempFinalGravity ) )
      case condition: Condition =>
        supervised:
          Actor
            .create( Conditioner(listener) )
            .ask( _.condition( condition ) )
      case logConditioningTempSrmColor: LogConditioningTempSrmColor =>
        supervised:
          Actor
            .create( Conditioner(listener) )
            .ask( _.logConditioningSrmColor( logConditioningTempSrmColor ) )
      case keg: Keg =>
        supervised:
          Actor
            .create( Kegger(listener) )
            .ask( _.keg( keg ) )
      case logKeggingTempBrewhouseEfficiency: LogKeggingTempBrewhouseEfficiency =>
        supervised:
          Actor
            .create( Kegger(listener) )
            .ask( _.logKeggingTempBrewhouseEfficiency( logKeggingTempBrewhouseEfficiency ) )

final class Sanitizer(listener: Listener):
  def sanitize(sanitize: Sanitize): Unit =
    listener.onEvent(
      Sanitizing(
        List( "Sanitizing brewing components." )
      )
    )
    listener.onEvent(
      Sanitized(
        List( "Sanitized brewing components." )
      )
    )


final class Preparer(listener: Listener):
  def prepare(prepare: Prepare): Unit =
    listener.onEvent(
      Preparing(
        List( "Preparing recipe ingredients." )
      )
    )
    listener.onEvent(
      Prepared(
        List(
          "Preparing the following recipe ingredients:",
          s"Grains: ${prepare.recipe.grains}",
          s"Hops: ${prepare.recipe.hops}",
          s"Adjuncts: ${prepare.recipe.adjuncts}",
          s"Yeasts: ${prepare.recipe.yeasts}",
          "Prepared all recipe ingredients."
        )
      )
    )

final class Malter(listener: Listener):
  def malt(malt: Malt): Unit =
    listener.onEvent(
      Malting(
        List( "Malting grains." )
      )
    )
    listener.onEvent(
      Malted(
        List( "Malted grains." )
      )
    )

final class Miller(listener: Listener):
  def mill(mill: Mill): Unit =
    listener.onEvent(
      Milling(
        List( "Milling grains into a grist." )
      )
    )
    listener.onEvent(
      Milled(
        List( "Milled grains into a grist." )
      )
    )

final class Masher(listener: Listener):
  def mash(mash: Mash): Unit =
    listener.onEvent(
      Mashing(
        List( "Mashing grist into wort." )
      )
    )
    listener.onEvent(
      Mashed(
        List(
          s"Mashed grist into a wort within this temp range / duration: ${mash.recipe.mashingTempDuration}",
          s"Optionally added adjuncts: ${mash.recipe.adjuncts}",
          s"pH should be: ${mash.recipe.pH}",
          "Mashed wort."
        )
      )
    )
  def logMashTempPh(logMashTempPh: LogMashingTempPh): Unit =
    listener.onEvent(
      MashingTempPhLogged(
        mashingTemp = logMashTempPh.mashingTemp,
        pH = logMashTempPh.pH
      )
    )

final class Lauterer(listener: Listener):
  def lauter(lauter: Lauter): Unit =
    listener.onEvent(
      Lautering(
        List( "Lautering wort." )
      )
    )
    listener.onEvent(
      Lautered(
        List( "Lautered wort." )
      )
    )

final class Sparger(listener: Listener):
  def sparge(sparge: Sparge): Unit =
    listener.onEvent(
      Sparging(
        List( "Sparging wort." )
      )
    )
    listener.onEvent(
      Sparged(
        List(
          s"Should have a mash efficiency within this range: ${sparge.recipe.mashEfficiency}",
          "Sparged wort."
        )
      )
    )
  def logMashEfficiency(logMashEfficiency: LogMashEfficiency): Unit =
    listener.onEvent(
      MashEfficiencyLogged(
        mashEfficiency = Batch.mashEfficiency(logMashEfficiency.actualMashExtract, logMashEfficiency.recipe.potentialMashExtract)
      )
    )

final class Boiler(listener: Listener):
  def boil(boil: Boil): Unit =
    listener.onEvent(
      Boiling(
        List( "Boiling wort." )
      )
    )
    listener.onEvent(
      Boiled(
        List(
          s"Boiled wort within this temp range / duration: ${boil.recipe.boilingTempDuration}",
          s"Added hops: ${boil.recipe.hops}",
          s"Optionally added adjuncts: ${boil.recipe.adjuncts}",
          "Boiled wort."
        )
      )
    )

final class Cooler(listener: Listener):
  def cool(cool: Cool): Unit =
    listener.onEvent(
      Cooling(
        List( "Cooling wort." )
      )
    )
    listener.onEvent(
      Cooled(
        List(
          s"Cooled the wort within this temp range: ${cool.recipe.coolingTempRange}",
          "Cooled wort."
        )
      )
    )

final class Whirlpooler(listener: Listener):
  def whirlpool(whirlpool: Whirlpool): Unit =
    listener.onEvent(
      Whirlpooling(
        List( "Whirlpooling wort." )
      )
    )
    listener.onEvent(
      Whirlpooled(
        List(
          s"Optionally added hops: ${whirlpool.recipe.hops}",
          s"Should have an orginal gravity within this range: ${whirlpool.recipe.originalGravity}",
          "Whirlpooled wort."
        )
      )
    )
  def logBoilingCoolingTempOriginalGravity(logBoilingCoolingTempOriginalGravity: LogBoilingCoolingTempOriginalGravity): Unit =
    listener.onEvent(
      BoilingCoolingTempOriginalGravityLogged(
        boilingTemp = logBoilingCoolingTempOriginalGravity.boilingTemp,
        coolingTemp = logBoilingCoolingTempOriginalGravity.coolingTemp,
        originalGravity = logBoilingCoolingTempOriginalGravity.originalGravity
      )
    )

final class Fermenter(listener: Listener):
  def ferment(ferment: Ferment): Unit =
    listener.onEvent(
      Fermenting(
        List( "Fermenting wort." )
      )
    )
    listener.onEvent(
      Fermented(
        List(
          s"Fermented within this temp range / duration: ${ferment.recipe.fermentatingTempDuration}",
          s"Should have a final gravity within this range: ${ferment.recipe.finalGravity}",
          "Fermented wort."
        )
      )
    )
  def logFermentingTempFinalGravity(logFermentingTempFinalGravity: LogFermentingTempFinalGravity): Unit =
    listener.onEvent(
      FermentingTempFinalGravityLogged(
        fermentingTemp = logFermentingTempFinalGravity.fermentingTemp,
        finalGravity = logFermentingTempFinalGravity.finalGravity
      )
    )

final class Conditioner(listener: Listener):
  def condition(condition: Condition): Unit =
    listener.onEvent(
      Conditioning(
        List( "Conditioning wort." )
      )
    )
    listener.onEvent(
      Conditioned(
        List(
          s"Conditioned within this temp range / duration: ${condition.recipe.conditioningTempDuration}",
          s"Optionally added adjuncts: ${condition.recipe.adjuncts}",
          s"Optionally added hops: ${condition.recipe.hops}",
          s"Should have an SRM color within this range: ${condition.recipe.srmColor}",
          "Conditioned wort."
        )
      )
    )
  def logConditioningSrmColor(logConditioningTempSrmColor: LogConditioningTempSrmColor): Unit =
    listener.onEvent(
      ConditioningTempSrmColorLogged(
        conditioningTemp = logConditioningTempSrmColor.conditioningTemp,
        srmColor = logConditioningTempSrmColor.srmColor
      )
    )

final class Kegger(listener: Listener):
  def keg(keg: Keg): Unit =
    listener.onEvent(
      Kegging(
        List( "Kegging wort." )
      )
    )
    listener.onEvent(
      Kegged(
        ibuBitterness = Batch.ibuBitterness(keg.recipe.hops),
        alcoholByVolume = Batch.alcoholByVolume(
          listener.ask( _.originalGravity ),
          listener.ask( _.finalGravity )
        ),
        alcoholByWeight = Batch.alcoholByWeight(
          Batch.alcoholByWeight(
            listener.ask( _.originalGravity ),
            listener.ask( _.finalGravity ) ),
            listener.ask( _.finalGravity )
          ),
        calories = Batch.calories(
          keg.recipe.packageVolume.volume,
          listener.ask( _.originalGravity ),
          listener.ask( _.finalGravity )
        ),
        List(
          s"Conditioned within this temp range / duration: ${keg.recipe.keggingTempDuration}",
          s"Hop bitterness should be within this range: ${keg.recipe.ibuBitterness}",
          s"Alcohol by volume should be within this range: ${keg.recipe.alcoholByVolume}",
          s"Alcohol by weight should be within this range: ${keg.recipe.alcoholByWeight}",
          s"Calories should be within this range: ${keg.recipe.calories}",
          s"Should have a brew efficiency within this range: ${keg.recipe.brewhouseEfficiency}",
          "Kegged wort."
        ),
      )
    )
  def logKeggingTempBrewhouseEfficiency(logKeggingTempBrewhouseEfficiency: LogKeggingTempBrewhouseEfficiency): Unit =
    listener.onEvent(
      KeggingTempBrewhouseEfficiencyLogged(
        keggingTemp = logKeggingTempBrewhouseEfficiency.keggingTemp,
        brewhouseEfficiency = Batch.brewhouseEfficiency(
          logKeggingTempBrewhouseEfficiency.actualFermentableExtract,
          logKeggingTempBrewhouseEfficiency.recipe.potentialFermentableExtract
        )
      )
    )