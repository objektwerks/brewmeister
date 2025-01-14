package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class BrewerTest extends AnyFunSuite with Matchers:
  test("brew"):
    val recipe = Recipe.default
    val store = Store()
    
    store.writeRecipe(recipe)
    store.readRecipe(recipe.fileProperty.value) shouldBe recipe
    store.listRecipes.length should be >= 1

    val listener = Listener()
    Brewer.brew(listener, recipe)

    val batch = listener.batch
    store.writeBatch(batch)
    store.readBatch(batch.fileProperty.value) shouldBe batch
    store.listBatches.length should be >= 1

    store.listRecipes.foreach { recipe => store.removeRecipe(recipe) }
    store.listRecipes.isEmpty shouldBe true

    store.listBatches.foreach { batch => store.removeBatch(batch) }
    store.listBatches.isEmpty shouldBe true

    store.writeRecipe(recipe)
    store.writeBatch(batch)