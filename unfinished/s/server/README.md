### Run in Docker

Build the Docker base image:

```bash
docker build -t local/openjdk-jre-8-bash:latest -f Dockerfile-base .
```

I use `openjdk:8-jre-alpine` as the base image, adding `bash`, networking
utility (i.e. `ping`, `telnet`), and `curl` to make it easier for debugging
purposes.


Build and upload the app:

```bash
sbt docker:publishLocal
```

Run it:

```bash
docker-compose up -d
```

Application can be stopped with:

```bash
docker-compose down
```

Check it at:

 * http://localhost:8088/members
 * http://localhost:8089/members
 * http://localhost:8090/members

### Run in Kubernetes

Build the Docker base image:

```bash
docker build -t local/openjdk-jre-8-bash:latest -f Dockerfile-base .
```

I use `openjdk:8-jre-alpine` as the base image, adding `bash`, networking
utility (i.e. `ping`, `telnet`), and `curl` to make it easier for debugging
purposes.


Build and upload the app:

```bash
sbt docker:publishLocal
```

Deploy the example app by running the following command. This will create
3 head nodes:

```bash
kubectl create -f deployment
```

Application can be stopped with:

```bash
kubectl delete -f deployment
```

Confirm the sample app is working. After a couple minutes, check the 3 head
nodes created by the example app (i.e. `myapp-0`, `myapp-1`,`myapp-2`). And
try to add a worker node to join the cluster.

```bash
kubectl scale statefulsets myapp --replicas=4
```

Once the 4th pod is stood up, we can check the cluster information:

The example app exposes the `/members` endpoint which displays the list
of members visible for a particular pod. For example, the following displays
the list of members visible from `myapp-0` pod:

```
kubectl exec -ti myapp-0 -- curl -v myapp-0:9000/members
*   Trying 172.30.207.9...
* TCP_NODELAY set
* Connected to myapp-0 (172.17.0.5) port 9000 (#0)
> GET /members HTTP/1.1
> Host: myapp-0:9000
> User-Agent: curl/7.55.0
> Accept: */*
>
< HTTP/1.1 200 OK
< Server: akka-http/10.0.10
< Date: Tue, 01 Oct 2019 10:06:32 GMT
< Content-Type: application/json
< Content-Length: 147
<
{
"members" : [ {
  "address" : "akka.tcp://myapp@myapp-0.myapp.default.svc.cluster.local:2551",
  "status" : "Up",
  "roles" : [ ]
}, {
  "address" : "akka.tcp://myapp@myapp-1.myapp.default.svc.cluster.local:2551",
  "status" : "Up",
  "roles" : [ ]
}, {
  "address" : "akka.tcp://myapp@myapp-2.myapp.default.svc.cluster.local:2551",
  "status" : "Up",
  "roles" : [ ]
}, {
  "address" : "akka.tcp://myapp@myapp-3.myapp.default.svc.cluster.local:2551",
  "status" : "Up",
  "roles" : [ ]
} ]

* Connection #0 to host myapp-0 left intact
```
