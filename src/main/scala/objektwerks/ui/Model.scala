package objektwerks.ui

import scalafx.beans.property.ObjectProperty

import objektwerks.{Batch, Recipe}

final class Model:
  val objectRecipe = ObjectProperty[Recipe](Recipe())
  val objectBatch = ObjectProperty[Batch](Batch())