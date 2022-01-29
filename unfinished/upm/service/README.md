## Build
```
sbt docker:publishLocal
```

## Run

```
$ docker images
REPOSITORY           TAG                 IMAGE ID            CREATED             SIZE
upsidemind-service   0.1                 6c765dcfcde9        11 seconds ago      501MB
<none>               <none>              b5674aff0e5b        13 seconds ago      522MB
<none>               <none>              339b367907d6        3 minutes ago       501MB
<none>               <none>              8e0300ff4705        10 minutes ago      501MB
<none>               <none>              bbe0e022bf59        10 minutes ago      522MB
<none>               <none>              d1b9d2a82530        27 minutes ago      83MB
<none>               <none>              65170aef4ad5        27 minutes ago      127MB
openjdk              jre                 8c8b7f0ab84c        2 weeks ago         479MB
hello-world          latest              fce289e99eb9        2 months ago        1.84kB
openjdk              jre-alpine          ccfb0c83b2fe        8 months ago        83MB
```

```
docker run -p8080:8080 6c765dcfcde9
```