val jarVersion = "0.74"

name := "brewmeister"
organization := "objektwerks"
version := "0.75-SNAPSHOT"
scalaVersion := "3.6.3-RC2"
mainClass := Some("objektwerks.App")
libraryDependencies ++= {
  Seq(
    "org.scalafx" %% "scalafx" % "23.0.1-R34",
    "org.controlsfx" % "controlsfx" % "11.2.1",
    "com.lihaoyi" %% "os-lib" % "0.11.4-M3",
    "com.lihaoyi" %% "upickle" % "4.0.2",
    "com.typesafe" % "config" % "1.4.3",
    "org.scalatest" %% "scalatest" % "3.2.19" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
outputStrategy := Some(StdoutOutput)
parallelExecution := false

// Begin: Assembly Tasks

lazy val createAssemblyDir = taskKey[File]("Create assembly dir.")
createAssemblyDir := {
  import java.nio.file.*

  val assemblyDir: File = baseDirectory.value / ".assembly"
  val assemblyPath: Path = assemblyDir.toPath

  if (!Files.exists(assemblyPath)) Files.createDirectory(assemblyPath)

  println(s"[createAssemblyDir] assembly dir: $assemblyPath is valid: ${Files.isDirectory(assemblyPath)}")

  assemblyDir
}

lazy val copyAssemblyJar = taskKey[Unit]("Copy assembly jar to assembly dir.")
copyAssemblyJar := {
  import java.nio.file.*

  val assemblyDir: File = createAssemblyDir.value
  val assemblyPath: String = s"${assemblyDir.toString}/${assemblyJarName.value}"

  val source: Path = (assembly / assemblyOutputPath).value.toPath
  val target: Path = Paths.get(assemblyPath)

  println(s"[copyAssemblyJar] source: $source")
  println(s"[copyAssemblyJar] target: $target")

  Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING)
}

// End: Assembly Tasks

// Begin: Assembly

/*
See assembly section in readme.
1. sbt -Dtarget="mac" clean test assembly copyAssemblyJar
2. sbt -Dtarget="m1" clean test assembly copyAssemblyJar
3. sbt -Dtarget="win" clean test assembly copyAssemblyJar
4. sbt -Dtarget="linux" clean test assembly copyAssemblyJar
*/
lazy val OS: String = sys.props.getOrElse("target", "") match {
  case name if name.startsWith("mac")   => "mac"
  case name if name.startsWith("m1")    => "mac-aarch64"
  case name if name.startsWith("win")   => "win"
  case name if name.startsWith("linux") => "linux"
  case _ => ""
}

if (OS == "mac") assemblyJarName := s"brewmeister-mac-$jarVersion.jar"
else if (OS == "mac-aarch64") assemblyJarName := s"brewmeister-m1-$jarVersion.jar"
else if (OS == "win") assemblyJarName := s"brewmeister-win-$jarVersion.jar"
else if (OS == "linux") assemblyJarName := s"brewmeister-linux-$jarVersion.jar"
else assemblyJarName := s"brewmeister-no-valid-target-specified-$jarVersion.jar"

lazy val javafxModules = Seq("base", "controls", "web")
libraryDependencies ++= javafxModules.map( module =>
  "org.openjfx" % s"javafx-$module" % "23" classifier OS
)

assembly / assemblyMergeStrategy := {
  case PathList("META-INF",  xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

// End: Assembly
