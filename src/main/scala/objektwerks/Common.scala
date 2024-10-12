package objektwerks

import java.time.LocalDateTime

import upickle.default.{ReadWriter => JsonSupport}

def now(): String = LocalDateTime.now.toString

def localDateTime(now: String): LocalDateTime = if now.nonEmpty then LocalDateTime.parse(now) else LocalDateTime.now

def format(double: Double): Double = f"$double%1.1f".toDouble

def formatGravity(double: Double): Double = f"$double%1.3f".toDouble

enum UoM derives JsonSupport:
  case oz, gl, ml, l, lb, kg

enum UoT derives JsonSupport:
  case minutes, days, weeks, months