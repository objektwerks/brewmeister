package objektwerks

import ox.channels.Actor
import ox.supervised

final class Brewer:
  def brew(recipe: Recipe): List[Event] =
    supervised:
      val sanitized = Actor.create( Sanitizer() ).ask( _.sanitize( Sanitize() ) )
      val prepared = Actor.create( Preparer() ).ask( _.prepare( Prepare(recipe) ) )
      val malted = Actor.create( Malter() ).ask( _.malt( Malt(recipe) ) )
      val milled = Actor.create( Miller() ).ask( _.mill( Mill(recipe) ) )
      val mashed = Actor.create( Masher() ).ask( _.mash( Mash(recipe) ) )
      val lautered = Actor.create( Lauterer() ).ask( _.lauter( Lauter() ) )
      val sparged = Actor.create( Sparger() ).ask( _.sparge( Sparge() ) )
      val boiled = Actor.create( Boiler() ).ask( _.boil( Boil(recipe) ) )
      val cooled = Actor.create( Cooler() ).ask( _.cool( Cool() ) )
      val whirlpooled = Actor.create( Whirlpooler() ).ask( _.whirlpool( Whirlpool(recipe) ) )
      val fermented = Actor.create( Fermenter() ).ask( _.ferment( Ferment(recipe) ) )
      val conditioned = Actor.create( Conditioner() ).ask( _.condition( Condition(recipe) ) )
      val packaged = Actor.create( Packager() ).ask( _.`package`( Package() ) )
      List(
        sanitized,
        prepared,
        malted,
        milled,
        mashed,
        lautered,
        sparged,
        boiled,
        cooled,
        whirlpooled,
        fermented,
        conditioned,
        packaged
      )