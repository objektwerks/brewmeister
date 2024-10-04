package objektwerks

import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.macros.*

object Serializer:
  given JsonValueCodec[Recipe] = JsonCodecMaker.make[Recipe]
  given JsonValueCodec[Metrics] = JsonCodecMaker.make[Metrics]
