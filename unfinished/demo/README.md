```
cd web
npm run build
docker build -t demo-web .
docker run --name demo-web-1 -d -p 8080:80 demo-web
```

Push to Google Cloud
```
gcloud auth configure-docker
docker tag demo-web gcr.io/smiling-audio-295610/demo-web:latest
docker push gcr.io/smiling-audio-295610/demo-web:latest
```

Pull from Google Cloud
```
gcloud auth configure-docker
docker pull gcr.io/smiling-audio-295610/demo-web:latest
```

Configure Kubernetes
```
gcloud container clusters list
gcloud container clusters get-credentials demo-k8s-cluster --zone=us-central1-c
```

Create deployment
```
kubectl create deployment demo-web --image=gcr.io/smiling-audio-295610/demo-web:latest
kubectl expose deployment demo-web --name=demo-web-service --type=LoadBalancer --port 80 --target-port 80
```
See also: https://cloud.google.com/kubernetes-engine/docs/tutorials/hello-app

Build server
```
sbt assembly
```

Deploy server
```
docker build --build-arg JAR_FILE=target/scala-2.13/server-assembly-*.jar -t demo-server .
docker tag demo-server gcr.io/smiling-audio-295610/demo-server:latest
docker push gcr.io/smiling-audio-295610/demo-server:latest

kubectl create deployment demo-server --image=gcr.io/smiling-audio-295610/demo-server:latest
kubectl expose deployment demo-server --name=demo-server-service --type=LoadBalancer --port 80 --target-port 8080
```

Redeploy
```
kubectl set image deployment/demo-server demo-server=gcr.io/smiling-audio-295610/demo-server:v2
```