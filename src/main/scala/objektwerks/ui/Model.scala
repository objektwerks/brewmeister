package objektwerks.ui

import com.typesafe.scalalogging.LazyLogging

import scalafx.beans.property.ObjectProperty
import scalafx.collections.ObservableBuffer

import objektwerks.{Batch, Recipe, Store}

final class Model(store: Store) extends LazyLogging:
  val recipes = store.listRecipes
  if recipes.isEmpty then store.writeRecipe( Recipe.default )
  val observableRecipes = ObservableBuffer.from( store.listRecipes ).sorted
  val selectedRecipe = ObjectProperty( Recipe(name = "") )

  val observableBatches = ObservableBuffer.from( store.listBatches ).sorted
  val selectedBatch = ObjectProperty( Batch() )

  logger.info("Initialized model.")

  def add(recipe: Recipe): Unit =
    store.writeRecipe(recipe)
    observableRecipes.insert(0, recipe)
    selectedRecipe.value = recipe

  def add(batch: Batch): Unit =
    store.writeBatch(batch)
    observableBatches.insert(0, batch)
    selectedBatch.value = batch

  def save(recipe: Recipe): Unit =
    store.writeRecipe(recipe)
    val index = observableRecipes.indexOf(selectedRecipe.value)
    if index > -1 then observableRecipes.update(index, recipe)

  def remove(recipe: Recipe): Boolean =
    store.removeRecipe(recipe)
    val removed = observableRecipes.remove(recipe)
    if removed then selectedRecipe.value = Recipe(name = "")
    removed

  def remove(batch: Batch): Boolean =
    store.removeBatch(batch)
    val removed = observableBatches.remove(batch)
    if removed then selectedBatch.value = Batch()
    removed