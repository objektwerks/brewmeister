package objektwerks.ui

import scalafx.beans.property.ObjectProperty
import scalafx.collections.ObservableBuffer

import objektwerks.{Batch, Recipe}

final class Model:
  val observableBatches = ObservableBuffer[Batch]()
  val observableRecipes = ObservableBuffer[Recipe]()

  val selectedBatch = ObjectProperty[Batch]( Batch() )
  val selectedRecipe = ObjectProperty[Recipe]( Recipe() )