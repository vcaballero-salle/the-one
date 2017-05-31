import Dependencies._
import sbt.file

lazy val root = (project in file(".")).
  settings(
    name := "ONE",
    autoScalaLibrary := false,
    libraryDependencies ++= Seq(
      "junit" % "junit" % "4.12" % "test",
      "com.novocode" % "junit-interface" % "0.11" % "test"
    ),
    testOptions += Tests.Argument(TestFrameworks.JUnit, "-v", "-q")
  )

// .jar in lib/ directory are automatically added to the classpath (lib/*.jar)

