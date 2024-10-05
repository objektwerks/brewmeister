package objektwerks

import ox.channels.Actor
import ox.supervised

final class Brewer:
  def brew(brew: Brew): Brewed =
    val recipe = brew.recipe
    val events = supervised:
      List(
        sanitize( Sanitize() ),
        prepare( Prepare(recipe) ),
        malt( Malt(recipe) ),
        mill( Mill(recipe) ),
        mash( Mash(recipe) ),
        lauter( Lauter(recipe) ),
        sparge( Sparge(recipe) ),
        boil( Boil(recipe) ),
        cool( Cool(recipe) ),
        whirlpool( Whirlpool(recipe) ),
        ferment( Ferment(recipe) ),
        condition( Condition(recipe) ),
        `package`( Package(recipe) )
      )
    Brewed( events )

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

  def lauter(lauter: Lauter): Lautered =
    supervised:
      Actor.create( Lauterer() ).ask( _.lauter( lauter ) )

  def sparge(sparge: Sparge): Sparged =
    supervised:
      Actor.create( Sparger() ).ask( _.sparge( sparge ) )

  def boil(boil: Boil): Boiled =
    supervised:
      Actor.create( Boiler() ).ask( _.boil( boil ) )

  def cool(cool: Cool): Cooled =
    supervised:
      Actor.create( Cooler() ).ask( _.cool( cool ) )

  def whirlpool(whirlpool: Whirlpool): Whirlpooled =
    supervised:
      Actor.create( Whirlpooler() ).ask( _.whirlpool( whirlpool ) )

  def ferment(ferment: Ferment): Fermented =
    supervised:
      Actor.create( Fermenter() ).ask( _.ferment( ferment ) )

  def condition(condition: Condition): Conditioned =
    supervised:
      Actor.create( Conditioner() ).ask( _.condition( condition ) )

  def `package`(`package`: Package): Packaged =
    supervised:
      Actor.create( Packager() ).ask( _.`package`( `package` ) )