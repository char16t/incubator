ThisBuild / organization := "com.manenkov"
ThisBuild / version      := "0.1.0-alpha"
ThisBuild / scalaVersion := "2.12.6"

lazy val root = (project in file("."))
  .settings(
    name := "jsonrpc",
  )

libraryDependencies ++= Seq(
  "io.spray"      %% "spray-json"     % "1.3.5",
  "org.scalatest" %  "scalatest_2.12" % "3.0.5" % "test",
)
