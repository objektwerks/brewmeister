package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import upickle.default.*

class BrewerTest extends AnyFunSuite with Matchers:
  test("recipe"):
    val recipe = Recipe.default
    val recipeAsJson = write(recipe)
    recipe shouldBe read[Recipe](recipeAsJson)

  test("batch"):
    val batch = Batch.default
    val batchAsJson = write(batch)
    batch shouldBe read[Batch](batchAsJson)

  test("brew"):
    val batch = Brewer.simulate(Recipe.default)
    Store().writeBatch(batch)
    batch.recipe.nonEmpty shouldBe true