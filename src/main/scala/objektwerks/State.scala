package objektwerks

sealed trait State

case object Sanitizing extends State

case object Preparing extends State

case object Malting extends State

case object Mashing extends State

case object Lautering extends State

case object Sparging extends State

case object Boiling extends State

case object Cooling extends State

case object Whirlpooling extends State

case object Fermenting extends State

case object Conditioning extends State

case object Packaging extends State