package objektwerks

sealed trait Command

final case class Sanitize(recipe: Recipe) extends Command

final case class Prepare(recipe: Recipe) extends Command

final case class Malt(recipe: Recipe) extends Command

final case class Mill(recipe: Recipe) extends Command

final case class Mash(recipe: Recipe, pH: Double) extends Command

final case class Lauter(recipe: Recipe) extends Command

final case class Sparge(recipe: Recipe, actualMashExtract: Double) extends Command // removed actualMashExtract

final case class Boil(recipe: Recipe) extends Command

final case class Cool(recipe: Recipe) extends Command

final case class Whirlpool(recipe: Recipe, originalGravity: Double) extends Command // remove originalGravity

final case class Ferment(recipe: Recipe, finalGravity: Double) extends Command // remove finalGravity

final case class Condition(recipe: Recipe) extends Command

final case class Keg(recipe: Recipe, actualFermentableExtract: Double) extends Command