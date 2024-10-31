package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class BrewerTest extends AnyFunSuite with Matchers:
  test("brew"):
    val listener = Listener()
    val brewer = Brewer(listener)
    val recipe = Recipe.default

    brewer.brew( Sanitize(recipe) )
    brewer.brew( Prepare(recipe) )
    brewer.brew( Malt(recipe) )
    brewer.brew( Mill(recipe) )
    brewer.brew( Mash(recipe) )
    brewer.brew( LogMashingTempPh(150, 5.6) )
    brewer.brew( Lauter(recipe) )
    brewer.brew( Sparge(recipe) )
    brewer.brew( LogMashEfficiency(recipe, 4.0) )
    brewer.brew( Boil(recipe) )
    brewer.brew( Cool(recipe) )
    brewer.brew( Whirlpool(recipe) )
    brewer.brew( LogBoilingCoolingTempOriginalGravity(150, 72, 1.060) )
    brewer.brew( Ferment(recipe) )
    brewer.brew( LogFermentingTempFinalGravity(72, 1.012) )
    brewer.brew( Condition(recipe) )
    brewer.brew( LogConditioningTempSrmColor(72, 9) )
    brewer.brew( Keg(recipe) )
    brewer.brew( LogKeggingTempBrewhouseEfficiency(recipe, 72, 4.0) )

    val batch = listener.batch
    println(batch)
    batch.recipe.nonEmpty shouldBe true