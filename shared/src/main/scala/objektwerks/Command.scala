package objektwerks

trait Command

case object Sanitize extends Command

final case class Prepare(recipe: Recipe)

// Malt, Mill, Mash, Lauter, Sparge, Boil, Cool, Whirlpool, Ferment, Condition, Package