all: build run

build:
	g++ -static -Wall myapp.cpp -o myapp
	sudo docker build -t myapp:latest .

run:
	sudo docker run -p 9090:9090 myapp:latest

install:
	cp ./myapp.service /etc/systemd/system

start:
	systemctl start myapp.service

uninstall:
	systemctl stop myapp.service
	rm /etc/systemd/system/myapp.service
