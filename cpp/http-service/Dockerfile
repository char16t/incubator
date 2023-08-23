FROM alpine:latest

RUN apk add --no-cache \
    ca-certificates \
    && update-ca-certificates \
    && rm -rf /var/cache/apk/*

COPY myapp /usr/local/bin/myapp

EXPOSE 9090
ENTRYPOINT ["/usr/local/bin/myapp"]
