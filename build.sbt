scalaVersion := "2.12.10"

val Http4sVersion = "0.20.8"
val CirceVersion = "0.11.1"
val LogbackVersion = "1.2.3"

libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-blaze-server" % Http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % Http4sVersion,
  "org.http4s" %% "http4s-circe" % Http4sVersion,
  "org.http4s" %% "http4s-dsl" % Http4sVersion,
  "io.circe" %% "circe-generic" % CirceVersion,
  "ch.qos.logback" % "logback-classic" % LogbackVersion
)
addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.10.3")
addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.0")

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
  "-Xfatal-warnings",
)
