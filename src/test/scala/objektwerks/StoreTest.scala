package objektwerks

import org.scalatest.funsuite.AnyFunSuite

import scala.annotation.nowarn

@nowarn
final class StoreTest extends AnyFunSuite:
  test("store"):
    val store = Store()

    val recipe = Recipe.default
    store.writeRecipe(recipe)
    assert( store.readRecipe(recipe.created) == recipe )
    assert( store.listRecipes.length >= 1 )

    val batch = Batch.default
    store.writeBatch(batch)
    assert( store.readBatch(batch.created) == batch )
    assert( store.listBatches.length >= 1 )