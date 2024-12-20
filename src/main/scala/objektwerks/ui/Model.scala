package objektwerks.ui

import scalafx.beans.property.ObjectProperty
import scalafx.collections.ObservableBuffer

import objektwerks.{Batch, Recipe, Store}

final class Model(store: Store):
  val observableRecipes = ObservableBuffer.from(store.listRecipes)
  val observableBatches = ObservableBuffer.from(store.listBatches)

  val selectedRecipeIndex = ObjectProperty(0)
  val selectedRecipe = ObjectProperty( Recipe() )

  val selectedBatchIndex = ObjectProperty(0)
  val selectedBatch = ObjectProperty( Batch() )

  def save(recipe: Recipe): Unit = store.writeRecipe(recipe)