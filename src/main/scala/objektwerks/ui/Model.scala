package objektwerks.ui

import scalafx.beans.property.ObjectProperty
import scalafx.collections.ObservableBuffer

import objektwerks.{Batch, Recipe, Store}

final class Model(store: Store):
  var observableRecipes = ObservableBuffer.from(store.listRecipes)
  val selectedRecipeIndex = ObjectProperty(0)
  val selectedRecipe = ObjectProperty( Recipe() )

  var observableBatches = ObservableBuffer.from(store.listBatches)
  val selectedBatchIndex = ObjectProperty(0)
  val selectedBatch = ObjectProperty( Batch() )

  def add(recipe: Recipe): Unit =
    store.writeRecipe(recipe)
    observableRecipes.insert(0, recipe)
    observableRecipes = observableRecipes.sortInPlace
    selectedRecipeIndex.value = observableRecipes.indexOf(recipe)
    selectedRecipe.value = recipe

  def add(batch: Batch): Unit =
    store.writeBatch(batch)
    observableBatches.insert(0, batch)
    selectedBatchIndex.value = 0
    selectedBatch.value = batch

  def save(recipe: Recipe): Unit =
    store.writeRecipe(recipe)
    observableRecipes.update(selectedRecipeIndex.value, recipe)

  def remove(recipe: Recipe): Unit =
    store.removeRecipe(recipe)
    observableRecipes.remove(selectedRecipeIndex)
    selectedRecipeIndex.value = 0
    selectedRecipe.value = Recipe()

  def remove(batch: Batch): Unit =
    store.removeBatch(batch)
    observableBatches.remove(selectedBatchIndex)
    selectedBatchIndex.value = 0
    selectedBatch.value = Batch()