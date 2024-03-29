// TEST
// https://github.com/scoverage/sbt-scoverage
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.6.0-M3")


// SCALA STYLE
// http://www.scalastyle.org/sbt.html
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "1.0.0")

// https://github.com/scalacenter/sbt-scalafix-example/blob/master/project/plugins.sbt
resolvers += Resolver.sonatypeRepo("releases")
addSbtPlugin("ch.epfl.scala" % "sbt-scalafix" % "0.9.1")

resolvers += Resolver.bintrayRepo("scalameta", "maven")
addSbtPlugin("com.geirsson" % "sbt-scalafmt" % "1.6.0-RC4")
