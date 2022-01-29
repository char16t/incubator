name := "backend"
version := "0.1"
scalaVersion := "2.12.6"

dockerBaseImage := "openjdk:jre"
dockerExposedPorts := Seq(8080)

mainClass in Compile :=
  Some("com.manenkov.upsidemind.service.Main")

libraryDependencies ++= Seq(
  "com.typesafe.akka"  %% "akka-http"            % "10.1.7",
  "com.typesafe.akka"  %% "akka-stream"          % "2.5.19",
  "com.typesafe.akka"  %% "akka-http-spray-json" % "10.1.7",
  "com.typesafe.slick" %% "slick"                % "3.3.0",
  "com.typesafe.slick" %% "slick-hikaricp"       % "3.3.0",
  "org.slf4j"          % "slf4j-nop"             % "1.6.4",
  "org.postgresql"     %  "postgresql"           % "9.4-1206-jdbc42",
  "io.spray"           %% "spray-json"           % "1.3.5",
)

enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)