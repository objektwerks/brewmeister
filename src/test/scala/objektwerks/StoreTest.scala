package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

final class StoreTest extends AnyFunSuite with Matchers:
  test("store"):
    val store = Store()

    val recipe = Recipe.default
    store.writeRecipe(recipe)
    store.readRecipe(recipe.created) shouldBe recipe

    val batch = Batch.default
    store.writeBatch(batch)
    store.readBatch(batch.created) shouldBe batch