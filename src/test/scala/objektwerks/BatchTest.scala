package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import upickle.default.*

final class BatchTest extends AnyFunSuite with Matchers:
  test("batch"):
    val batch = Batch.default
    val batchAsJson = write(batch)
    println(batchAsJson)
    batch shouldBe read[Batch](batchAsJson)