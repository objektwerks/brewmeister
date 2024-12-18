package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class BrewerTest extends AnyFunSuite with Matchers:
  test("brew"):
    val recipe = Recipe.default
    val store = Store()
    
    store.writeRecipe(recipe)
    store.readRecipe(recipe.name) shouldBe recipe
    store.listRecipes.length should be >= 1

    val listener = Listener()
    Brewer.brew(listener, recipe)

    val batch = listener.batch
    store.writeBatch(batch)
    store.readBatch(batch.recipe, batch.started) shouldBe batch
    store.listBatches.length should be >= 1