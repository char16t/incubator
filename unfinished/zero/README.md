## Build

```
sbt assembly
docker build . -t zero
```

## Run

```
 docker run -p 8080:8080 -it zero
```

## Minikube

```
# Start minikube
minikube start

# Set docker env
eval $(minikube docker-env)

# Build image
docker build -t zero:latest .

# Run in minikube
kubectl run hello-foo --image=zero:latest --image-pull-policy=Never

# Check that it's running
kubectl get pods

kubectl expose deployment zero --type=NodePort
minikube service zero --url
```

## TODO

 - [x] Try to build Docker image and run dockerized application
 - [ ] Replace base image from `openjdk:8` to something with JRE only
 - [ ] Describe deployment configuration in yaml file
 - [ ] Docker Compose
 - [ ] Add tests for REST APIs
 - [ ] Test coverage
 - [ ] Performance testing
 - [ ] Store all data in database