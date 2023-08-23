FROM alpine:latest

RUN apk add --no-cache \
    ca-certificates \
    && update-ca-certificates \
    && rm -rf /var/cache/apk/*

COPY build-dir/demo-server /usr/local/bin/myapp

EXPOSE 8082
ENTRYPOINT ["/usr/local/bin/myapp"]
