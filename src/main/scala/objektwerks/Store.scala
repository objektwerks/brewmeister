package objektwerks

import os.Path

import upickle.default.{read, write}

final class Store(recipesPath: Path,
                  batchesPath: Path):
  validatePaths

  private def validatePaths: Unit =
    if !os.exists(recipesPath) then os.makeDir(recipesPath)
    if !os.exists(batchesPath) then os.makeDir(batchesPath)

  def listRecipes: IndexedSeq[Path] =
    os.list(recipesPath)

  def readRecipe(path: Path): Recipe =
    val recipeAsJson = os.read(path)
    read[Recipe](recipeAsJson)

  def writeRecipe(path: Path, recipe: Recipe): Unit =
    val recipeAsJson = write(recipe)
    os.write.over(path, recipeAsJson)

  def listBatches: IndexedSeq[Path] =
    os.list(batchesPath)

  def readBatch(path: Path): Batch =
    val batchAsJson = os.read(path)
    read[Batch](batchAsJson)

  def writeBatch(path: Path, batch: Batch): Unit =
    val batchAsJson = write(batch)
    os.write.over(path, batchAsJson)