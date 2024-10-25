package objektwerks

import os.Path

import upickle.default.{read => readJson, write => writeJson}

final class Store(recipesPath: Path,
                  batchesPath: Path):
  validatePaths

  private def validatePaths: Unit =
    os.makeDir.all(recipesPath)
    os.makeDir.all(batchesPath)

  def listRecipes: IndexedSeq[Path] =
    os.list(recipesPath)

  def readRecipe(path: Path): Recipe =
    val recipeAsJson = os.read(path)
    readJson[Recipe](recipeAsJson)

  def writeRecipe(path: Path, recipe: Recipe): Unit =
    val recipeAsJson = writeJson(recipe)
    os.write.over(path, recipeAsJson)

  def listBatches: IndexedSeq[Path] =
    os.list(batchesPath)

  def readBatch(path: Path): Batch =
    val batchAsJson = os.read(path)
    readJson[Batch](batchAsJson)

  def writeBatch(path: Path, batch: Batch): Unit =
    val batchAsJson = writeJson(batch)
    os.write.over(path, batchAsJson)