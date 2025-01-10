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
    observableRecipes.add(recipe)
    //observableRecipes = observableRecipes.sorted
    selectedRecipe.value = recipe

  def add(batch: Batch): Unit =
    store.writeBatch(batch)
    observableBatches.add(batch)
    selectedBatch.value = batch

  def save(recipe: Recipe): Unit =
    store.writeRecipe(recipe)
    observableRecipes.update( observableRecipes.indexOf(selectedRecipe.value), recipe )

  def remove(recipe: Recipe): Unit =
    store.removeRecipe(recipe)
    observableRecipes.remove(recipe)
    //observableRecipes = observableRecipes.sorted
    selectedRecipe.value = Recipe(name = "")

  def remove(batch: Batch): Unit =
    store.removeBatch(batch)
    observableBatches.remove(batch)
    observableBatches = observableBatches.sorted
    selectedBatch.value = Batch()