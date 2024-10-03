package objektwerks

import ox.channels.Actor
import ox.supervised

final class Brewer:
  def simulate(recipe: Recipe, listener: Listener): Unit =
    supervised:
      Actor.create( Sanitizer() ).ask( _.sanitize( Sanitize(listener) ) )
      Actor.create( Preparer() ).ask( _.prepare( Prepare(recipe), listener ) )
      Actor.create( Malter() ).ask( _.malt( Malt(recipe), listener ) )
      Actor.create( Miller() ).ask( _.mill( Mill(recipe), listener ) )
      Actor.create( Masher() ).ask( _.mash( Mash(recipe), listener ) )
      Actor.create( Lauterer() ).ask( _.mash( Lauter(), listener ) )
      Actor.create( Sparger() ).ask( _.mash( Sparge(), listener ) )
      Actor.create( Boiler() ).ask( _.mash( Boil(recipe), listener ) )
      Actor.create( Cooler() ).ask( _.mash( Cool(), listener ) )
      Actor.create( Whirlpooler() ).ask( _.mash( Whirlpool(recipe), listener ) )
      Actor.create( Fermenter() ).ask( _.mash( Ferment(recipe), listener ) )
      Actor.create( Conditioner() ).ask( _.mash( Condition(), listener ) )
      Actor.create( Packager() ).ask( _.mash( Package(), listener ) )