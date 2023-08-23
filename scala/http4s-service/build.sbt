name := "demo"
version := "0.1.0"
scalaVersion := "3.3.0"

val http4sVersion = "0.23.23"
val logbackVersion = "1.4.8"

libraryDependencies ++= Seq(
  "org.http4s"     %% "http4s-ember-client" % http4sVersion,
  "org.http4s"     %% "http4s-ember-server" % http4sVersion,
  "org.http4s"     %% "http4s-dsl"          % http4sVersion,
  "ch.qos.logback" %  "logback-classic"     % logbackVersion % Runtime,
)

assembly / assemblyMergeStrategy := {
      case "module-info.class" => MergeStrategy.discard
      case x => (assembly / assemblyMergeStrategy).value.apply(x)
}
