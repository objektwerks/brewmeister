package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import ox.channels.Actor
import ox.supervised

class BrewerTest extends AnyFunSuite with Matchers:
  test("brew"):
    val listener = supervised( Actor.create( Listener() ) )
    val brewer = Brewer(listener)
    val recipe = Recipe.default

    brewer.handle( Sanitize(recipe) )

    val batch = listener.ask( _.batch )
    batch.recipe.nonEmpty shouldBe true