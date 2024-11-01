package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class BrewerTest extends AnyFunSuite with Matchers:
  test("brew"):
    val batch = Brewer.simulate(Recipe.default)
    Store().writeBatch(batch)
    batch.recipe.nonEmpty shouldBe true