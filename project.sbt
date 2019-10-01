organization := "org.fluster"
homepage     := Some(url("https://github.com/Fluster-Org"))

val projectName = IO.readLines(new File("PROJECT_NAME")).head
name := projectName

val versionFromFile = IO.readLines(new File("VERSION")).head
version := versionFromFile
