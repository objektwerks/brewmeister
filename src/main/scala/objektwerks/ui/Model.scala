package objektwerks.ui

import scalafx.beans.property.ObjectProperty

import objektwerks.Recipe

final class Model:
  val objectRecipe = ObjectProperty[Recipe](Recipe())