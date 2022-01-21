ThisBuild / organization := "com.democompany"
ThisBuild / scalaVersion := "2.13.1"
ThisBuild / version      := "0.1.0"
ThisBuild / licenses     += ("Public Domain", url("https://unlicense.org/"))

lazy val scala213 = "2.13.1"
lazy val scala212 = "2.12.10"
lazy val scala211 = "2.11.12"
lazy val scala210 = "2.10.7"
lazy val scala29 = "2.9.3"
lazy val scala28 = "2.8.2"
lazy val scala27 = "2.7.7"
lazy val scala26 = "2.6.1"
lazy val scala25 = "2.5.1"
lazy val supportedScalaVersions = List(scala213, scala212, scala211, scala210, scala29, scala28, scala27, scala26, scala25)

lazy val bintrayRepo = "maven-test"
lazy val bintrayOrg = "char16t"
lazy val nameCore = "demo-core"
lazy val nameModule1 = "demo-module1"

lazy val root = (project in file("."))
  .aggregate(core, module1)
  .settings(
    crossScalaVersions := Nil,
    publish / skip := true
  )

lazy val core = (project in file(nameCore))
  .settings(
    name                := nameCore,
    crossScalaVersions  := supportedScalaVersions,
    bintrayOrganization := Some(bintrayOrg),
    bintrayRepository   := bintrayRepo,
    publishTo           := Some("bintray" at s"https://api.bintray.com/maven/${bintrayOrg}/${bintrayRepo}/${nameCore}/;publish=1")
  )

lazy val module1 = (project in file(nameModule1))
  .dependsOn(core)
  .settings(
    name                := nameModule1,
    crossScalaVersions  := supportedScalaVersions,
    bintrayOrganization := Some(bintrayOrg),
    bintrayRepository   := bintrayRepo,
    publishTo           := Some("bintray" at s"https://api.bintray.com/maven/${bintrayOrg}/${bintrayRepo}/${nameModule1}/;publish=1")
  )
