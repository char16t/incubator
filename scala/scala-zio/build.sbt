ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "app",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio"          % "2.0.2",
      "dev.zio" %% "zio-test"     % "2.0.2" % Test,
      "dev.zio" %% "zio-test-sbt" % "2.0.2" % Test,
    ),
    testFrameworks := Seq(
      new TestFramework("zio.test.sbt.ZTestFramework")
    ),
  )
