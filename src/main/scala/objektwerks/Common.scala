package objektwerks

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import upickle.default.{ReadWriter => JsonSupport}

final case class DoubleRange(low: Double, high: Double) derives CanEqual, JsonSupport:
  def avg: Double = format( (low + high) / 2 )

final case class IntRange(low: Int, high: Int) derives CanEqual, JsonSupport:
  def avg: Int = (low + high) / 2

final case class TempRangeDuration(tempRange: IntRange, duration: Int, unit: UoT) derives CanEqual, JsonSupport

final case class Volume(value: Double, unit: UoM) derives CanEqual, JsonSupport

enum UoM derives JsonSupport:
  case oz, gl, ml, l, lb, kg

enum UoT derives JsonSupport:
  case minutes, days, weeks, months

extension (double: Double)
  def format: Double = f"$double%1.1f".toDouble
  def formatGravity: Double = f"$double%1.3f".toDouble

extension (now: String)
  def localDateTime: LocalDateTime = if now.nonEmpty then LocalDateTime.parse(now, formatter) else LocalDateTime.now

def now(): String = LocalDateTime.now.format(formatter)

private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss.SSS")