scalaVersion := "2.12.10"

val http4sVersion = "0.20.8"
val circeVersion = "0.11.1"
val LogbackVersion = "1.2.3"
val tapirVersion = "0.11.4"
val zioVersion = "1.0.0-RC14"

libraryDependencies ++= Seq(
  "org.http4s"             %% "http4s-blaze-server" % http4sVersion,
  "org.http4s"             %% "http4s-blaze-client" % http4sVersion,
  "org.http4s"             %% "http4s-circe"        % http4sVersion,
  "org.http4s"             %% "http4s-dsl"          % http4sVersion,
  "io.circe"               %% "circe-generic"       % circeVersion,

  "ch.qos.logback"         % "logback-classic"      % LogbackVersion,

  "com.softwaremill.tapir" %% "tapir-core" % tapirVersion,
  // https://tapir-scala.readthedocs.io/en/latest/server/http4s.html
  "com.softwaremill.tapir" %% "tapir-http4s-server" % tapirVersion,
  // https://tapir-scala.readthedocs.io/en/latest/endpoint/json.html
  "com.softwaremill.tapir" %% "tapir-json-circe" % tapirVersion,

  "dev.zio"                %% "zio"                 % zioVersion
  // "dev.zio" %% "zio-streams" % zioVersion,
)

addCompilerPlugin("org.typelevel" %% "kind-projector"     % "0.10.3")
addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.0")

scalacOptions ++= List(
  "-Yrangepos",
  "-feature",
  "-Ywarn-dead-code",
  "-Ywarn-inaccessible",
  "-Ywarn-unused",
  "-Ywarn-unused-import",
  "-Ywarn-value-discard",
  "-deprecation",
  "-encoding",
  "utf8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-Ypartial-unification",
  "-Xfatal-warnings"
)
