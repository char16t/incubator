name := "assistant-service"
version := "0.2"
scalaVersion := "2.12.6"

dockerBaseImage := "openjdk:jre"
dockerExposedPorts := Seq(80, 443)

mainClass in Compile :=
  Some("com.manenkov.assistant.Server")

libraryDependencies ++= Seq(
  "com.softwaremill.sttp" %% "core"               % "1.6.1",
  "com.typesafe.play"     %% "play-json"          % "2.7.3",
  "com.typesafe.akka"     %% "akka-actor"         % "2.5.23",
  "com.typesafe.akka"     %% "akka-stream"        % "2.5.23",
  "com.typesafe.akka"     %% "akka-http"          % "10.1.8",
  "com.typesafe.slick"    %% "slick"              % "3.3.0",
  "com.typesafe.slick"    %% "slick-hikaricp"     % "3.3.0",
  "com.github.tminglei"   %% "slick-pg"           % "0.18.0",
  "com.github.tminglei"   %% "slick-pg_play-json" % "0.18.0",
  "org.postgresql"        %  "postgresql"         % "9.4-1206-jdbc42",
  "org.slf4j"             % "slf4j-nop"           % "1.6.4",
)

enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)
