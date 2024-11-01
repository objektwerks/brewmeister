package objektwerks

sealed trait Command

final case class Sanitize(recipe: Recipe) extends Command

final case class Prepare(recipe: Recipe) extends Command

final case class Malt(recipe: Recipe) extends Command

final case class Mill(recipe: Recipe) extends Command

final case class Mash(recipe: Recipe) extends Command

final case class LogMashingTempPh(mashingTemp: Int,
                                  pH: Double) extends Command

final case class Lauter(recipe: Recipe) extends Command

final case class Sparge(recipe: Recipe) extends Command

final case class LogMashEfficiency(recipe: Recipe,
                                   actualMashExtract: Double) extends Command

final case class Boil(recipe: Recipe) extends Command

final case class Cool(recipe: Recipe) extends Command

final case class Whirlpool(recipe: Recipe) extends Command

final case class LogBoilingCoolingTempOriginalGravity(boilingTemp: Int,
                                                      coolingTemp: Int,
                                                      originalGravity: Double) extends Command

final case class Ferment(recipe: Recipe) extends Command

final case class LogFermentingTempFinalGravity(fermentingTemp: Int,
                                               finalGravity: Double) extends Command

final case class Condition(recipe: Recipe) extends Command

final case class LogConditioningTempSrmColor(conditioningTemp: Int,
                                             srmColor: Int) extends Command

final case class Keg(recipe: Recipe,
                     keggingTemp: Int,
                     appearance: Int,
                     aroma: Int,
                     taste: Int) extends Command

final case class LogKeggingTempBrewhouseEfficiency(recipe: Recipe,
                                                   actualFermentableExtract: Double) extends Command