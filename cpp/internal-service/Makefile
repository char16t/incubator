all: build run

build:
	rm -rf build-dir
	mkdir build-dir
	cd build-dir && cmake .. && make
	sudo docker build -t internal-service:latest .

run:
	sudo docker run -p 8082:8082 internal-service:latest
