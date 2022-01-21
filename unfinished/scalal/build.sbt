name := "scalal"
version := "0.1"
scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.2.3",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.3",
  "com.github.tminglei" %% "slick-pg" % "0.15.3",
  "com.github.tminglei" %% "slick-pg_spray-json" % "0.15.3",
  "io.github.nafg" %% "slick-migration-api" % "0.4.4",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
)

resolvers ++= Seq(
  Resolver.jcenterRepo,
)
