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
    os.write.over(batchesPath / s"${batch.recipe}.${batch.created}.json", batchAsJson)

  def readBatch(recipe: String, created: String): Batch =
    val batchAsJson = os.read(batchesPath / s"$recipe.$created.json")
    readJson[Batch](batchAsJson)