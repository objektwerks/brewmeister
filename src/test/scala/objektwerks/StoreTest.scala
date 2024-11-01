package objektwerks

import org.scalatest.funsuite.AnyFunSuite

import scala.annotation.nowarn

@nowarn
final class StoreTest extends AnyFunSuite:
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