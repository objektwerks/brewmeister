package objektwerks

trait Command

case object Sanitize extends Command

final case class Prepare(recipe: Recipe) extends Command

final case class Malt(recipe: Recipe) extends Command

final case class Mill(recipe: Recipe) extends Command

final case class Mash(recipe: Recipe) extends Command

final case class Lauter(recipe: Recipe) extends Command

//, Sparge, Boil, Cool, Whirlpool, Ferment, Condition, Package