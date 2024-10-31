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
    brewer.handle( Prepare(recipe) )
    brewer.handle( Malt(recipe) )
    brewer.handle( Mill(recipe) )
    brewer.handle( Mash(recipe) )
    brewer.handle( LogMashingTempPh(150, 5.6) )
    brewer.handle( Lauter(recipe) )
    brewer.handle( Sparge(recipe) )
    brewer.handle( LogMashEfficiency(recipe, 4.0) )
    brewer.handle( Boil(recipe) )
    brewer.handle( Cool(recipe) )
    brewer.handle( Whirlpool(recipe) )
    brewer.handle( LogBoilingCoolingTempOriginalGravity(150, 72, 1.012) )
    brewer.handle( Ferment(recipe) )
    brewer.handle( LogFermentingTempFinalGravity(72, 1.012) )
    brewer.handle( Condition(recipe) )
    brewer.handle( LogConditioningTempSrmColor(72, 9) )

    val batch = listener.ask( _.batch )
    batch.recipe.nonEmpty shouldBe true