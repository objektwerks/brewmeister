package objektwerks

import os.Path

import upickle.default.{read => readJson, write => writeJson}

final class Store:
  os.makeDir.all( buildRecipesPath )
  os.makeDir.all( buildBatchesPath )

  private val recipesPath = buildRecipesPath
  private val batchesPath = buildBatchesPath

  private def buildRecipesPath: Path = os.home / ".brewmeister" / "store" / "recipes"

  private def buildBatchesPath: Path = os.home / ".brewmeister" / "store" / "batches"

  def listRecipes: List[Recipe] =
    os.list(recipesPath).map { path => readRecipe(s"${path.baseName}.json") }.toList

  def writeRecipe(recipe: Recipe): Unit =
    val recipeAsJson = writeJson(recipe)
    os.write.over(recipesPath / recipe.fileProperty.value, recipeAsJson)

  def readRecipe(file: String): Recipe =
    val recipeAsJson = os.read(recipesPath / file)
    readJson[Recipe](recipeAsJson)

  def removeRecipe(recipe: Recipe): Unit =
    os.remove(recipesPath / recipe.fileProperty.value)

  def listBatches: List[Batch] =
    os.list(batchesPath).map { path => readBatch(s"${path.baseName}.json") }.toList

  def writeBatch(batch: Batch): Unit =
    val batchAsJson = writeJson(batch)
    os.write.over(batchesPath / batch.fileProperty.value, batchAsJson)

  def readBatch(file: String): Batch =
    val batchAsJson = os.read(batchesPath / file)
    readJson[Batch](batchAsJson)

  def removeBatch(batch: Batch): Unit =
    os.remove(batchesPath / batch.fileProperty.value)