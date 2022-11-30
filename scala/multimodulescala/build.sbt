ThisBuild / organization := "com.manenkov.multimodulescala"
ThisBuild / name := "parent"
ThisBuild / scalaVersion := "3.1.2"

lazy val commonSettings = Seq(
  crossScalaVersions := Seq(
    "2.10.7",
    "2.11.12",
    "2.12.16",
    "2.13.6",
    "2.13.8",
    "3.1.2"
  ),
  libraryDependencies ++= commonDependencies,
  publishTo := Some(MavenCache("local-maven", file("./maven-repo/releases"))),
  publishConfiguration := publishConfiguration.value.withOverwrite(true),
  publishLocalConfiguration := publishLocalConfiguration.value.withOverwrite(
    true
  ),
  releaseCrossBuild := true
)

lazy val commonDependencies = Seq(
  "org.scalactic" %% "scalactic" % "3.2.12",
  "org.scalatest" %% "scalatest" % "3.2.12" % Test
)

lazy val root = (project in file("."))
  .aggregate(web, core)
  .settings(
    commonSettings
    // other settings
  )

lazy val core = (project in file("multimodulescala-core"))
  .settings(
    commonSettings
    // other settings
  )

lazy val web = (project in file("multimodulescala-web"))
  .dependsOn(core)
  .settings(
    commonSettings
    // other settings
  )

lazy val docs = project
  .in(file("multimodulescala-docs"))
  .dependsOn(core, web)
  .settings(
    commonSettings,

    // other settings
    unidocProjectFilter in (ScalaUnidoc, unidoc) := inProjects(core, web),
    target in (ScalaUnidoc, unidoc) := (LocalRootProject / baseDirectory).value / "website" / "static" / "api",
    cleanFiles += (target in (ScalaUnidoc, unidoc)).value,
    docusaurusCreateSite := docusaurusCreateSite
      .dependsOn(Compile / unidoc)
      .value
  )
  .enablePlugins(MdocPlugin, ScalaUnidocPlugin, DocusaurusPlugin)
