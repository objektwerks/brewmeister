package objektwerks

sealed trait Actor:
  def close: Unit = ()

class Sanitizer extends Actor

class Preparer extends Actor

class Malter extends Actor

class Miller extends Actor

class Masher extends Actor

class Lauter extends Actor

class Sparger extends Actor

class Boiler extends Actor

class Cooler extends Actor

class Whirlpooler extends Actor

class Fermenter extends Actor

class Conditioner extends Actor

class Packager extends Actor