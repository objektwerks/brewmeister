package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.annotation.nowarn

import upickle.default.*

@nowarn
class BrewerTest extends AnyFunSuite with Matchers:
  test("recipe"):
    val recipe = Recipe.default
    val recipeAsJson = write(recipe)
    recipe shouldBe read[Recipe](recipeAsJson)

  test("batch"):
    val batch = Batch.default
    val batchAsJson = write(batch)
    batch shouldBe read[Batch](batchAsJson)

  test("store"):
    val recipe = Recipe.default
    val batch = Brewer.simulate(recipe)

    val store = Store()

    store.writeRecipe(recipe)
    assert( store.readRecipe(recipe.name) == recipe )
    assert( store.listRecipes.length >= 1 )

    store.writeBatch(batch)
    assert( store.readBatch(batch.recipe, batch.started) == batch )
    assert( store.listBatches.length >= 1 )

  test("brew"):
    val batch = Brewer.simulate(Recipe.default)
    Store().writeBatch(batch)
    batch.recipe.nonEmpty shouldBe true