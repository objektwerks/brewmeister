package objektwerks

import scalafx.beans.property.ObjectProperty

import upickle.default.{ReadWriter => JsonSupport}

object Batch extends Brew:
  given Ordering[Batch] = Ordering.by[Batch, String](b => b.completed).reverse

@upickle.implicits.serializeDefaults(true)
final case class Batch(recipe: String = "",
                       style: String = "",
                       volume: Volume = Volume(0.0, UoM.gl),
                       mashingTemp: Int = 0,
                       pH: Double = 0.0,
                       boilingTemp: Int = 0,
                       coolingTemp: Int = 0,
                       mashEfficiency: Int = 0,
                       originalGravity: Double = 0.0,
                       fermentingTemp: Int = 0,
                       finalGravity: Double = 0.0,
                       conditioningTemp: Int = 0,
                       srmColor: Int = 0,
                       ibuBitterness: Int = 0,
                       alcoholByVolume: Double = 0.0,
                       alcoholByWeight: Double = 0.0,
                       calories: Int = 0,
                       keggingTemp: Int = 0,
                       brewhouseEfficiency: Int = 0,
                       appearance: Int = 1,
                       aroma: Int = 1,
                       taste: Int = 1,
                       log: List[String] = List.empty[String],
                       started: String = "",
                       process: Process = Process(),
                       completed: String = "",
                       stored: String = "") derives CanEqual, JsonSupport:
  val nameProperty = ObjectProperty(s"$recipe.$stored")
  val brewhouseEfficiencyProperty = ObjectProperty(brewhouseEfficiency)
  val fileProperty = ObjectProperty(s"$recipe.$stored.json")

@upickle.implicits.serializeDefaults(true)
final case class Process(sanitizingStarted: String = "",
                         sanitizingCompleted: String = "",
                         preparingStarted: String = "",
                         preparingCompleted: String = "",
                         maltingStarted: String = "",
                         maltingCompleted: String = "",
                         millingStarted: String = "",
                         millingCompleted: String = "",
                         mashingStarted: String = "",
                         mashingCompleted: String = "",
                         lauteringStarted: String = "",
                         lauteringCompleted: String = "",
                         spargingStarted: String = "",
                         spargingCompleted: String = "",
                         boilingStarted: String = "",
                         boilingCompleted: String = "",
                         coolingStarted: String = "",
                         coolingCompleted: String = "",
                         whirlpoolingStarted: String = "",
                         whirlpoolingCompleted: String = "",
                         fermentingStarted: String = "",
                         fermentingCompleted: String = "",
                         conditioningStarted: String = "",
                         conditioningCompleted: String = "",
                         keggingStarted: String = "",
                         keggingCompleted: String = "") derives CanEqual, JsonSupport