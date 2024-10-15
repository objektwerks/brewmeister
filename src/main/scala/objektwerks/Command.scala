package objektwerks

sealed trait Command

final case class Sanitize(recipe: Recipe) extends Command

case object Sanitized extends Command

final case class Prepare(recipe: Recipe) extends Command

case object Prepared extends Command

final case class Malt(recipe: Recipe) extends Command

case object Malted extends Command

final case class Mill(recipe: Recipe) extends Command

case object Milled extends Command

final case class Mash(recipe: Recipe, pH: Double) extends Command // remove pH

final case class Mashed(pH: Double) extends Command

case object Mashed extends Command

final case class Lauter(recipe: Recipe) extends Command

case object Lautered extends Command

final case class Sparge(recipe: Recipe, actualMashExtract: Double) extends Command // removed actualMashExtract

final case class Spared(actualMashExtract: Double) extends Command

final case class Boil(recipe: Recipe) extends Command

case object Boiled extends Command

final case class Cool(recipe: Recipe) extends Command

case object Cooled extends Command

final case class Whirlpool(recipe: Recipe, originalGravity: Double) extends Command // remove originalGravity

final case class Whirlpooled(originalGravity: Double) extends Command

final case class Ferment(recipe: Recipe, finalGravity: Double) extends Command

final case class Fermented(finalGravity: Double) extends Command

final case class Condition(recipe: Recipe) extends Command

case object Conditioned extends Command

final case class Keg(recipe: Recipe, actualFermentableExtract: Double) extends Command