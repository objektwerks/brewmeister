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

  def listRecipes: IndexedSeq[Path] =
    os.list(recipesPath)

  def writeRecipe(recipe: Recipe): Unit =
    val recipeAsJson = writeJson(recipe)
    os.write.over(recipesPath / s"${recipe.name}.json", recipeAsJson)

  def readRecipe(name: String): Recipe =
    val recipeAsJson = os.read(recipesPath / s"$name.json")
    readJson[Recipe](recipeAsJson)

  def listBatches: IndexedSeq[Path] =
    os.list(batchesPath)

  def writeBatch(batch: Batch): Unit =
    val batchAsJson = writeJson(batch)
    os.write.over(batchesPath / s"${batch.recipe}.${batch.started}.json", batchAsJson)

  def readBatch(recipe: String, started: String): Batch =
    val batchAsJson = os.read(batchesPath / s"$recipe.$started.json")
    readJson[Batch](batchAsJson)