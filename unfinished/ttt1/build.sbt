ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "ttt1"
  )

libraryDependencies += "org.typelevel"            %% "cats-effect"           % "3.3.11"
libraryDependencies += "org.postgresql"           % "postgresql"             % "42.3.4"
libraryDependencies += "com.zaxxer"               % "HikariCP"               % "5.0.1"
libraryDependencies += "org.tpolecat"             %% "doobie-core"           % "1.0.0-RC2"
libraryDependencies += "org.tpolecat"             %% "doobie-postgres"       % "1.0.0-RC2"
libraryDependencies += "org.tpolecat"             %% "doobie-postgres-circe" % "1.0.0-RC2"
libraryDependencies += "org.tpolecat"             %% "doobie-hikari"         % "1.0.0-RC2"
libraryDependencies += "io.circe"                 %% "circe-core"            % "0.14.1"
libraryDependencies += "io.circe"                 %% "circe-generic"         % "0.14.1"
libraryDependencies += "io.circe"                 %% "circe-parser"          % "0.14.1"
libraryDependencies += "org.flywaydb"             % "flyway-core"            % "8.5.10"

libraryDependencies += "org.tpolecat"             %% "doobie-specs2"    % "1.0.0-RC2" % Test
libraryDependencies += "org.tpolecat"             %% "doobie-scalatest" % "1.0.0-RC2" % Test
libraryDependencies += "org.tpolecat"             %% "doobie-munit"     % "1.0.0-RC2" % Test
libraryDependencies += "org.scalactic"            %% "scalactic"        % "3.2.11"
libraryDependencies += "org.scalatest"            %% "scalatest"        % "3.2.11" % Test
libraryDependencies += "org.scalacheck"           %% "scalacheck"       % "1.15.4" % Test

