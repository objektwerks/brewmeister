package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import upickle.default.*

final class RecipeTest extends AnyFunSuite with Matchers:
  test("recipe"):
    val recipe = Recipe.default
    val recipeAsJson = write(recipe)
    println(recipeAsJson)
    recipe shouldBe read[Recipe](recipeAsJson)