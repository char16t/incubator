import sbt._
import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import scalariform.formatter.preferences.AlignSingleLineCaseStatements
import com.typesafe.sbt.packager.docker._

lazy val akkaVersion = "2.5.25"
lazy val akkaHttpVersion = "10.1.10"

name := "myapp"
organization := "com.manenkov"
organizationName := "Valeriy Manenkov"
startYear := Some(2019)
maintainer := "valeriy@manenkov.com"

scalaVersion := "2.12.9"

enablePlugins(AutomateHeaderPlugin, JavaServerAppPackaging)

resolvers += Resolver.bintrayRepo("hseeberger", "maven")

libraryDependencies ++= List(
  "com.typesafe.akka" %% "akka-actor"           % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster"         % akkaVersion,
  "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream"          % akkaVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,

  "com.typesafe.akka" %% "akka-http-testkit"    % akkaHttpVersion % Test,
  "com.typesafe.akka" %% "akka-stream-testkit"  % akkaVersion     % Test,
  "com.typesafe.akka" %% "akka-testkit"         % akkaVersion     % Test,
  "org.scalatest"     %% "scalatest"            % "3.0.8"         % Test,
)

dockerEntrypoint ++= Seq(
  """-DakkaActorSystemName="$AKKA_ACTOR_SYSTEM_NAME"""",
  """-Dakka.remote.netty.tcp.hostname="$(eval "echo $AKKA_REMOTING_BIND_HOST")"""",
  """-Dakka.remote.netty.tcp.port="$AKKA_REMOTING_BIND_PORT"""",
  """$(IFS=','; I=0; for NODE in $AKKA_SEED_NODES; do echo "-Dakka.cluster.seed-nodes.$I=akka.tcp://$AKKA_ACTOR_SYSTEM_NAME@$NODE"; I=$(expr $I + 1); done)""",
  "-Dakka.io.dns.resolver=async-dns",
  "-Dakka.io.dns.async-dns.resolve-srv=true",
  "-Dakka.io.dns.async-dns.resolv-conf=on",
  """-DhttpHost="$HTTP_HOST"""",
  """-DhttpPort="$HTTP_PORT"""",
  """-DclusterMembershipAskTimeout="$CLUSTER_MEMBERSHIP_ASK_TIMEOUT""""
)
dockerCommands :=
  dockerCommands.value.flatMap {
    case ExecCmd("ENTRYPOINT", args @ _*) => Seq(Cmd("ENTRYPOINT", args.mkString(" ")))
    case v => Seq(v)
  }
dockerRepository := Some("char16t")
dockerUpdateLatest := true
dockerBaseImage := "local/openjdk-jre-8-bash"

ScalariformKeys.preferences :=
  ScalariformKeys.preferences.value
    .setPreference(AlignSingleLineCaseStatements, true)
    .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 100)

licenses += ("MIT", new URL("file://LICENSE"))
parallelExecution in Test := false
