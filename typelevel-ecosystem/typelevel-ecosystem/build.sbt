ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.1.1"

lazy val root = (project in file("."))
  .settings(
    name := "typelevel-ecosystem",
  )

libraryDependencies ++= Seq(
  "org.typelevel"         %% "cats-effect"              % "3.3.11"           withSources() withJavadoc(),
  "eu.timepit"            %% "fs2-cron-core"            % "0.7.2"            withSources() withJavadoc(),
  "eu.timepit"            %% "fs2-cron-calev"           % "0.7.2"            withSources() withJavadoc(),

  // Logging
  "org.typelevel"         %% "log4cats-slf4j"           % "2.3.0"            withSources() withJavadoc(),
  "org.slf4j"             %  "slf4j-api"                % "1.7.36"           withSources() withJavadoc(),
  "ch.qos.logback"        %  "logback-core"             % "1.2.11"           withSources() withJavadoc(),
  "ch.qos.logback"        %  "logback-classic"          % "1.2.11"           withSources() withJavadoc(),

  // Testing
  "org.typelevel"         %% "munit-cats-effect-3"      % "1.0.7"     % Test  withSources() withJavadoc(),
  "com.disneystreaming"   %% "weaver-cats"              % "0.7.11"    % Test  withSources() withJavadoc(),
  "org.scalacheck"        %% "scalacheck"               % "1.16.0"    % Test  withSources() withJavadoc(),
  "org.typelevel"         %% "scalacheck-effect"        % "1.0.4"             withSources() withJavadoc(),
  "org.typelevel"         %% "scalacheck-effect-munit"  % "1.0.4"     % Test  withSources() withJavadoc(),
)

testFrameworks += new TestFramework("weaver.framework.CatsEffect")
