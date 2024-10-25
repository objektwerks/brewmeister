package objektwerks

import os.*

final class Store(storePathRecipes: Path,
                  storePathBatches: Path):
  validatePaths

  private def validatePaths: Unit =
    if !os.exists(storePathRecipes) then os.makeDir(storePathRecipes)
    if !os.exists(storePathBatches) then os.makeDir(storePathBatches)

  def listRecipes: IndexedSeq[Path] = os.list(storePathRecipes)

  def readRecipe(path: Path): Recipe = ???

  def addRecipe(recipe: Recipe): Unit = ???

  def listBatches: IndexedSeq[Path] = os.list(storePathBatches)

  def addBatch(batch: Batch): Unit = ???