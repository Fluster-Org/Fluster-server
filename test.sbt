// ////////////Test/////////////////
// https://mvnrepository.com/artifact/org.scalatest/scalatest
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.5" % Test
)
parallelExecution in Test := false
/////////////////////////////////////

// Test
testOptions in Test += Tests.Argument("-oD")
javaOptions in Test ++= Seq(
  "-Xms512M",
  "-Xmx2048M",
  "-XX:MaxPermSize=2048M",
  "-XX:+CMSClassUnloadingEnabled"
)
parallelExecution in Test := false
fork in Test              := false
