## Repository structure

 * `cm` contains configuration management files
 * `static` contains static files that will be deployed to server as is

## Build image

```
docker build -t html-server-image:v1 .
```

## Run container

```
docker run -d -p 80:80 html-server-image:v1
```

## Save image to transfer

```
docker save html-server-image:v1 > saved-image.tar
```

## Transferring image

```
Once saved, transfer the .tar file to target server using any of the file transfer protocols like FTP or SCP.
```

## Loading docker image

Once the .tar file is transferred to target machine, login to target machine and load the image into local registry using docker load command.

```
docker load < saved-image.tar
```
