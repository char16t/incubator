name := "zero"
version := "0.1"
scalaVersion := "2.12.6"
libraryDependencies ++= Seq(
  "com.typesafe.akka"   %% "akka-http"             % "10.1.8",
  "com.typesafe.akka"   %% "akka-actor"            % "2.5.20",
  "com.typesafe.akka"   %% "akka-stream"           % "2.5.20",
  "com.typesafe.akka"   %% "akka-http-spray-json"  % "10.1.8",
  "de.heikoseeberger"   %% "akka-http-play-json"   % "1.17.0",
  "com.typesafe.akka"   %% "akka-slf4j"            % "2.5.20",
  "ch.qos.logback"      %  "logback-classic"       % "1.2.3",
  "com.typesafe.slick"  %% "slick"                 % "3.3.0",
  "com.typesafe.slick"  %% "slick-hikaricp"        % "3.3.0",
  "com.github.tminglei" %% "slick-pg"              % "0.17.2",
  "com.github.tminglei" %% "slick-pg_spray-json"   % "0.17.2",
  
  "com.typesafe.akka" %% "akka-testkit"    % "2.5.20"   % "test",
  "org.scalatest"     %% "scalatest"       % "3.0.5"    % "test",
)