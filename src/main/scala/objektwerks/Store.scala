package objektwerks

final class Store(storePath: String):
  println(storePath)

  def listRecipes: List[Recipe] = ???

  def addRecipe(recipe: Recipe) = ???

  def listBatches: List[Batch] = ???