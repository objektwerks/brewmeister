package objektwerks

import com.typesafe.scalalogging.LazyLogging

import os.Path

import ox.*

import upickle.default.{read => readJson, write => writeJson}

import scalafx.application.Platform

final class Store extends LazyLogging:
  os.makeDir.all( buildRecipesPath )
  os.makeDir.all( buildBatchesPath )

  private val recipesPath = buildRecipesPath
  private val batchesPath = buildBatchesPath

  logger.info("Initialized store.")

  private def buildRecipesPath: Path = os.home / ".brewmeister" / "store" / "recipes"

  private def buildBatchesPath: Path = os.home / ".brewmeister" / "store" / "batches"

  private def assertNotInFxThread: Unit = assert( !Platform.isFxApplicationThread, "Store operation called on Fx thread!" )

  def listRecipes: List[Recipe] =
    supervised:
      assertNotInFxThread
      os.list(recipesPath)
        .filter { path => path.baseName.nonEmpty }
        .map { path => readRecipe(s"${path.baseName}.json") }.toList

  def writeRecipe(recipe: Recipe): Unit =
    supervised:
      assertNotInFxThread
      val recipeAsJson = writeJson(recipe)
      os.write.over(recipesPath / recipe.fileProperty.value, recipeAsJson)
      logger.info(s"Write recipe: ${recipe.name}")

  def readRecipe(file: String): Recipe =
    supervised:
      assertNotInFxThread
      val recipeAsJson = os.read(recipesPath / file)
      logger.info(s"Read recipe: $file")
      readJson[Recipe](recipeAsJson)

  def removeRecipe(recipe: Recipe): Unit =
    supervised:
      assertNotInFxThread
      os.remove(recipesPath / recipe.fileProperty.value)
      logger.info(s"Remove recipe: ${recipe.name}")

  def listBatches: List[Batch] =
    supervised:
      assertNotInFxThread
      os.list(batchesPath)
        .filter { path => path.baseName.nonEmpty }
        .map { path => readBatch(s"${path.baseName}.json") }.toList

  def writeBatch(batch: Batch): Unit =
    supervised:
      assertNotInFxThread
      val batchAsJson = writeJson(batch)
      os.write.over(batchesPath / batch.fileProperty.value, batchAsJson)
      logger.info(s"Write batch: ${batch.nameProperty.value}")

  def readBatch(file: String): Batch =
    supervised:
      assertNotInFxThread
        val batchAsJson = os.read(batchesPath / file)
        logger.info(s"Read batch: $file")
        readJson[Batch](batchAsJson)

  def removeBatch(batch: Batch): Unit =
    supervised:
      assertNotInFxThread
      os.remove(batchesPath / batch.fileProperty.value)
      logger.info(s"Remove batch: ${batch.nameProperty.value}")