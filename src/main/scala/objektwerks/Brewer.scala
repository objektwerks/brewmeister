package objektwerks

import ox.channels.{Actor, ActorRef}
import ox.supervised

final class Brewer(listener: ActorRef[Listener]):
  def handle(command: Command): Unit =
    command match
      case sanitize: Sanitize =>
        supervised:
          Actor
            .create( Sanitizer(listener) )
            .tell( _.sanitize( sanitize ) )
      case prepare: Prepare =>
        supervised:
          Actor
            .create( Preparer(listener) )
            .tell( _.prepare( prepare ) )
      case malt: Malt =>
        supervised:
          Actor
            .create( Malter(listener) )
            .tell( _.malt( malt ) )
      case mill: Mill =>
        supervised:
          Actor
            .create( Miller(listener) )
            .tell( _.mill( mill ) )
      case mash: Mash =>
        supervised:
          Actor
            .create( Masher(listener) )
            .tell( _.mash( mash ) )
      case logMashTempPh: LogMashTempPh =>
        supervised:
          Actor
            .create( Masher(listener) )
            .tell( _.logMashTempPh( logMashTempPh ) )
      case lauter: Lauter =>
        supervised:
          Actor
            .create( Lauterer(listener) )
            .tell( _.lauter( lauter ) )
      case sparge: Sparge =>
        supervised:
          Actor
            .create( Sparger(listener) )
            .tell( _.sparge( sparge ) )
      case logMashEfficiency: LogMashEfficiency =>
        supervised:
          Actor
            .create( Sparger(listener) )
            .tell( _.logMashEfficiency( logMashEfficiency ) )
      case boil: Boil =>
        supervised:
          Actor
            .create( Boiler(listener) )
            .tell( _.boil( boil ) )
      case cool: Cool =>
        supervised:
          Actor
            .create( Cooler(listener) )
            .tell( _.cool( cool ) )
      case whirlpool: Whirlpool =>
        supervised:
          Actor
            .create( Whirlpooler(listener) )
            .tell( _.whirlpool( whirlpool ) )
      case logBoilingCoolingTempOriginalGravity: LogBoilingCoolingTempOriginalGravity =>
        supervised:
          Actor
            .create( Whirlpooler(listener) )
            .tell( _.logBoilingCoolingTempOriginalGravity( logBoilingCoolingTempOriginalGravity ) )
      case ferment: Ferment =>
        supervised:
          Actor
            .create( Fermenter(listener) )
            .tell( _.ferment( ferment ) )
      case logFermentingTempFinalGravity: LogFermentingTempFinalGravity =>
        supervised:
          Actor
            .create( Fermenter(listener) )
            .tell( _.logFermentingTempFinalGravity( logFermentingTempFinalGravity ) )
      case condition: Condition =>
        supervised:
          Actor
            .create( Conditioner(listener) )
            .tell( _.condition( condition ) )
      case logSrmColor: LogConditioningTempSrmColor =>
        supervised:
          Actor
            .create( Conditioner(listener) )
            .tell( _.logConditioningSrmColor( logSrmColor ) )
      case keg: Keg =>
        supervised:
          Actor
            .create( Kegger(listener) )
            .tell( _.keg( keg ) )
      case logBrewhouseEfficiency: LogBrewhouseEfficiency =>
        supervised:
          Actor
            .create( Kegger(listener) )
            .tell( _.logBrewhouseEfficiency( logBrewhouseEfficiency ) )

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
          "Preparing the following recipe ingrediants:",
          s"Grains: ${prepare.recipe.grains}",
          s"Hops: ${prepare.recipe.hops}",
          s"Adjuncts: ${prepare.recipe.adjuncts}",
          s"Yeasts: ${prepare.recipe.yeasts}",
          "Prepared all recipe ingrediants."
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
          s"Mashed grist into a wort within this temp range / duration: ${mash.recipe.mashingTempDuration}",
          s"Optionally added adjuncts: ${mash.recipe.adjuncts}",
          s"pH should be: ${mash.recipe.pH}",
          "Mashed wort."
        )
      )
    )
  def logMashTempPh(logMashTempPh: LogMashTempPh): Unit =
    listener.tell( _.onEvent:
      MashTempPhLogged(
        mashTemp = logMashTempPh.mashTemp,
        pH = logMashTempPh.pH
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
        List( s"Should have a mash efficiency within this range: ${sparge.recipe.mashEfficiency}" )
      )
    )
  def logMashEfficiency(logMashEfficiency: LogMashEfficiency): Unit =
    listener.tell( _.onEvent:
      MashEfficiencyLogged(
        mashEfficiency = Batch.mashEfficiency(logMashEfficiency.actualMashExtract, logMashEfficiency.recipe.potentialMashExtract)
      )
    )

final class Boiler(listener: ActorRef[Listener]):
  def boil(boil: Boil): Unit =
    listener.tell( _.onEvent:
      Boiled(
        List(
          s"Boiled wort within this temp range / duration: ${boil.recipe.boilingTempDuration}",
          s"Added hops: ${boil.recipe.hops}",
          s"Optionally added adjuncts: ${boil.recipe.adjuncts}"
        )
      )
    )

final class Cooler(listener: ActorRef[Listener]):
  def cool(cool: Cool): Unit =
    listener.tell( _.onEvent:
      Cooled(
        List( s"Cooled the wort within this temp range: ${cool.recipe.coolingTempRange}" )
      )
    )

final class Whirlpooler(listener: ActorRef[Listener]):
  def whirlpool(whirlpool: Whirlpool): Unit =
    listener.tell( _.onEvent:
      Whirlpooled(
        List(
          s"Optionally added hops: ${whirlpool.recipe.hops}",
          s"Should have an orginal gravity within this range: ${whirlpool.recipe.originalGravity}"
        )
      )
    )
  def logBoilingCoolingTempOriginalGravity(logBoilingCoolingTempOriginalGravity: LogBoilingCoolingTempOriginalGravity): Unit =
    listener.tell( _.onEvent:
      BoilingCoolingTempOriginalGravityLogged(
        boilingTemp = logBoilingCoolingTempOriginalGravity.boilingTemp,
        coolingTemp = logBoilingCoolingTempOriginalGravity.coolingTemp,
        originalGravity = logBoilingCoolingTempOriginalGravity.originalGravity
      )
    )

final class Fermenter(listener: ActorRef[Listener]):
  def ferment(ferment: Ferment): Unit =
    listener.tell( _.onEvent:
      Fermented(
        List(
          s"Fermented within this temp range / duration: ${ferment.recipe.fermentatingTempDuration}",
          s"Should have a final gravity within this range: ${ferment.recipe.finalGravity}"
        )
      )
    )
  def logFermentingTempFinalGravity(logFermentingTempFinalGravity: LogFermentingTempFinalGravity): Unit =
    listener.tell( _.onEvent:
      FermentingTempFinalGravityLogged(
        fermentingTemp = logFermentingTempFinalGravity.fermentingTemp,
        finalGravity = logFermentingTempFinalGravity.finalGravity
      )
    )

final class Conditioner(listener: ActorRef[Listener]):
  def condition(condition: Condition): Unit =
    listener.tell( _.onEvent:
      Conditioned(
        List(
          s"Conditioned within this temp range / duration: ${condition.recipe.conditioningTempDuration}",
          s"Optionally added adjuncts: ${condition.recipe.adjuncts}",
          s"Optionally added hops: ${condition.recipe.hops}",
          s"Should have an SRM color within this range: ${condition.recipe.srmColor}"
        )
      )
    )
  def logConditioningSrmColor(logConditioningTempSrmColor: LogConditioningTempSrmColor): Unit =
    listener.tell( _.onEvent:
      ConditioningTempSrmColorLogged(
        conditioningTemp = logConditioningTempSrmColor.conditioningTemp,
        srmColor = logConditioningTempSrmColor.srmColor
      )
    )

final class Kegger(listener: ActorRef[Listener]):
  def keg(keg: Keg): Unit =
    listener.tell( _.onEvent:
      Kegged(
        List(
          s"Conditioned within this temp range / duration: ${keg.recipe.packagingTempDuration}",
          s"Hop bitterness should be within this range: ${keg.recipe.ibuBitterness}",
          s"Alcohol by volume should be within this range: ${keg.recipe.alcoholByVolume}",
          s"Alcohol by weight should be within this range: ${keg.recipe.alcoholByWeight}",
          s"Calories should be within this range: ${keg.recipe.calories}",
          s"Should have a brew efficiency within this range: ${keg.recipe.brewhouseEfficiency}",
          s"Should refrigerate within this temp range: ${keg.recipe.refrigerateTempRange}",
        ),
        ibuBitterness = Batch.ibuBitterness(keg.recipe.hops),
        alcoholByVolume = Batch.alcoholByVolume(
          listener.ask( _.originalGravity ),
          listener.ask( _.finalGravity )
        ),
        alcoholByWeight = Batch.alcoholByWeight(
          Batch.alcoholByVolume(
            listener.ask( _.originalGravity ),
            listener.ask( _.finalGravity ) ),
            listener.ask( _.finalGravity )
          ),
        calories = Batch.calories(
          keg.recipe.packageVolume.volume,
          listener.ask( _.originalGravity ),
          listener.ask( _.finalGravity )
        )
      )
    )
  def logBrewhouseEfficiency(logBrewhouseEfficiency: LogBrewhouseEfficiency): Unit =
    listener.tell( _.onEvent:
      BrewhouseEfficiencyLogged(
        keggingTemp = logBrewhouseEfficiency.keggingTemp,
        brewhouseEfficiency = Batch.brewhouseEfficiency(
          logBrewhouseEfficiency.actualFermentableExtract,
          logBrewhouseEfficiency.recipe.potentialFermentableExtract
        )
      )
    )