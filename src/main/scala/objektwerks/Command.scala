package objektwerks

sealed trait Command

final case class Sanitize(recipe: Recipe) extends Command

case object Sanitizing extends Command

final case class Prepare(recipe: Recipe) extends Command

case object Preparing extends Command

final case class Malt(recipe: Recipe) extends Command

case object Malting extends Command

final case class Mill(recipe: Recipe) extends Command

case object Milling extends Command

final case class Mash(recipe: Recipe) extends Command

final case class Lauter(recipe: Recipe) extends Command

case object Lautering extends Command

final case class Sparge(recipe: Recipe, actualMashExtract: Double) extends Command // removed actualMashExtract

case object Sparing extends Command

final case class Boil(recipe: Recipe) extends Command

case object Boiling extends Command

final case class Cool(recipe: Recipe) extends Command

case object Cooling extends Command

final case class Whirlpool(recipe: Recipe, originalGravity: Double) extends Command // remove originalGravity

case object Whirlpooling extends Command

final case class Ferment(recipe: Recipe, finalGravity: Double) extends Command // remove finalGravity

case object Fermented extends Command

final case class Condition(recipe: Recipe) extends Command

case object Conditioned extends Command

final case class Keg(recipe: Recipe, actualFermentableExtract: Double) extends Command

case object Kegged(actualFermentableExtract: Double) extends Command // remove actualFermentableExtract
