package objektwerks

import os.*

final class Store(storePath: Path):
  if !os.exists(storePath) then os.makeDir(storePath)

  def listRecipes: List[Recipe] = ???

  def addRecipe(recipe: Recipe): Unit = ???

  def listBatches: List[Batch] = ???

  def addBatch(batch: Batch): Unit = ???