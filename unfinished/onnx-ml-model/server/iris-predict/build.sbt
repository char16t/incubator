ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "iris-predict",
    libraryDependencies ++= Seq(
      "com.microsoft.onnxruntime" % "onnxruntime" % "1.12.1"
    ),
  )
