lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.mozilla.telemetry",
      scalaVersion := "2.11.8",
      version      := "0.1.0-SNAPSHOT"
    )))

name := "telemetry-spark-packages-assembly"

val opsRepo = "https://s3-us-west-2.amazonaws.com/net-mozaws-data-us-west-2-ops-mavenrepo/"

resolvers ++= Seq(
  "S3 local maven releases" at opsRepo + "releases",
  "S3 local maven snapshots" at opsRepo + "snapshots"
)

libraryDependencies ++= Seq(
  "com.mozilla.telemetry" %% "spark-hyperloglog" % "2.2.2"
)

assemblyJarName in assembly := s"${name.value}.jar"

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

assemblyMergeStrategy in assembly := {
  // Ignore a variety of python packaging-related files
  case "setup.py" => MergeStrategy.discard
  case "setup.cfg" => MergeStrategy.discard
  case "tox.ini" => MergeStrategy.discard
  case "MANIFEST.in" => MergeStrategy.discard
  case PathList("tests", xs @ _*) => MergeStrategy.discard

  // Other rules
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}
