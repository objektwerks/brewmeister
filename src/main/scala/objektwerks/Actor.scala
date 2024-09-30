package objektwerks

sealed trait Actor:
  def close: Unit = println(s"*** ${getClass.getSimpleName}.close")

final class Brewer extends Actor

final class Sanitizer extends Actor

final class Preparer extends Actor

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