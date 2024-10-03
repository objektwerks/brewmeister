package objektwerks

sealed trait Command

final case class Brew(recipe: Recipe) extends Command

final case class Sanitize() extends Command

final case class Prepare(recipe: Recipe) extends Command

final case class Malt(recipe: Recipe) extends Command

final case class Mill(recipe: Recipe) extends Command

final case class Mash(recipe: Recipe) extends Command

final case class Lauter() extends Command

final case class Sparge() extends Command

final case class Boil(recipe: Recipe) extends Command

final case class Cool() extends Command

final case class Whirlpool(recipe: Recipe) extends Command

final case class Ferment(recipe: Recipe) extends Command

final case class Condition(recipe: Recipe) extends Command

final case class Package() extends Command