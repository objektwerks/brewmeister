name := "ciphers"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "3.5.1"
mainClass := Some("objektwerks.App")
libraryDependencies ++= {
  Seq(
    "org.scalafx" %% "scalafx" % "22.0.0-R33",
    "com.softwaremill.ox" %% "core" % "0.4.0"
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)