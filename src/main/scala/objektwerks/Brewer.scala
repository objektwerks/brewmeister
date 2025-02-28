package objektwerks

object Brewer:
  def brew(listener: Listener,
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
    brewer.brew( LogMashingTempPh(mashingTemp, pH) )
    brewer.brew( Lauter(recipe) )
    brewer.brew( Sparge(recipe) )
    brewer.brew( LogMashEfficiency(recipe, actualMashExtract) )
    brewer.brew( Boil(recipe) )
    brewer.brew( Cool(recipe) )
    brewer.brew( Whirlpool(recipe) )
    brewer.brew( LogBoilingCoolingTempOriginalGravity(boilingTemp, coolingTemp, originalGravity) )
    brewer.brew( Ferment(recipe) )
    brewer.brew( LogFermentingTempFinalGravity(fermentingTemp, finalGravity) )
    brewer.brew( Condition(recipe) )
    brewer.brew( LogConditioningTempSrmColor(conditioningTemp, srmColor) )
    brewer.brew( Keg(recipe, keggingTemp, appearance, aroma, taste) )
    brewer.brew( LogKeggingTempBrewhouseEfficiency(recipe, actualFermentableExtract) )

final class Brewer(listener: Listener):
  def brew(command: Command): Unit =
    command match
      case _: Sanitize =>
        Sanitizer(listener).sanitize()
      case prepare: Prepare =>
        Preparer(listener).prepare( prepare )
      case _: Malt =>
        Malter(listener).malt()
      case _: Mill =>
        Miller(listener).mill()
      case mash: Mash =>
        Masher(listener).mash( mash )
      case logMashTempPh: LogMashingTempPh =>
        Masher(listener).logMashTempPh( logMashTempPh )
      case _: Lauter =>
        Lauterer(listener).lauter()
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
  def sanitize(): Unit =
    listener.onEvent(
      Sanitizing(
        List( "Sanitizing brewing components." ),
        started = listener.incrProcessDateTime(1, UoT.minutes)
      )
    )
    listener.onEvent(
      Sanitized(
        List( "Sanitized brewing components." ),
        completed = listener.incrProcessDateTime(10, UoT.minutes)
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
        ),
        started = listener.incrProcessDateTime(1, UoT.minutes)
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
        ),
        completed = listener.incrProcessDateTime(10, UoT.minutes)
      )
    )

final class Malter(listener: Listener):
  def malt(): Unit =
    listener.onEvent(
      Malting(
        List( "Malting grains." ),
        started = listener.incrProcessDateTime(1, UoT.minutes)
      )
    )
    listener.onEvent(
      Malted(
        List( "Malted grains." ),
        completed = listener.incrProcessDateTime(30, UoT.minutes)
      )
    )

final class Miller(listener: Listener):
  def mill(): Unit =
    listener.onEvent(
      Milling(
        List( "Milling grains into a grist." ),
        started = listener.incrProcessDateTime(1, UoT.minutes)
      )
    )
    listener.onEvent(
      Milled(
        List( "Milled grains into a grist." ),
        completed = listener.incrProcessDateTime(30, UoT.minutes)
      )
    )

final class Masher(listener: Listener):
  def mash(mash: Mash): Unit =
    listener.onEvent(
      Mashing(
        List(
          "Mashing grist into wort.",
          s"Mashing grist into a wort within this temp range / duration: ${mash.recipe.mashingTempRangeDuration}",
          s"Optionally added adjuncts: ${mash.recipe.adjuncts}",
          s"pH should be: ${mash.recipe.pH}",
        ),
        started = listener.incrProcessDateTime(1, UoT.minutes)
      )
    )
    listener.onEvent(
      Mashed(
        List( "Mashed wort." ),
        completed = listener.incrProcessDateTime(mash.recipe.mashingTempRangeDuration.duration, mash.recipe.mashingTempRangeDuration.unit)
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
  def lauter(): Unit =
    listener.onEvent(
      Lautering(
        List( "Lautering wort." ),
        started = listener.incrProcessDateTime(1, UoT.minutes)
      )
    )
    listener.onEvent(
      Lautered(
        List( "Lautered wort." ),
        completed = listener.incrProcessDateTime(30, UoT.minutes)
      )
    )

final class Sparger(listener: Listener):
  def sparge(sparge: Sparge): Unit =
    listener.onEvent(
      Sparging(
        List(
          "Sparging wort.",
          s"Mash efficiency should be within this range: ${sparge.recipe.mashEfficiencyRange}"
        ),
        started = listener.incrProcessDateTime(1, UoT.minutes)
      )
    )
    listener.onEvent(
      Sparged(
        List( "Sparged wort." ),
        completed = listener.incrProcessDateTime(30, UoT.minutes)
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
        List(
          "Boiling wort.",
          s"Boiling wort within this temp range / duration: ${boil.recipe.boilingTempRangeDuration}",
          s"Added hops: ${boil.recipe.hops}",
          s"Optionally added adjuncts: ${boil.recipe.adjuncts}"
        ),
        started = listener.incrProcessDateTime(1, UoT.minutes)
      )
    )
    listener.onEvent(
      Boiled(
        List( "Boiled wort." ),
        completed = listener.incrProcessDateTime(boil.recipe.boilingTempRangeDuration.duration, boil.recipe.boilingTempRangeDuration.unit)
      )
    )

final class Cooler(listener: Listener):
  def cool(cool: Cool): Unit =
    listener.onEvent(
      Cooling(
        List(
          "Cooling wort.",
          s"Cooling the wort within this temp range: ${cool.recipe.coolingTempRange}"
        ),
        started = listener.incrProcessDateTime(1, UoT.minutes)
      )
    )
    listener.onEvent(
      Cooled(
        List( "Cooled wort." ),
        completed = listener.incrProcessDateTime(120, UoT.minutes)
      )
    )

final class Whirlpooler(listener: Listener):
  def whirlpool(whirlpool: Whirlpool): Unit =
    listener.onEvent(
      Whirlpooling(
        List(
          "Whirlpooling wort.",
          s"Optionally added hops: ${whirlpool.recipe.hops}",
          s"Should have an orginal gravity within this range: ${whirlpool.recipe.originalGravityRange}"
        ),
        started = listener.incrProcessDateTime(1, UoT.minutes)
      )
    )
    listener.onEvent(
      Whirlpooled(
        List( "Whirlpooled wort." ),
        completed = listener.incrProcessDateTime(30, UoT.minutes)
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
        List(
          "Fermenting wort.",
          s"Fermenting within this temp range / duration: ${ferment.recipe.fermentingTempRangeDuration}",
          s"Potential fermentable extract: ${ferment.recipe.potentialFermentableExtract}",
          s"Should have a final gravity within this range: ${ferment.recipe.finalGravityRange}"
        ),
        started = listener.incrProcessDateTime(1, UoT.minutes)
      )
    )
    listener.onEvent(
      Fermented(
        List( "Fermented wort." ),
        completed = listener.incrProcessDateTime(ferment.recipe.fermentingTempRangeDuration.duration, ferment.recipe.fermentingTempRangeDuration.unit)
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
        List(
          "Conditioning wort.",
          s"Conditioning within this temp range / duration: ${condition.recipe.conditioningTempRangeDuration}",
          s"Optionally added adjuncts: ${condition.recipe.adjuncts}",
          s"Optionally added hops: ${condition.recipe.hops}",
          s"Should have an SRM color within this range: ${condition.recipe.srmColorRange}"
        ),
        started = listener.incrProcessDateTime(1, UoT.minutes)
      )
    )
    listener.onEvent(
      Conditioned(
        List( "Conditioned wort." ),
        completed = listener.incrProcessDateTime(condition.recipe.conditioningTempRangeDuration.duration, condition.recipe.conditioningTempRangeDuration.unit)
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
    val alcoholByWeight = Batch.alcoholByWeight(alcoholByVolume, listener.finalGravity)
    val calories = Batch.calories(
      keg.recipe.volume,
      listener.originalGravity,
      listener.finalGravity
    )
    listener.onEvent(
      Kegging(
        List(
          "Kegging wort.",
          s"Conditioning within this temp range / duration: ${keg.recipe.keggingTempRangeDuration}",
          s"IBU hop bitterness should be within this range: ${keg.recipe.ibuBitternessRange}",
          s"Alcohol by volume should be within this range: ${keg.recipe.alcoholByVolumeRange}",
          s"Alcohol by weight should be within this range: ${keg.recipe.alcoholByWeightRange}",
          s"Calories should be within this range: ${keg.recipe.calorieRange}",
          s"Should have a brew efficiency within this range: ${keg.recipe.brewhouseEfficiencyRange}"
        ),
        started = listener.incrProcessDateTime(1, UoT.minutes)
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
          s"IBU hop bitterness: $ibuBitterness",
          s"ABV: $alcoholByVolume",
          s"ABW: $alcoholByWeight",
          s"Calories: $calories for volume of: ${keg.recipe.volume} ${keg.recipe.volume.unit}",
          s"Kegging temp: ${keg.keggingTemp}",
          s"Appearance: ${keg.appearance}",
          s"Aroma: ${keg.aroma}",
          s"Taste: ${keg.taste}",
          "Kegged wort."
        ),
        completed = listener.incrProcessDateTime(keg.recipe.keggingTempRangeDuration.duration, keg.recipe.keggingTempRangeDuration.unit)
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