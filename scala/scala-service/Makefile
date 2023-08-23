default: build

build: clean
	sbt assembly

test: clean
	sbt test

clean:
	sbt clean

run: build
	java -jar target/demo.jar

build-image: build
	sudo docker build -t demo .

run-container: build-image
	sudo docker run -it -p 8080:8080 --rm demo
