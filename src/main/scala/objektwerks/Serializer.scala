package objektwerks

object Serializer:
  import upickle.default.*

  given ReadWriter[ContainerType] = macroRW
  given ReadWriter[MixinStep] = macroRW
  given ReadWriter[UnitType] = macroRW

  given ReadWriter[Grain] = macroRW
  given ReadWriter[Hop] = macroRW
  given ReadWriter[Adjunct] = macroRW
  given ReadWriter[Yeast] = macroRW

  given ReadWriter[Recipe] = macroRW
  given ReadWriter[Metrics] = macroRW
