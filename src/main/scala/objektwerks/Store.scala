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
    os.list(recipesPath).map { path => readRecipe(path.baseName) }.toList

  def writeRecipe(recipe: Recipe): Unit =
    val recipeAsJson = writeJson(recipe)
    os.write.over(recipesPath / s"${recipe.name}.json", recipeAsJson)

  def readRecipe(name: String): Recipe =
    val recipeAsJson = os.read(recipesPath / s"$name.json")
    readJson[Recipe](recipeAsJson)

  def listBatches: List[Batch] =
    os.list(batchesPath).map { path => readBatch(s"${path.baseName}.json") }.toList

  def writeBatch(batch: Batch): Unit =
    val batchAsJson = writeJson(batch)
    os.write.over(batchesPath / batch.fileProperty.value, batchAsJson)

  def readBatch(file: String): Batch =
    val batchAsJson = os.read(batchesPath / file)
    readJson[Batch](batchAsJson)