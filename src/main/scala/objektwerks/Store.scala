package objektwerks

import os.*

final class Store(storePathRecipes: Path,
                  storePathBatches: Path):
  validatePaths

  private def validatePaths: Unit =
    if !os.exists(storePathRecipes) then os.makeDir(storePathRecipes)
    if !os.exists(storePathBatches) then os.makeDir(storePathBatches)

  def listRecipes: List[Recipe] = ???

  def addRecipe(recipe: Recipe): Unit = ???

  def listBatches: List[Batch] = ???

  def addBatch(batch: Batch): Unit = ???