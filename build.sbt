name := "ciphers"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "3.5.1"
mainClass := Some("objektwerks.App")
libraryDependencies ++= {
  val jsoniterVersion = "2.30.14"
  Seq(
    "org.scalafx" %% "scalafx" % "22.0.0-R33",
    "com.softwaremill.ox" %% "core" % "0.4.0",
    "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core" % jsoniterVersion,
    "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % jsoniterVersion
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)

// Begin: Assembly Tasks

lazy val createAssemblyDir = taskKey[File]("Create assembly dir.")
createAssemblyDir := {
  import java.nio.file._

  val assemblyDir: File = baseDirectory.value / ".assembly"
  val assemblyPath: Path = assemblyDir.toPath()

  if (!Files.exists(assemblyPath)) Files.createDirectory(assemblyPath)

  println(s"[createAssemblyDir] assembly dir: ${assemblyPath} is valid: ${Files.isDirectory(assemblyPath)}")

  assemblyDir
}

lazy val copyAssemblyJar = taskKey[Unit]("Copy assembly jar to assembly dir.")
copyAssemblyJar := {
  import java.nio.file._

  val assemblyDir: File = createAssemblyDir.value
  val assemblyPath: String = s"${assemblyDir.toString}/${assemblyJarName.value}"

  val source: Path = (assembly / assemblyOutputPath).value.toPath
  val target: Path = Paths.get(assemblyPath)

  println(s"[copyAssemblyJar] source: ${source}")
  println(s"[copyAssemblyJar] target: ${target}")

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

if (OS == "mac") assemblyJarName := "pool-balance-mac-0.1.jar"
else if (OS == "mac-aarch64") assemblyJarName := "pool-balance-m1-0.1.jar"
else if (OS == "win") assemblyJarName := "pool-balance-win-0.1.jar"
else if (OS == "linux") assemblyJarName := "pool-balance-linux-0.1.jar"
else assemblyJarName := "pool-balance-no-valid-target-specified-0.1.jar"

lazy val javafxModules = Seq("base", "controls", "web")
libraryDependencies ++= javafxModules.map( module =>
  "org.openjfx" % s"javafx-$module" % "22" classifier OS
)

assembly / assemblyMergeStrategy := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

// End: Assembly