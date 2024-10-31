package objektwerks

import ox.channels.{Actor, ActorRef}
import ox.supervised

final class Brewer(listener: ActorRef[Listener]):
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

final class Sanitizer(listener: ActorRef[Listener]):
  def sanitize(sanitize: Sanitize): Unit =
    listener.ask( _.onEvent:
      Sanitizing(
        List( "Sanitizing brewing components." )
      )
    )
    listener.ask( _.onEvent:
      Sanitized(
        List( "Sanitized brewing components." )
      )
    )


final class Preparer(listener: ActorRef[Listener]):
  def prepare(prepare: Prepare): Unit =
    listener.ask( _.onEvent:
      Preparing(
        List( "Preparing recipe ingredients." )
      )
    )
    listener.ask( _.onEvent:
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

final class Malter(listener: ActorRef[Listener]):
  def malt(malt: Malt): Unit =
    listener.ask( _.onEvent:
      Malting(
        List( "Malting grains." )
      )
    )
    listener.ask( _.onEvent:
      Malted(
        List( "Malted grains." )
      )
    )

final class Miller(listener: ActorRef[Listener]):
  def mill(mill: Mill): Unit =
    listener.ask( _.onEvent:
      Milling(
        List( "Milling grains into a grist." )
      )
    )
    listener.ask( _.onEvent:
      Milled(
        List( "Milled grains into a grist." )
      )
    )

final class Masher(listener: ActorRef[Listener]):
  def mash(mash: Mash): Unit =
    listener.ask( _.onEvent:
      Mashing(
        List( "Mashing grist into wort." )
      )
    )
    listener.ask( _.onEvent:
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
    listener.ask( _.onEvent:
      MashingTempPhLogged(
        mashingTemp = logMashTempPh.mashingTemp,
        pH = logMashTempPh.pH
      )
    )

final class Lauterer(listener: ActorRef[Listener]):
  def lauter(lauter: Lauter): Unit =
    listener.ask( _.onEvent:
      Lautering(
        List( "Lautering wort." )
      )
    )
    listener.ask( _.onEvent:
      Lautered(
        List( "Lautered wort." )
      )
    )

final class Sparger(listener: ActorRef[Listener]):
  def sparge(sparge: Sparge): Unit =
    listener.ask( _.onEvent:
      Sparging(
        List( "Sparging wort." )
      )
    )
    listener.ask( _.onEvent:
      Sparged(
        List(
          s"Should have a mash efficiency within this range: ${sparge.recipe.mashEfficiency}",
          "Sparged wort."
        )
      )
    )
  def logMashEfficiency(logMashEfficiency: LogMashEfficiency): Unit =
    listener.ask( _.onEvent:
      MashEfficiencyLogged(
        mashEfficiency = Batch.mashEfficiency(logMashEfficiency.actualMashExtract, logMashEfficiency.recipe.potentialMashExtract)
      )
    )

final class Boiler(listener: ActorRef[Listener]):
  def boil(boil: Boil): Unit =
    listener.ask( _.onEvent:
      Boiling(
        List( "Boiling wort." )
      )
    )
    listener.ask( _.onEvent:
      Boiled(
        List(
          s"Boiled wort within this temp range / duration: ${boil.recipe.boilingTempDuration}",
          s"Added hops: ${boil.recipe.hops}",
          s"Optionally added adjuncts: ${boil.recipe.adjuncts}",
          "Boiled wort."
        )
      )
    )

final class Cooler(listener: ActorRef[Listener]):
  def cool(cool: Cool): Unit =
    listener.ask( _.onEvent:
      Cooling(
        List( "Cooling wort." )
      )
    )
    listener.ask( _.onEvent:
      Cooled(
        List(
          s"Cooled the wort within this temp range: ${cool.recipe.coolingTempRange}",
          "Cooled wort."
        )
      )
    )

final class Whirlpooler(listener: ActorRef[Listener]):
  def whirlpool(whirlpool: Whirlpool): Unit =
    listener.ask( _.onEvent:
      Whirlpooling(
        List( "Whirlpooling wort." )
      )
    )
    listener.ask( _.onEvent:
      Whirlpooled(
        List(
          s"Optionally added hops: ${whirlpool.recipe.hops}",
          s"Should have an orginal gravity within this range: ${whirlpool.recipe.originalGravity}",
          "Whirlpooled wort."
        )
      )
    )
  def logBoilingCoolingTempOriginalGravity(logBoilingCoolingTempOriginalGravity: LogBoilingCoolingTempOriginalGravity): Unit =
    listener.ask( _.onEvent:
      BoilingCoolingTempOriginalGravityLogged(
        boilingTemp = logBoilingCoolingTempOriginalGravity.boilingTemp,
        coolingTemp = logBoilingCoolingTempOriginalGravity.coolingTemp,
        originalGravity = logBoilingCoolingTempOriginalGravity.originalGravity
      )
    )

final class Fermenter(listener: ActorRef[Listener]):
  def ferment(ferment: Ferment): Unit =
    listener.ask( _.onEvent:
      Fermenting(
        List( "Fermenting wort." )
      )
    )
    listener.ask( _.onEvent:
      Fermented(
        List(
          s"Fermented within this temp range / duration: ${ferment.recipe.fermentatingTempDuration}",
          s"Should have a final gravity within this range: ${ferment.recipe.finalGravity}",
          "Fermented wort."
        )
      )
    )
  def logFermentingTempFinalGravity(logFermentingTempFinalGravity: LogFermentingTempFinalGravity): Unit =
    listener.ask( _.onEvent:
      FermentingTempFinalGravityLogged(
        fermentingTemp = logFermentingTempFinalGravity.fermentingTemp,
        finalGravity = logFermentingTempFinalGravity.finalGravity
      )
    )

final class Conditioner(listener: ActorRef[Listener]):
  def condition(condition: Condition): Unit =
    listener.ask( _.onEvent:
      Conditioning(
        List( "Conditioning wort." )
      )
    )
    listener.ask( _.onEvent:
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
    listener.ask( _.onEvent:
      ConditioningTempSrmColorLogged(
        conditioningTemp = logConditioningTempSrmColor.conditioningTemp,
        srmColor = logConditioningTempSrmColor.srmColor
      )
    )

final class Kegger(listener: ActorRef[Listener]):
  def keg(keg: Keg): Unit =
    listener.ask( _.onEvent:
      Kegging(
        List( "Kegging wort." )
      )
    )
    listener.ask( _.onEvent:
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
    listener.ask( _.onEvent:
      KeggingTempBrewhouseEfficiencyLogged(
        keggingTemp = logKeggingTempBrewhouseEfficiency.keggingTemp,
        brewhouseEfficiency = Batch.brewhouseEfficiency(
          logKeggingTempBrewhouseEfficiency.actualFermentableExtract,
          logKeggingTempBrewhouseEfficiency.recipe.potentialFermentableExtract
        )
      )
    )