package objektwerks

import ox.channels.*
import ox.{never, supervised}

sealed trait Actor:
  def close: Unit = println(s"*** ${getClass.getSimpleName}.close")

final class Brewer extends Actor:
  var metrics = Metrics.empty

  def log(state: State): Unit = scribe.info(state.toString)

  def brew(id: Long, recipe: Recipe): Metrics =
    supervised:
      val sanitizer = Actor.create( Sanitizer() )
      sanitizer.tell( _.process( Sanitize() ) )

      val preparer = Actor.create( Preparer() )
      preparer.tell( _.process( Prepare(recipe) ) )

      never

    metrics

final class Sanitizer extends Actor:
  def process(sanitize: Sanitize): Sanitized = Sanitized()

final class Preparer extends Actor:
  def process(prepare: Prepare): Prepared = Prepared()

final class Malter extends Actor

final class Miller extends Actor

final class Masher extends Actor

final class Lauterer extends Actor

final class Sparger extends Actor

final class Boiler extends Actor

final class Cooler extends Actor

final class Whirlpooler extends Actor

final class Fermenter extends Actor

final class Conditioner extends Actor

final class Packager extends Actor