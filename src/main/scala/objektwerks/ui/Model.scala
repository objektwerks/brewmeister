package objektwerks.ui

import scalafx.beans.property.ObjectProperty
import scalafx.collections.ObservableBuffer

import objektwerks.{Batch, Recipe, Store}

final class Model(store: Store):
  var observableRecipes = ObservableBuffer.from(store.listRecipes).sorted
  val selectedRecipe = ObjectProperty( Recipe(name = "") )

  var observableBatches = ObservableBuffer.from(store.listBatches).sorted
  val selectedBatch = ObjectProperty( Batch() )

  if observableRecipes.isEmpty then store.writeRecipe( Recipe.default )

  def add(recipe: Recipe): Unit =
    store.writeRecipe(recipe)
    observableRecipes.insert(0, recipe)
    selectedRecipe.value = recipe

  def add(batch: Batch): Unit =
    store.writeBatch(batch)
    observableBatches.insert(0, batch)
    selectedBatch.value = batch

  def save(recipe: Recipe): Boolean =
    store.writeRecipe(recipe)
    val index = observableRecipes.indexOf(selectedRecipe.value)
    if index != -1 then
      observableRecipes.update(index, recipe)
      true
    else false

  def remove(recipe: Recipe): Unit =
    store.removeRecipe(recipe)
    observableRecipes.remove(recipe)
    selectedRecipe.value = Recipe(name = "")

  def remove(batch: Batch): Unit =
    store.removeBatch(batch)
    observableBatches.remove(batch)
    selectedBatch.value = Batch()