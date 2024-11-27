package objektwerks.ui

import scalafx.beans.property.ObjectProperty
import scalafx.collections.ObservableBuffer

import objektwerks.{Batch, Recipe}

final class Model(recipes: List[Recipe], batches: List[Batch]):
  val observableRecipes = ObservableBuffer.from(recipes)
  val observableBatches = ObservableBuffer.from(batches)

  val selectedBatch = ObjectProperty( Batch() )
  val selectedRecipe = ObjectProperty( Recipe() )