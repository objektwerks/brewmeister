package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.annotation.nowarn

@nowarn
class BrewerTest extends AnyFunSuite with Matchers:
  test("brew > store"):
    val recipe = Recipe.default
    val batch = Brewer.simulate(recipe)

    val store = Store()

    store.writeRecipe(recipe)
    store.readRecipe(recipe.name) shouldBe recipe
    store.listRecipes.length should be >= 1

    store.writeBatch(batch)
    store.readBatch(batch.recipe, batch.started) shouldBe batch
    store.listBatches.length should be >= 1