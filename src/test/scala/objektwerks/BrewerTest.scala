package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class BrewerTest extends AnyFunSuite with Matchers:
  test("brew"):
    val listener = Listener()
    val recipe = Recipe.default
    val brewer = Brewer(listener)
    val batch = brewer.simulate(recipe)
    val store = Store()
    store.writeRecipe(recipe)
    store.writeBatch(batch)
    batch.recipe.nonEmpty shouldBe true