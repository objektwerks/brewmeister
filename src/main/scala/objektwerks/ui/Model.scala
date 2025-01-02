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

  def add(recipe: Recipe): Unit =
    store.writeRecipe(recipe)
    observableRecipes.addOne(recipe)
    observableRecipes.sortInPlace

  def add(batch: Batch): Unit =
    store.writeBatch(batch)
    observableBatches.insert(0, batch)

  def update(recipe: Recipe): Unit =
    observableRecipes.update(selectedRecipeIndex.value, recipe)
    save(recipe)

  def save(recipe: Recipe): Unit = store.writeRecipe(recipe)

  def remove(recipe: Recipe): Unit =
    store.removeRecipe(recipe)
    observableRecipes.remove(selectedRecipeIndex)
    selectedRecipe.value = Recipe()

  def remove(batch: Batch): Unit =
    store.removeBatch(batch)
    observableBatches.remove(selectedBatchIndex)
    selectedBatch.value = Batch()