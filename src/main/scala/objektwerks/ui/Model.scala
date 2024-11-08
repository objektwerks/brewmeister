package objektwerks.ui

import scalafx.beans.property.ObjectProperty
import scalafx.collections.ObservableBuffer

import objektwerks.{Batch, Recipe}

final class Model:
  val observableRecipes = ObservableBuffer[Recipe]()
  val observableBatches = ObservableBuffer[Batch]()

  val selectedRecipe = ObjectProperty[Recipe]( Recipe() )
  val selectedBatch = ObjectProperty[Batch]( Batch() )