package objektwerks

import os.Path

import upickle.default.{read => readJson, write => writeJson}

final class Store():
  os.makeDir.all( os.home / ".brewmeister" / "store" / "recipes" )
  os.makeDir.all( os.home / ".brewmeister" / "store" / "batches" )

  val recipesPath = os.home / ".brewmeister" / "store" / "recipes"
  val batchesPath = os.home / ".brewmeister" / "store" / "batches"

  def listRecipes: IndexedSeq[Path] =
    os.list(recipesPath)

  def readRecipe(name: String): Recipe =
    val recipeAsJson = os.read(recipesPath / s"$name.json")
    readJson[Recipe](recipeAsJson)

  def writeRecipe(recipe: Recipe): Unit =
    val recipeAsJson = writeJson(recipe)
    os.write.over(recipesPath / s"${recipe.created}.json", recipeAsJson)

  def listBatches: IndexedSeq[Path] =
    os.list(batchesPath)

  def readBatch(name: String): Batch =
    val batchAsJson = os.read(batchesPath / s"$name.json")
    readJson[Batch](batchAsJson)

  def writeBatch(batch: Batch): Unit =
    val batchAsJson = writeJson(batch)
    os.write.over(batchesPath / s"${batch.created}.json", batchAsJson)