package objektwerks

import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.macros.*

object Serializer:
  given JsonValueCodec[Recipe] = JsonCodecMaker.make[Recipe]
  given JsonValueCodec[Metrics] = JsonCodecMaker.make[Metrics]

  given JsonValueCodec[Grain] = JsonCodecMaker.make[Grain]
  given JsonValueCodec[Hop] = JsonCodecMaker.make[Hop]
  given JsonValueCodec[Adjunct] = JsonCodecMaker.make[Adjunct]
  given JsonValueCodec[Yeast] = JsonCodecMaker.make[Yeast]

