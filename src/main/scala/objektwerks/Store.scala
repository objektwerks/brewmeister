package objektwerks

import os.Path

import upickle.default.{read}

final class Store(storePathRecipes: Path,
                  storePathBatches: Path):
  validatePaths

  private def validatePaths: Unit =
    if !os.exists(storePathRecipes) then os.makeDir(storePathRecipes)
    if !os.exists(storePathBatches) then os.makeDir(storePathBatches)

  def listRecipes: IndexedSeq[Path] = os.list(storePathRecipes)

  def readRecipe(path: Path): Recipe =
    val recipeAsJson = os.read(path)
    read[Recipe](recipeAsJson)

  def writeRecipe(recipe: Recipe): Unit = ???

  def listBatches: IndexedSeq[Path] = os.list(storePathBatches)

  def readBatch(path: Path): Batch = ???

  def writeBatch(batch: Batch): Unit = ???