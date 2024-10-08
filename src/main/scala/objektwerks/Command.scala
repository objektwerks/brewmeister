package objektwerks

sealed trait Command

final case class Sanitize(batchId: Int) extends Command

final case class Prepare(batchId: Int, recipe: Recipe) extends Command

final case class Malt(batchId: Int, recipe: Recipe) extends Command

final case class Mill(batchId: Int, recipe: Recipe) extends Command

final case class Mash(batchId: Int, recipe: Recipe) extends Command

final case class Lauter(batchId: Int, recipe: Recipe) extends Command

final case class Sparge(batchId: Int, recipe: Recipe) extends Command

final case class Boil(recipe: Recipe) extends Command

final case class Cool(recipe: Recipe) extends Command

final case class Whirlpool(recipe: Recipe) extends Command

final case class Ferment(recipe: Recipe) extends Command

final case class Condition(recipe: Recipe) extends Command

final case class Package(recipe: Recipe) extends Command