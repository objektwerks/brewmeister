package objektwerks

trait Command

case object Sanitize extends Command

final case class Prepare(recipe: Recipe)

final case class Malt(recipe: Recipe)

final case class Mill(recipe: Recipe)

//Mash, Lauter, Sparge, Boil, Cool, Whirlpool, Ferment, Condition, Package