ThisBuild / scalaVersion := "3.2.2"

name := "demo"
version := "0.1.0"

val commonDepenedences = Seq(
    "org.scalactic" %% "scalactic" % "3.2.15",
    "org.scalatest" %% "scalatest" % "3.2.15" % "test",
)

lazy val moduleHttp = (project in file("modules/http"))
  .dependsOn(moduleCore)
  .settings(
    libraryDependencies ++= commonDepenedences,
    libraryDependencies ++= Seq(
        "dev.zio" %% "zio-http" % "0.0.5",
    ),
    assembly / assemblyMergeStrategy := {
        case "META-INF/io.netty.versions.properties" => MergeStrategy.first
        case x =>
        val oldStrategy = (assembly / assemblyMergeStrategy).value
        oldStrategy(x)
    }
  )
lazy val moduleCore = (project in file("modules/core"))
  .settings(
    libraryDependencies ++= commonDepenedences,
  )
lazy val root = (project in file("."))
  .aggregate(moduleHttp, moduleCore)
  .dependsOn(moduleHttp, moduleCore)
  .settings(
    mainClass := Some("proj.http.Application"),
    libraryDependencies ++= commonDepenedences,
    assembly / assemblyMergeStrategy := {
        case "META-INF/io.netty.versions.properties" => MergeStrategy.first
        case x =>
        val oldStrategy = (assembly / assemblyMergeStrategy).value
        oldStrategy(x)
    },
    assembly / assemblyJarName := "demo.jar",
    assembly / assemblyOutputPath := file("target/demo.jar"),
  )
