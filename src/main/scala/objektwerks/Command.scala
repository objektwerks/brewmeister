package objektwerks

sealed trait Command

final case class Sanitize(batchId: Int) extends Command

final case class Prepare(batchId: Int, recipe: Recipe) extends Command

final case class Malt(batchId: Int, recipe: Recipe) extends Command

final case class Mill(batchId: Int, recipe: Recipe) extends Command

final case class Mash(batchId: Int, recipe: Recipe) extends Command

final case class Lauter(batchId: Int, recipe: Recipe) extends Command

final case class Sparge(batchId: Int, recipe: Recipe) extends Command

final case class Boil(batchId: Int, recipe: Recipe) extends Command

final case class Cool(batchId: Int, recipe: Recipe) extends Command

final case class Whirlpool(batchId: Int, recipe: Recipe) extends Command

final case class Ferment(batchId: Int, recipe: Recipe) extends Command

final case class Condition(batchId: Int, recipe: Recipe) extends Command

final case class Package(recipe: Recipe) extends Command