package objektwerks.ui

import scalafx.beans.property.ObjectProperty
import scalafx.collections.ObservableBuffer

import objektwerks.{Batch, Recipe}

final class Model:
  val objectRecipe = ObservableBuffer[Recipe]()
  val objectBatch = ObservableBuffer[Batch]()

  val selectedRecipe = ObjectProperty[String]("")
  val selectedBatch = ObjectProperty[String]("")