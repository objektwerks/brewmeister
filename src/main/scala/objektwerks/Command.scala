package objektwerks

sealed trait Command

final case class Sanitize(batchId: Int) extends Command

final case class Prepare(batchId: Int, batch: Batch) extends Command

final case class Malt(batchId: Int, batch: Batch) extends Command

final case class Mill(batchId: Int, batch: Batch) extends Command

final case class Mash(batchId: Int, batch: Batch) extends Command

final case class Lauter(batchId: Int, batch: Batch) extends Command

final case class Sparge(batchId: Int, batch: Batch) extends Command

final case class Boil(batchId: Int, batch: Batch) extends Command

final case class Cool(batchId: Int, batch: Batch) extends Command

final case class Whirlpool(batchId: Int, batch: Batch) extends Command

final case class Ferment(batchId: Int, batch: Batch) extends Command

final case class Condition(batchId: Int, batch: Batch) extends Command

final case class Package(batchId: Int, batch: Batch) extends Command