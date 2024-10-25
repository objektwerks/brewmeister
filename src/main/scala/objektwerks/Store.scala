package objektwerks

final class Store(storePath: String):
  println(storePath)

  def listRecipes: List[Recipe] = ???

  def addRecipe(recipe: Recipe): Unit = ???

  def listBatches: List[Batch] = ???

  def addBatch(batch: Batch): Unit = ???