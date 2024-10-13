package objektwerks

sealed trait Command

final case class Sanitize(batch: Batch) extends Command

final case class Prepare(batch: Batch) extends Command

final case class Malt(batch: Batch) extends Command

final case class Mill(batch: Batch) extends Command

final case class Mash(batch: Batch, pH: Double) extends Command

final case class Lauter(batch: Batch) extends Command

final case class Sparge(batch: Batch, actualMashExtract: Double) extends Command

final case class Boil(batch: Batch) extends Command

final case class Cool(batch: Batch) extends Command

final case class Whirlpool(batch: Batch, originalGravity: Double) extends Command

final case class Ferment(batch: Batch, finalGravity: Double) extends Command

final case class Condition(batch: Batch) extends Command

final case class Package(batch: Batch, actualFermentableExtract: Double) extends Command