package objektwerks

object Brewer:
  def simulate(listener: Listener,
               recipe: Recipe,
               mashingTemp: Int = 150,
               pH: Double = 5.6,
               actualMashExtract: Double = 4.5,
               boilingTemp: Int = 150,
               coolingTemp: Int = 72,
               originalGravity: Double = 1.060,
               fermentingTemp: Int = 72,
               finalGravity: Double = 1.012,
               conditioningTemp: Int = 72,
               srmColor: Int = 9,
               keggingTemp: Int = 72,
               appearance: Int = 3,
               aroma: Int = 3,
               taste: Int = 3,
               actualFermentableExtract: Double = 4.5): Unit =
    val brewer = Brewer(listener)
    brewer.brew( Sanitize(recipe) )
    brewer.brew( Prepare(recipe) )
    brewer.brew( Malt(recipe) )
    brewer.brew( Mill(recipe) )
    brewer.brew( Mash(recipe) )
    brewer.brew( LogMashingTempPh(mashingTemp = 150, pH = 5.6) )
    brewer.brew( Lauter(recipe) )
    brewer.brew( Sparge(recipe) )
    brewer.brew( LogMashEfficiency(recipe, actualMashExtract = 4.0) )
    brewer.brew( Boil(recipe) )
    brewer.brew( Cool(recipe) )
    brewer.brew( Whirlpool(recipe) )
    brewer.brew( LogBoilingCoolingTempOriginalGravity(boilingTemp = 150, coolingTemp = 72, originalGravity = 1.060) )
    brewer.brew( Ferment(recipe) )
    brewer.brew( LogFermentingTempFinalGravity(fermentingTemp = 72, finalGravity = 1.012) )
    brewer.brew( Condition(recipe) )
    brewer.brew( LogConditioningTempSrmColor(conditioningTemp = 72, srmColor = 9) )
    brewer.brew( Keg(recipe, keggingTemp, appearance, aroma, taste) )
    brewer.brew( LogKeggingTempBrewhouseEfficiency(recipe, actualFermentableExtract = 4.0) )

final class Brewer(listener: Listener):
  def brew(command: Command): Unit =
    command match
      case sanitize: Sanitize =>
        Sanitizer(listener).sanitize( sanitize )
      case prepare: Prepare =>
        Preparer(listener).prepare( prepare )
      case malt: Malt =>
        Malter(listener).malt( malt )
      case mill: Mill =>
        Miller(listener).mill( mill )
      case mash: Mash =>
        Masher(listener).mash( mash )
      case logMashTempPh: LogMashingTempPh =>
        Masher(listener).logMashTempPh( logMashTempPh )
      case lauter: Lauter =>
        Lauterer(listener).lauter( lauter )
      case sparge: Sparge =>
        Sparger(listener).sparge( sparge )
      case logMashEfficiency: LogMashEfficiency =>
        Sparger(listener).logMashEfficiency( logMashEfficiency )
      case boil: Boil =>
        Boiler(listener).boil( boil )
      case cool: Cool =>
        Cooler(listener).cool( cool )
      case whirlpool: Whirlpool =>
        Whirlpooler(listener).whirlpool( whirlpool )
      case logBoilingCoolingTempOriginalGravity: LogBoilingCoolingTempOriginalGravity =>
        Whirlpooler(listener).logBoilingCoolingTempOriginalGravity( logBoilingCoolingTempOriginalGravity )
      case ferment: Ferment =>
        Fermenter(listener).ferment( ferment )
      case logFermentingTempFinalGravity: LogFermentingTempFinalGravity =>
        Fermenter(listener).logFermentingTempFinalGravity( logFermentingTempFinalGravity )
      case condition: Condition =>
        Conditioner(listener).condition( condition )
      case logConditioningTempSrmColor: LogConditioningTempSrmColor =>
        Conditioner(listener).logConditioningSrmColor( logConditioningTempSrmColor )
      case keg: Keg =>
        Kegger(listener).keg( keg )
      case logKeggingTempBrewhouseEfficiency: LogKeggingTempBrewhouseEfficiency =>
        Kegger(listener).logKeggingTempBrewhouseEfficiency( logKeggingTempBrewhouseEfficiency )

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
        List(
          "Preparing recipe ingredients:",
          s"Grains: ${prepare.recipe.grains}",
          s"Hops: ${prepare.recipe.hops}",
          s"Adjuncts: ${prepare.recipe.adjuncts}",
          s"Yeasts: ${prepare.recipe.yeasts}"
        )
      )
    )
    listener.onEvent(
      Prepared(
        prepare.recipe.name,
        prepare.recipe.style,
        prepare.recipe.volume,
        List(
          "Prepared recipe ingredients for:",
          s"Recipe: ${prepare.recipe.name}",
          s"Style: ${prepare.recipe.style}",
          s"Water: ${prepare.recipe.water}",
          s"Batch: ${prepare.recipe.volume}"
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
        pH = logMashTempPh.pH,
        List(
          s"Mash temp: ${logMashTempPh.mashingTemp}",
          s"Mash pH: ${logMashTempPh.pH}"
        )
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
          s"Mash efficiency should be within this range: ${sparge.recipe.mashEfficiency}",
          "Sparged wort."
        )
      )
    )
  def logMashEfficiency(logMashEfficiency: LogMashEfficiency): Unit =
    val mashEfficiency = Batch.mashEfficiency(logMashEfficiency.actualMashExtract, logMashEfficiency.recipe.potentialMashExtract)
    listener.onEvent(
      MashEfficiencyLogged(
        mashEfficiency = mashEfficiency,
        log = List(
          s"Actual mash extract: ${logMashEfficiency.actualMashExtract}",
          s"Potential mash extract: ${logMashEfficiency.recipe.potentialMashExtract}",
          s"Mash efficiency: $mashEfficiency"
        )
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
        originalGravity = logBoilingCoolingTempOriginalGravity.originalGravity,
        log = List(
          s"Boiling temp: ${logBoilingCoolingTempOriginalGravity.boilingTemp}",
          s"Cooling temp: ${logBoilingCoolingTempOriginalGravity.coolingTemp}",
          s"Original gravity: ${logBoilingCoolingTempOriginalGravity.originalGravity}"
        )
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
          s"Potential fermentable extract: ${ferment.recipe.potentialFermentableExtract}",
          s"Should have a final gravity within this range: ${ferment.recipe.finalGravity}",
          "Fermented wort."
        )
      )
    )
  def logFermentingTempFinalGravity(logFermentingTempFinalGravity: LogFermentingTempFinalGravity): Unit =
    listener.onEvent(
      FermentingTempFinalGravityLogged(
        fermentingTemp = logFermentingTempFinalGravity.fermentingTemp,
        finalGravity = logFermentingTempFinalGravity.finalGravity,
        log = List(
          s"Fermenting temp: ${logFermentingTempFinalGravity.fermentingTemp}",
          s"Final gravity: ${logFermentingTempFinalGravity.finalGravity}"
        )
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
        srmColor = logConditioningTempSrmColor.srmColor,
        log = List(
          s"Conditioning temp: ${logConditioningTempSrmColor.conditioningTemp}",
          s"SRM color: ${logConditioningTempSrmColor.srmColor}"
        )
      )
    )

final class Kegger(listener: Listener):
  def keg(keg: Keg): Unit =
    val ibuBitterness = Batch.ibuBitterness(keg.recipe.hops)
    val alcoholByVolume = Batch.alcoholByVolume(listener.originalGravity, listener.finalGravity)
    val alcoholByWeight = Batch.alcoholByWeight(
      Batch.alcoholByVolume(listener.originalGravity, listener.finalGravity),
      listener.finalGravity
    )
    val calories = Batch.calories(
      keg.recipe.volume.value,
      listener.originalGravity,
      listener.finalGravity
    )
    listener.onEvent(
      Kegging(
        List( "Kegging wort." )
      )
    )
    listener.onEvent(
      Kegged(
        ibuBitterness = ibuBitterness,
        alcoholByVolume = alcoholByVolume,
        alcoholByWeight = alcoholByWeight,
        calories = calories,
        keggingTemp = keg.keggingTemp,
        appearance = keg.appearance,
        aroma = keg.aroma,
        taste = keg.taste,
        List(
          s"Conditioned within this temp range / duration: ${keg.recipe.keggingTempDuration}",
          s"IBU bitterness should be within this range: ${keg.recipe.ibuBitterness}",
          s"Alcohol by volume should be within this range: ${keg.recipe.alcoholByVolume}",
          s"Alcohol by weight should be within this range: ${keg.recipe.alcoholByWeight}",
          s"Calories should be within this range: ${keg.recipe.calories}",
          s"Should have a brew efficiency within this range: ${keg.recipe.brewhouseEfficiency}",
          s"IBU hop bitterness: $ibuBitterness",
          s"ABV: $alcoholByVolume",
          s"ABW: $alcoholByWeight",
          s"Calories: $calories",
          s"Kegging temp: ${keg.keggingTemp}",
          s"Appearance: ${keg.appearance}",
          s"Aroma: ${keg.aroma}",
          s"Taste: ${keg.taste}",
          "Kegged wort."
        ),
      )
    )
  def logKeggingTempBrewhouseEfficiency(logKeggingTempBrewhouseEfficiency: LogKeggingTempBrewhouseEfficiency): Unit =
    val brewhouseEfficiency = Batch.brewhouseEfficiency(
      logKeggingTempBrewhouseEfficiency.actualFermentableExtract,
      logKeggingTempBrewhouseEfficiency.recipe.potentialFermentableExtract
    )
    listener.onEvent(
      KeggingTempBrewhouseEfficiencyLogged(
        brewhouseEfficiency = brewhouseEfficiency,
        log = List(s"Brewhouse efficiency: $brewhouseEfficiency")
      )
    )
