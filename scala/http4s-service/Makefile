build:
	sbt assembly
	native-image \
		-jar target/scala-3.3.0/demo-assembly-0.1.0.jar \
		target/scala-3.3.0/demo-assembly-0.1.0 \
		-H:ReflectionConfigurationFiles=src/main/resources/reflection-config.json \
		-H:ResourceConfigurationFiles=src/main/resources/resource-config.json
