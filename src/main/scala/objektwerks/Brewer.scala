package objektwerks

import ox.channels.Actor
import ox.supervised

final class Brewer:
  def brew(brew: Brew): Brewed =
    val recipe = brew.recipe
    supervised:
      Brewed(
        List(
          Actor.create( Sanitizer() ).ask( _.sanitize( Sanitize() ) ),
          Actor.create( Preparer() ).ask( _.prepare( Prepare(recipe) ) ),
          Actor.create( Malter() ).ask( _.malt( Malt(recipe) ) ),
          Actor.create( Miller() ).ask( _.mill( Mill(recipe) ) ),
          Actor.create( Masher() ).ask( _.mash( Mash(recipe) ) ),
          Actor.create( Lauterer() ).ask( _.lauter( Lauter() ) ),
          Actor.create( Sparger() ).ask( _.sparge( Sparge() ) ),
          Actor.create( Boiler() ).ask( _.boil( Boil(recipe) ) ),
          Actor.create( Cooler() ).ask( _.cool( Cool() ) ),
          Actor.create( Whirlpooler() ).ask( _.whirlpool( Whirlpool(recipe) ) ),
          Actor.create( Fermenter() ).ask( _.ferment( Ferment(recipe) ) ),
          Actor.create( Conditioner() ).ask( _.condition( Condition(recipe) ) ),
          Actor.create( Packager() ).ask( _.`package`( Package() ) )
        )
      )

  def sanitize(sanitize: Sanitize): Sanitized =
    supervised:
      Actor.create( Sanitizer() ).ask( _.sanitize( sanitize ) )

  def prepare(prepare: Prepare): Prepared =
    supervised:
      Actor.create( Preparer() ).ask( _.prepare( prepare ) )

  def malt(malt: Malt): Malted =
    supervised:
      Actor.create( Malter() ).ask( _.malt( malt ) )

  def mill(mill: Mill): Milled =
    supervised:
      Actor.create( Miller() ).ask( _.mill( mill ) )

  def mash(mash: Mash): Mashed =
    supervised:
      Actor.create( Masher() ).ask( _.mash( mash ) )