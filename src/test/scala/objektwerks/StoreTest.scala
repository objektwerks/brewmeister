package objektwerks

import com.typesafe.config.ConfigFactory

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import os.Path

final class StoreTest extends AnyFunSuite with Matchers:
  val conf = ConfigFactory.load("/test.conf")
  val pathRecipes = Path( conf.getString("store.path.recipes") )
  val pathBatches = Path( conf.getString("store.path.batches") )

  test("store"):
    val store = Store(pathRecipes, pathBatches)

    val recipe = Recipe.default
    store.writeRecipe(pathRecipes / s"${recipe.created}.json", recipe)
    
    val batch = Batch.default
    store.writeBatch(pathBatches / s"${batch.created}.json", batch)